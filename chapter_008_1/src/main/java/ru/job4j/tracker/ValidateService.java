package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Validation service wrapper for MemoryStore.
 * The thread-safe singleton.
 */
public class ValidateService implements Credential<String, String>, Validate<User> {

    /**
     * A thread-safe instance of validator.
     */
    private static final ValidateService SERVICE = new ValidateService();

    /**
     * The store of users.
     */
    private Store<User> store = DbStore.getInstance();

    /**
     * A connection pool to the database.
     */
    private BasicDataSource dataSource = new DatabaseSource();

    private ValidateService() {
    }

    /**
     * Gets instance of validator.
     *
     * @return - the validator.
     */
    public static ValidateService getInstance() {
        return SERVICE;
    }

    /**
     * Updates user's data by ID.
     *
     * @param user - an updated user.
     * @throws NoSuchElementException - if a specified user is not found.
     * @throws NullPointerException   - if user is null or if user's ID is null.
     */
    @Override
    public void update(User user) throws NoSuchElementException, NullPointerException {
        if (!hasAccess(user) || this.store.findById(user.getId()) == null) {
            throw new NoSuchElementException("User with such ID not found");
        }
        this.store.update(user);
    }

    /**
     * Adds the user to store. If this user doesn't has access or
     * user already contains in the store, then throws exception.
     *
     * @param user - a new user.
     * @throws NullPointerException     - if user's value is null.
     * @throws IllegalArgumentException - if specified user already exists.
     */
    @Override
    public void add(User user) throws NullPointerException, IllegalArgumentException {
        if (!this.hasAccess(user) || this.store.findById(user.getId()) != null) {
            throw new IllegalArgumentException("User with this ID already exists");
        }
        this.store.add(user);
    }

    /**
     * Removes the user from store by ID.
     *
     * @param id - user's ID.
     * @throws NullPointerException   - if ID is null.
     * @throws NoSuchElementException - a user with such ID not found.
     */
    @Override
    public void delete(Integer id) throws NoSuchElementException, NullPointerException {
        if (id == null) {
            throw new NullPointerException("ID is null");
        } else if (this.store.findById(id) == null) {
            throw new NoSuchElementException("User with such ID not found");
        }
        this.store.delete(id);
    }

    /**
     * Returns list of users.
     *
     * @return - a list of users.
     */
    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }

    /**
     * Looks for a user from the repository by ID.
     *
     * @param id -ID.
     * @return - the user found, null if not found.
     */
    @Override
    public User findById(Integer id) {
        User user = null;
        if (id != null) {
            user = this.store.findById(id);
        }
        return user;
    }

    /**
     * Validates user's ID.
     *
     * @param user - a verifiable user.
     * @return - true if ID is correct, throws exception - otherwise.
     * @throws NullPointerException - if ID is null.
     */
    private boolean hasAccess(User user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException("User must not be null");
        } else if (user.getPassword() == null ||
                user.getLogin() == null ||
                user.getName() == null ||
                user.getCreateDate() == null ||
                user.getEmail() == null ||
                user.getRole() == null) {
            throw new NullPointerException("User's fields must be contain the values");
        }
        return true;
    }

    /**
     * Verifies that the user is in a system.
     *
     * @param login    - a login.
     * @param password - a password.
     * @return - true - if user is in system, false - otherwise or if one of parameters is null.
     */
    @Override
    public boolean isCredential(String login, String password) throws SQLException, IOException {
        boolean isCredential = false;
        String script = new Resource().content("database/scripts/user_authentication.sql");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(script)) {
            statement.setString(1, login);
            statement.setString(2, password);
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
