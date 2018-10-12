package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A roles validation service.
 */
public class RoleValidateService implements Validate<Role>, Credential<String, String> {

    /**
     * A thread-safe instance of validator.
     */
    private static final RoleValidateService SERVICE = new RoleValidateService();

    /**
     * A connection pool to the database.
     */
    private BasicDataSource dataSource = new DatabaseSource();

    /**
     * The store of roles.
     */
    private Store<Role> store = RoleStore.getInstance();

    private RoleValidateService() {
    }

    /**
     * Gets instance of validator.
     *
     * @return - the validator.
     */
    public static RoleValidateService getInstance() {
        return SERVICE;
    }

    /**
     * Adds the role to store.
     *
     * @param role - a new role.
     * @throws NullPointerException     - if role's value is null or contains nulls.
     * @throws IllegalArgumentException - if specified role already exists.
     */
    @Override
    public void add(Role role) throws NullPointerException, IllegalArgumentException {
        if (hasAccess(role) && this.store.findById(role.getId()) != null) {
            throw new IllegalArgumentException("Role with this ID already exists");
        }
        this.store.add(role);
    }

    /**
     * Updates role's data by ID.
     *
     * @param role - an updated user.
     * @throws NoSuchElementException - if a specified role is not found.
     * @throws NullPointerException     - if role's value is null or contains nulls.
     */
    @Override
    public void update(Role role) throws NoSuchElementException, NullPointerException {
        if (hasAccess(role) && this.store.findById(role.getId()) == null) {
            throw new NoSuchElementException("Role with this ID do not exist");
        }
        this.store.update(role);
    }

    /**
     * Removes the role from store by ID.
     *
     * @param id - role's ID.
     * @throws NullPointerException   - if ID is null.
     * @throws NoSuchElementException - a role with such ID not found
     */
    @Override
    public void delete(Integer id) throws NoSuchElementException, NullPointerException {
        if (id == null) {
            throw new NullPointerException("ID can not be null");
        }
        if (this.store.findById(id) == null) {
            throw new NoSuchElementException("Role with this ID do not exist");
        }
        this.store.delete(id);
    }

    /**
     * Returns list of roles.
     *
     * @return - a list of roles.
     */
    @Override
    public List<Role> findAll() {
        return this.store.findAll();
    }

    /**
     * Looks for a role from the repository by ID.
     *
     * @param id - ID.
     * @return - the role found, null if not found.
     * @throws NullPointerException   - if ID is null.
     */
    @Override
    public Role findById(Integer id) {
        if (id == null) {
            throw new NullPointerException("ID can not be null");
        }
        return this.store.findById(id);

    }


    /**
     * Validates of roles for nulls content.
     *
     * @param role - role for validation.
     * @return - true if role is correct, throws exception - otherwise.
     * @throws NullPointerException   - if role is null or role contains null fields.
     */
    private boolean hasAccess(final Role role) throws NullPointerException, NoSuchElementException {
        if (role == null) {
            throw new NullPointerException("Role must not be null");
        } else if (role.getName() == null ||
                role.getId() == null) {
            throw new NullPointerException("Role's fields must be contain the values");
        }
        return true;
    }

    /**
     * Check for matching username and role.
     * @param login - user's login.
     * @param role - role.
     * @return - true - if login has specified role, false - otherwise.
     * @throws IOException - IOException.
     * @throws SQLException - SQLException.
     */
    @Override
    public boolean isCredential(String login, String role) throws IOException, SQLException {
        boolean isCredential = false;
        String script = new Resource().content("database/scripts/admin_authorization.sql");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setString(1, login);
            statement.setString(2, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt("credential") == 1) {
                        isCredential = true;
                    }
                }
            }
        }
        return isCredential;
    }
}
