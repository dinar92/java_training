package sqlru.jobparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Writer of new offers to the single log file.
 */
public class LogWriter {

    /**
     * The job offers logger.
     */
    private final Logger logger = LoggerFactory.getLogger("ExternalAppLogger");

    /**
     * Writes specified offers to log.
     *
     * @param offers - offers.
     */
    public void write(Offers offers) {
        for (Offer offer : offers) {
            logger.info(offer.toString());
        }
    }
}
