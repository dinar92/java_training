package ru.job4j.sql.jdbc;


/**
 * The javabean like class. Contains configuration parameters about
 * the database connection and count of fields for manipulation.
 */
public class ConfBean {
    /**
     * Count of fields.
     */
    private int fieldCount;

    /**
     * The database's driver name.
     */
    private String driver;

    /**
     * The database's URL.
     */
    private String databaseUrl;

    /**
     * The default constructor.
     */
    public ConfBean() {
    }

    /**
     * Getter of count of fields.
     *
     * @return - count of fields.
     */
    public int getFieldCount() {
        return fieldCount;
    }

    /**
     * Setter of count of fields.
     *
     * @param fieldCount - count of fields.
     */
    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    /**
     * Getter of the driver name.
     *
     * @return - the database's driver name.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Setter of the driver name.
     *
     * @param driver - the database's driver name.
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * Getter of the database's URL.
     *
     * @return - the database's URL.
     */
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * Setter of the database's URL.
     *
     * @param databaseUrl - the database's URL.
     */
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
}
