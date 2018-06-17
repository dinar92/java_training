package sqlru.jobparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A container of new offers since the beginning of year.
 */
public class YearBeginOffers implements Offers {

    /**
     * A page implementation of sql.ru site.
     */
    private Page page = new Page();

    /**
     * A list of new offers.
     */
    private LinkedList<Offer> offersList;

    /**
     * The error logger.
     */
    private final Logger logger = LoggerFactory.getLogger(YearBeginOffers.class);

    /**
     * Iterator of offer's list.
     *
     * @return - iterator.
     */
    @Override
    public Iterator<Offer> iterator() {
        if (offersList == null) {
            try {
                this.init();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return offersList.listIterator();
    }

    /**
     * Returns the last offer.
     *
     * @return - the last offer.
     */
    @Override
    public Offer getLast() {
        if (offersList == null) {
            try {
                this.init();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return offersList.getLast();
    }

    /**
     * Checks compliance with current year.
     *
     * @param inputDate - date for comparing.
     * @return - true - if corresponds, false - otherwise.
     */
    private boolean checkByCurrentYear(LocalDateTime inputDate) {
        return LocalDateTime.now().getYear() == inputDate.getYear();
    }

    /**
     * Fills the list of new offers.
     *
     * @throws Exception - if page was not set.
     */
    private void init() throws Exception {
        boolean isEnd = false;
        Offer currentOffer;
        offersList = new LinkedList<>();
        for (int pageNumber = 1; !isEnd && page.setPageNumber(pageNumber); pageNumber++) {
            for (Topic topic : page.topicsList()) {
                currentOffer = topic.getContent();
                if (checkByCurrentYear(topic.getDateTime())) {
                    offersList.addFirst(currentOffer);
                } else {
                    isEnd = true;
                    break;
                }
            }
        }
    }
}
