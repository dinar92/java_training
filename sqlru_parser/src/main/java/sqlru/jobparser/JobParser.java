package sqlru.jobparser;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sqlru.core.Work;

import java.io.IOException;
import java.sql.*;

/**
 * Jobs parser from the sql.ru.
 * Main class in this package, which starts process.
 * Is launched by 'quartz' as a new detached thread.
 */
public class JobParser extends Work {

    /**
     * The logger of errors.
     */
    private final Logger logger = LoggerFactory.getLogger(JobParser.class);

    /**
     * Start point of the parser.
     * If the application started first time, then creates necessary table
     * and parsers offers from the beginning of the year.
     * If the application started second or more time, then
     * appends offers to existing table.
     * Logs the results to single file.
     *
     * @param jobExecutionContext -  A context bundle containing handles to various environment information, that
     *                            is given to a <code>{@link org.quartz.JobDetail}</code> instance as it is
     *                            executed, and to a <code>{@link Trigger}</code> instance after the
     *                            execution completes.
     * @throws JobExecutionException -  An exception that can be thrown by a <code>{@link org.quartz.Job}</code>
     *                               to indicate to the Quartz <code>{@link Scheduler}</code> that an error
     *                               occurred while executing, and whether or not the <code>Job</code> requests
     *                               to be re-fired immediately (using the same <code>{@link JobExecutionContext}</code>,
     *                               or whether it wants to be unscheduled.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Offers offers;

        try (DBAccess access = new DBAccess()) {
            if (!access.isExist()) {
                access.initTable();
                offers = new YearBeginOffers();
            } else {
                offers = new ContinuousOffers(access);
            }
            new DBWriter(access).write(offers);
            access.close();
            new LogWriter().write(offers);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

}