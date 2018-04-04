package ru.job4j.sql.jdbc;

import java.sql.SQLException;

/**
 * Provides simple manipulations with tables in SQL databases.
 */
public interface SQLTableManager {
    /**
     * Creates table in database with the specified name.
     * Varargs determines the columns in the table. Writes as SQL (Sample: "line VARCHAR(30) NOT NULL").
     * @param tableName - the name of the future table.
     * @param columns - columns of the table.
     * @return true - if the table is created, false - if the table already exist.
     * @exception SQLException - if SQL request could not be executed.
     */
    boolean createTable(String tableName, String ... columns) throws SQLException;

    /**
     * Removes table from the database.
     * @param tableName - the bane of the table.
     * @return true - if the table is removed, false - otherwise.
     * @exception SQLException - if SQL request could not be executed.
     */
    boolean deleteTable(String tableName) throws SQLException;

    /**
     * Clears all table's entries.
     * @param tableName - the name of the table.
     * @return true - if the table is cleaned, false - otherwise.
     * @exception SQLException - if SQL request could not be executed.
     */
    boolean clearTable(String tableName) throws SQLException;

}
