package ru.job4j.sql.jdbc;

import org.hamcrest.Matchers;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests for the class Application.
 */
public class ApplicationTest {

    /**
     * The configuration bean.
     */
    private final static ConfBean CONF_BEAN = new ConfBean();
    /**
     * Necessary count of fields for testing.
     */
    private final static int COUNT_OF_FIELDS = 1000000;
    /**
     * The database's driver name.
     */
    private final static String DRIVER_NAME = "org.sqlite.JDBC";
    /**
     * The database's URL to connection.
     */
    private final static String DATABASE_URL = "jdbc:sqlite:test2.db";
    /**
     * Expected out line to console.
     */
    private final static String EXPECT_RESULT = "500000500000\n";

    /**
     * Initializes bean's settings.
     */
    @BeforeClass
    public static void initConfBean() {
        CONF_BEAN.setFieldCount(COUNT_OF_FIELDS);
        CONF_BEAN.setDriver(DRIVER_NAME);
        CONF_BEAN.setDatabaseUrl(DATABASE_URL);
    }

    /**
     * Clears table's data.
     */
    @After
    public void clearTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            SQLTableManager manager = new SQLiteTableManager(connection);
            manager.deleteTable("TEST");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the file of database.
     */
    @AfterClass
    public static void clearDirectory() {
        new File("./test2.db").delete();
    }

    /**
     * Measures the running time. Must be less than 5 min.
     */
    @Test
    public void whenStartApplicationThenNotMoreThan5MIn() {
        long fiveMinInMillis = 300000;
        long startTime  = System.currentTimeMillis();

        Application application = new Application(CONF_BEAN);
        try {
            application.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime, Matchers.lessThan(fiveMinInMillis));
    }

    /**
     * Works through all branches of application.
     * Asserts output to console.
     * Creates a new table in database.
     */
    @Test
    public void whenRunThenPrintSumToConsole() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Application application = new Application(CONF_BEAN);
        try {
            application.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(outputStream.toString(), is(EXPECT_RESULT));
    }

    /**
     * Works through all branches of application without creating the new table.
     */
    @Test
    public void whenTableAlreadyExistThenPrintSumToConsole() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            SQLTableManager manager = new SQLiteTableManager(connection);
            manager.createTable("TEST", "field INTEGER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Application application = new Application(CONF_BEAN);
        try {
            application.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(outputStream.toString(), is(EXPECT_RESULT));
    }
}
