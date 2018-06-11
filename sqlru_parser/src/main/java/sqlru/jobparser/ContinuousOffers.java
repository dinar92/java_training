package sqlru.jobparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A container of new offers since the last addition.
 * Uses a access to database.
 * Must be use for continue adding offers to existing database.
 */
public class ContinuousOffers implements Offers {

    /**
     * A page implementation of sql.ru site.
     */
    private Page page = new Page();

    /**
     * A list of new offers.
     */
    private LinkedList<Offer> offerList = new LinkedList<>();

    /**
     * Access to database with offers.
     */
    private DBAccess access;

    /**
     * The error logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ContinuousOffers.class);

    /**
     * Sets access to database.
     *
     * @param access - access.
     */
    public ContinuousOffers(DBAccess access) {
        this.access = access;
    }

    /**
     * Iterator of offer's list.
     *
     * @return - iterator.
     */
    @Override
    public Iterator<Offer> iterator() {
        if (offerList.isEmpty()) {
            try {
                this.init();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return offerList.listIterator();
    }

    /**
     * Fills the list of new offers.
     *
     * @throws Exception - if the flag of the last element is not set in the database or if page was not set.
     */
    private void init() throws Exception {
        Offer lastOfferFromDB = this.access.getLast();
        Offer currentOffer;
        boolean endOfLoop = false;
        if (lastOfferFromDB == null) {
            throw new Exception("The last offer is null. The flag 'isLast' not setted in your DB table or database ");
        }
        for (int pageCounter = 1; !endOfLoop && page.setPageNumber(pageCounter); pageCounter++) {
            for (Topic topic : page.topicsList()) {
                currentOffer = topic.getContent();
                if (lastOfferFromDB.equals(currentOffer)) {
                    endOfLoop = true;
                    break;
                }
                offerList.addFirst(currentOffer);
            }
        }
    }

    /**
     * Returns the last offer.
     *
     * @return - the last offer.
     */
    @Override
    public Offer getLast() {
        if (offerList.isEmpty()) {
            try {
                this.init();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return offerList.getLast();
    }
}
