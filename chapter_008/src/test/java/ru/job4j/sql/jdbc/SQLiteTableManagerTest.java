package ru.job4j.sql.jdbc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Tests for the class SQLiteTableManager.
 */
public class SQLiteTableManagerTest {

    /**
     * Default URL for SQLite.
     */
    private static final String CONNECTION_NAME = "jdbc:sqlite:test1.db";

    /**
     * Default SQLite driver name.
     */
    private static final String DRIVER_NAME = "org.sqlite.JDBC";

    /**
     * Connection to the database.
     */
    private Connection connection = null;

    /**
     * An object of the tested class.
     */
    private SQLiteTableManager manipulator = null;

    /**
     * Defines new connection's resources before every test.
     */
    @Before
    public void openResources() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(CONNECTION_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection.
     */
    @After
    public void closeResources() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes the testing database.
     */
    @AfterClass
    public static void deleteDB() {
        new File("./test1.db").delete();
    }

    /**
     * Tests creation of table.
     */
    @Test
    public void whenCreateTableThenSuccessCreated() {
        manipulator = new SQLiteTableManager(connection);
        String tableName = "TestTable1";
        boolean tableExistInDB = true;

        try {
            manipulator.createTable(tableName, "field varchar(30)", "number INTEGER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"})) {
            Assert.assertThat(resultSet.next(), is(tableExistInDB));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests creation. If table already exist then nothing.
     */
    @Test
    public void whenTableAlreadyExistThenNothing() {
        manipulator = new SQLiteTableManager(connection);
        String tableName = "TestTable1";
        boolean tableExistInDB = true;
        boolean hasMoreTables = false;

        try {
            manipulator.createTable(tableName, "field varchar(30)");
            manipulator.createTable(tableName, "field varchar(30)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[] {"TABLE"})) {
            assertThat(resultSet.next(), is(tableExistInDB));
            assertThat(resultSet.next(), is(hasMoreTables));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests removing the table from database.
     */
    @Test
    public void whenDeleteTableThenSuccessDeleted() {
        manipulator = new SQLiteTableManager(connection);
        String tableName = "TestTable2";
        boolean tableIsNotInDB = true;

        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("CREATE TABLE %s (field VARCHAR(30))", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            manipulator.deleteTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"});
            Assert.assertThat(resultSet.next(), is(tableIsNotInDB));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests clearing data from the table in database.
     */
    @Test
    public void whenClearTableThenTableIsEmpty() {
        manipulator = new SQLiteTableManager(connection);
        String tableName = "TestTable3";
        String varcharForTable = "Some varchar";
        int intForTable = 1;
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("CREATE TABLE %s (field VARCHAR(30), number INTEGER)", tableName));
            statement.execute(String.format("INSERT INTO %s(field, number) VALUES('%s', %d)", tableName, varcharForTable, intForTable));
            manipulator.clearTable(tableName);
            ResultSet resultSet = statement.executeQuery(String.format("SELECT field, number FROM %s", tableName));
            if (resultSet.next()) {
                assertThat(resultSet.getString("field"), is(nullValue()));
                assertThat(resultSet.getInt("number"), is(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests non-existent table clear function.
     */
    @Test
    public void whenTableNotExistThenReturnFalse() {
        manipulator = new SQLiteTableManager(connection);
        String nonExistentTable = "table";
        boolean successDropTable = false;
        try {
            assertThat(manipulator.clearTable(nonExistentTable), is(successDropTable));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}