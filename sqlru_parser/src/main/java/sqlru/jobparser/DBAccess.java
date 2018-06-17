package sqlru.jobparser;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

import sqlru.core.Resource;

/**
 * Access implementation to the database.
 * Works only with "job_offer" table.
 * Has service functionality.
 * Uses data for connection from "conf/db" resources.
 * Executes scripts from "script" resources.
 * Can be used in try-with-resources constructions.
 */
public class DBAccess implements AutoCloseable {

    /**
     * A connection to the database.
     */
    private Connection connection;

    /**
     * Resources reader.
     */
    private Resource resource = new Resource();

    /**
     * Sets the connection to the database by "driver" and "url" files.
     *
     * @throws IOException            - If an I/O error occurs.
     * @throws ClassNotFoundException - Thr driver not found.
     * @throws SQLException           - if a database access error occurs or the url is null.
     */
    public DBAccess() throws IOException, ClassNotFoundException, SQLException {
        Class.forName(resource.content("jdbc/driver"));
        connection = DriverManager.getConnection(resource.content("jdbc/url"));
    }

    /**
     * Closes the connection to the database.
     *
     * @throws SQLException - if connection cannot be closed.
     */
    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Returns the last offer as an object from database.
     * The database contains a field with 'isLast' column.
     * Its value contains '1' if field was insert last, other fields - contain '0'.
     *
     * @return - an offer implementation of a databases entry, with the same parameters.
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public Offer getLast() throws SQLException, IOException {
        Offer lastOffer = null;
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(resource.content("scripts/getLastOffer.sql"))) {
            while (result.next()) {
                lastOffer = new Offer();
                lastOffer.setAuthor(result.getString("author"));
                lastOffer.setTitle(result.getString("title"));
                lastOffer.setContent(result.getString("content"));
                lastOffer.setPostTimeDate(LocalDateTime.parse(result.getString("post_timedate")));

            }
        }
        return lastOffer;
    }

    /**
     * Initializes a working table ('job_offer') in the database.
     *
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public void initTable() throws SQLException, IOException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(resource.content("scripts/initJobTable.sql"));
        }
    }

    /**
     * Verifies the existence of the working table ('job_offer').
     *
     * @return - true - if exists, false - otherwise.
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public boolean isExist() throws SQLException, IOException {
        int result = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(resource.content("scripts/checksTableExists.sql"))) {
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        }
        return (result == 1);
    }

    /**
     * Sets flag 'isLast' from the old last offer to the specified offer.
     * Uses an offers unique property - content.
     *
     * @param newLastOffer - an offer, which will be the last.
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public void resetLastFlag(Offer newLastOffer) throws SQLException, IOException {
        try (Statement statement = connection.createStatement(); PreparedStatement preparedStatement = connection.prepareStatement(resource.content("scripts/setLastFlagToOffer.sql"))) {
            statement.executeUpdate(resource.content("scripts/unsetLastFlagFromOffer.sql"));
            preparedStatement.setString(1, newLastOffer.getContent());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Checks for a duplicate of the specified offer.
     *
     * @param offer - an inspected offer.
     * @return - true - if a duplicate is found, false - otherwise.
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public boolean isDuplicate(Offer offer) throws SQLException, IOException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(resource.content("scripts/checkDuplicate.sql"))) {
            statement.setString(1, offer.getContent());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(1) != 0) {
                        result = true;
                    }
                }
                return result;
            }
        }
    }

    /**
     * Writes the specified offer to the database.
     *
     * @param offer - a new offer.
     * @throws IOException  - if a database access error occurs, or the database contains duplicate.
     * @throws SQLException - if an I/O error occurs.
     */
    public void write(Offer offer) throws IOException, SQLException {
        try (PreparedStatement statement = connection.prepareStatement(resource.content("scripts/insertOfferToJobTable.sql"))) {
            statement.setString(1, offer.getAuthor());
            statement.setString(2, offer.getTitle());
            statement.setString(3, offer.getContent());
            statement.setString(4, offer.getPostTimeDate().toString());
            statement.setString(5, offer.getUrl());
            statement.executeUpdate();
        }
    }
}
