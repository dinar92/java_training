package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Source for the connection pool of the database.
 */
public class DatabaseSource extends BasicDataSource {

    /**
     * An instance of logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbStore.class);

    /**
     * Sets properties of database.
     */
    public DatabaseSource() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("database/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            this.setDriverClassName(properties.getProperty("db.driver"));
            this.setUrl(properties.getProperty("db.url"));
            this.setUsername(properties.getProperty("db.username"));
            this.setPassword(properties.getProperty("db.password"));
            this.setMinIdle(Integer.parseInt(properties.getProperty("dbcp.minIdle")));
            this.setMaxIdle(Integer.parseInt(properties.getProperty("dbcp.maxIdle")));
            this.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("dbcp.maxOpenPreparedStatements")));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
