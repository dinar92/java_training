package ru.job4j.sql.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides some operations with SQLite databases.
 */
public class SQLiteTableManager implements SQLTableManager {
    /**
     * The connection to the database.
     */
    private final Connection connection;

    /**
     * Sets the connection to database.
     *
     * @param connection - the connection to the database.
     */
    public SQLiteTableManager(Connection connection) {
        this.connection = connection;
    }

    public boolean createTable(String tableName, String... sqlColumns) throws SQLException {
        boolean wasCreate = false;
        try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});
             Statement statement = connection.createStatement()) {
            if (!resultSet.next()) {
                StringBuilder builder = new StringBuilder(String.format("CREATE TABLE %s (", tableName));
                String delimiter = ",";
                for (int i = 0; i < sqlColumns.length; i++) {
                    builder.append(sqlColumns[i]);
                    if (i < sqlColumns.length - 1) {
                        builder.append(delimiter);
                    }
                }
                builder.append(")");
                statement.execute(builder.toString());
                wasCreate = true;
            }
        }
        return wasCreate;
    }

    public boolean clearTable(String tableName) throws SQLException {
        return executeCommand(tableName, String.format("DELETE FROM %s", tableName));
    }

    public boolean deleteTable(String tableName) throws SQLException {
        return executeCommand(tableName, String.format("DROP TABLE %s", tableName));
    }

    /**
     * Executes the SQL command to perform data deletion operations or completely table.
     *
     * @param tableName - the table name.
     * @param command   - SQL command.
     * @return true - if query is completed, false - otherwise.
     */
    private boolean executeCommand(String tableName, String command) throws SQLException {
        boolean success = false;
        try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});
             Statement statement = connection.createStatement()) {
            if (resultSet.next()) {
                statement.execute(command);
                success = true;
            }
        }
        return success;
    }
}
