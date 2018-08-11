package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A database store implementation.
 */
public class DbStore implements Store<User> {

    /**
     * A connection pool of a database.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * An instance of the current database.
     */
    private static final DbStore INSTANCE = new DbStore();

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
    private DbStore() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("database/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            SOURCE.setDriverClassName(properties.getProperty("db.driver"));
            SOURCE.setUrl(properties.getProperty("db.url"));
            SOURCE.setUsername(properties.getProperty("db.username"));
            SOURCE.setPassword(properties.getProperty("db.password"));
            SOURCE.setMinIdle(Integer.parseInt(properties.getProperty("dbcp.minIdle")));
            SOURCE.setMaxIdle(Integer.parseInt(properties.getProperty("dbcp.maxIdle")));
            SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("dbcp.maxOpenPreparedStatements")));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Checks existing of a table in database. Uses a specified script.
     * @return - true - if exist, false - otherwise.
     * @throws IOException - If an I/O error occurs.
     * @throws SQLException - if a database access error occurs.
     */
    private static boolean isTableExist() throws IOException, SQLException {
        String pathToScript = "database/scripts/database_exist_check.sql";
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
     * @throws IOException - If an I/O error occurs.
     * @throws SQLException - if a database access error occurs.
     */
    private static void initTable() throws SQLException, IOException {
        String pathToScript = "database/scripts/user_db_init.sql";
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(RESOURCE.content(pathToScript));
        }
    }

    /**
     * Gets an instance of a database user store.
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
     * Adds a new user to the database store.
     * @param user - a new user.
     */
    @Override
    public void add(User user) {
        String pathToScript = "database/scripts/add_user.sql";

        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, user.getId());
                statement.setString(2, user.getName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                statement.setDate(5, Date.valueOf(user.getCreateDate()));
                statement.executeUpdate();
            } catch (IOException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.error(e.getMessage(), e);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Updates an existing user by ID.
     * @param user - an updated user.
     */
    @Override
    public void update(User user) {
        String pathToScript = "database/scripts/update_user.sql";

        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getEmail());
                statement.setDate(4, Date.valueOf(user.getCreateDate()));
                statement.setInt(5, user.getId());
                statement.executeUpdate();
            } catch (IOException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.error(e.getMessage(), e);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Removes an user from the database by ID.
     * @param id - user's ID.
     */
    @Override
    public void delete(Integer id) {
        String pathToScript = "database/scripts/delete_user_by_id.sql";

        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (IOException e) {
                connection.rollback();
                LOGGER.error(e.getMessage(), e);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Returns all users from the database as List<User>.
     * @return - a list of users.
     */
    @Override
    public List<User> findAll() {
        String pathToScript = "database/scripts/get_all_users.sql";
        List<User> users = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(RESOURCE.content(pathToScript))) {
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                LocalDate date = resultSet.getDate("create_date").toLocalDate();
                user.setCreateDate(date);
                users.add(user);
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    /**
     * Returns an user from the database by ID.
     * @param id - an user's ID.
     * @return - found user.
     */
    @Override
    public User findById(Integer id) {
        String pathToScript = "database/scripts/find_by_id.sql";
        User user = null;

        try (Connection connection = SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(RESOURCE.content(pathToScript))) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setName(resultSet.getString("name"));
                        user.setLogin(resultSet.getString("login"));
                        user.setLogin(resultSet.getString("login"));
                        user.setEmail(resultSet.getString("email"));
                        LocalDate date = resultSet.getDate("create_date").toLocalDate();
                        user.setCreateDate(date);
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }
}
