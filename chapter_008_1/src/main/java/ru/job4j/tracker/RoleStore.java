package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The storage of roles.
 */
public class RoleStore implements Store<Role> {

    /**
     * A connection pool of a database.
     */
    private static final BasicDataSource SOURCE = new DatabaseSource();

    /**
     * An instance of the current database.
     */
    private static final RoleStore INSTANCE = new RoleStore();

    /**
     * A resource files reader.
     */
    private static Resource RESOURCE = new Resource();

    /**
     * An instance of logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbStore.class);

    /**
     * Sets properties of database.
     */
    private RoleStore() {
    }

    /**
     * Checks existing of a table in database. Uses a specified script.
     *
     * @return - true - if exist, false - otherwise.
     * @throws IOException  - If an I/O error occurs.
     * @throws SQLException - if a database access error occurs.
     */
    private static boolean isTableExist() throws IOException, SQLException {
        String pathToScript = "database/scripts/role_db_exist_check.sql";
        String query = RESOURCE.content(pathToScript);
        boolean tableExist = false;
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                if (resultSet.getInt("count") != 0) {
                    tableExist = true;
                }
            }
        }
        return tableExist;
    }

    /**
     * Initializes a table in the database.
     *
     * @throws IOException  - If an I/O error occurs.
     * @throws SQLException - if a database access error occurs.
     */
    private static void initTable() throws SQLException, IOException {
        String pathToScript = "database/scripts/role_db_init.sql";
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(RESOURCE.content(pathToScript));
        }
    }

    /**
     * Gets an instance of a database user store.
     *
     * @return - an instance.
     */
    public static Store getInstance() {
        try {
            if (!isTableExist()) {
                initTable();
            }
        } catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return INSTANCE;
    }

    /**
     * Adds new role to database.
     *
     * @param role - new Role.
     */
    @Override
    public void add(Role role) {
        String pathToScript = "database/scripts/role_add.sql";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, role.getId());
                statement.setString(2, role.getName());
                statement.executeUpdate();
                connection.commit();
            } catch (IOException e) {
                connection.rollback();
                LOGGER.error(e.getMessage(), e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Updates existing role by ID.
     *
     * @param role - updated role.
     */
    @Override
    public void update(Role role) {
        String pathToScript = "database/scripts/role_update.sql";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setString(1, role.getName());
                statement.setInt(2, role.getId());
                statement.executeUpdate();
                connection.commit();
            } catch (IOException e) {
                connection.rollback();
                LOGGER.error(e.getMessage(), e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes a role from dattabase by ID.
     *
     * @param id - element's ID.
     */
    @Override
    public void delete(Integer id) {
        String pathToScript = "database/scripts/role_delete.sql";
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, id);
                statement.executeUpdate();
                connection.commit();
            } catch (IOException e) {
                connection.rollback();
                LOGGER.error(e.getMessage(), e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Returns all roles from the database.
     *
     * @return - a list of roles.
     */
    @Override
    public List<Role> findAll() {
        String pathToScript = "database/scripts/role_get_all.sql";
        List<Role> roles = new ArrayList<>();
        Role role = null;
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(RESOURCE.content(pathToScript))) {
            while (result.next()) {
                role = new Role();
                role.setId(result.getInt("id"));
                role.setName(result.getString("name"));
                roles.add(role);
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return roles;
    }

    /**
     * Finds a role with specified ID.
     *
     * @param id - ID.
     * @return - found role or null if not found.
     */
    @Override
    public Role findById(Integer id) {
        String pathToScript = "database/scripts/role_find_by_id.sql";
        Role role = null;

        try (Connection connection = SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        role = new Role();
                        role.setId(resultSet.getInt("id"));
                        role.setName(resultSet.getString("name"));
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }
}
