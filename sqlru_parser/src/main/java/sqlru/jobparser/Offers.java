package sqlru.jobparser;

import java.util.Iterator;

/**
 * A container of offers for iterating.
 * Can be used in foreach construction.
 * Has a function of returning the last offer.
 */
public interface Offers extends Iterable<Offer> {

    /**
     * Returns iterator by offers.
     * @return - an iterator.
     */
    Iterator<Offer> iterator();

    /**
     * Returns a last offer.
     * @return - a last offer.
     */
    Offer getLast();
}
