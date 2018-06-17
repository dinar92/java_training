package sqlru.jobparser;

import java.io.IOException;
import java.sql.*;

/**
 * Writer of new offers to the database by access.
 */
public class DBWriter {
    /**
     * Access to the working database.
     */
    private DBAccess access;

    /**
     * Sets necessary access to the database.
     *
     * @param access - access to the database.
     */
    public DBWriter(DBAccess access) {
        this.access = access;
    }

    /**
     * Writes offers except for duplicates.
     *
     * @param offers - new offers.
     * @throws SQLException - if a database access error occurs.
     * @throws IOException  - if an I/O error occurs.
     */
    public void write(Offers offers) throws IOException, SQLException {
        for (Offer offer : offers) {
            if (!access.isDuplicate(offer)) {
                access.write(offer);
            }
        }
        access.resetLastFlag(offers.getLast());
    }
}
