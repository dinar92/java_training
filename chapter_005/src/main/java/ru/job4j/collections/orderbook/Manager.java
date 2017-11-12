package ru.job4j.collections.orderbook;

import java.util.Iterator;

/**
 * Manager who allocates profitable deals comparing orders.
 * @param <O> - the universal order of deals.
 */
public class Manager<O extends Order> {

    /**
     * The specified store of books.
     */
    OrderBookStore bookStore;

    /**
     * Setts the store of books.
     * @param bookStore - the book store.
     */
    public void setBook(OrderBookStore bookStore) {
        this.bookStore = bookStore;
    }

    /**
     * Removes unfavorable deals from the book.
     */
    public void leaveProfitableTrades() {
        for (OrderBook book : (Iterable<OrderBook>) this.bookStore) {
            Iterator<O> bidIterator = book.getBIDOrders().iterator();
            Iterator<O> askIterator = book.getASKOrders().iterator();
            Order bid = null, ask = null;
            if (bidIterator.hasNext() && askIterator.hasNext()) {
                bid = bidIterator.next();
                ask = askIterator.next();
            }
            while (bidIterator.hasNext() && askIterator.hasNext()) {
                if (bid.getPrice() >= ask.getPrice()) {
                    if (bid.getVolume() > ask.getVolume()) {
                        bid.setVolume(bid.getVolume() - ask.getVolume());
                        askIterator.remove();
                        ask = askIterator.next();
                    } else if (bid.getVolume() < ask.getVolume()) {
                        ask.setVolume(ask.getVolume() - bid.getVolume());
                        bidIterator.remove();
                        bid = bidIterator.next();
                    } else {
                        bidIterator.remove();
                        askIterator.remove();
                        bid = bidIterator.next();
                        ask = askIterator.next();
                    }
                } else {
                    bid = bidIterator.next();
                    ask = askIterator.next();
                }
            }
            this.removeSuperfluous(bidIterator, askIterator);
        }
    }

    /**
     * Removes orders without pair.
     * @param bid - buy orders.
     * @param ask - sell orders.
     */
    private void removeSuperfluous(Iterator<O> bid, Iterator<O> ask) {
        while (bid.hasNext() || ask.hasNext()) {
            if (bid.hasNext()) {
                bid.remove();
                bid.next();
            } else {
                ask.remove();
                ask.next();
            }
        }
    }
}
