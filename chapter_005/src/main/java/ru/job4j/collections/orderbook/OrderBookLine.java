package ru.job4j.collections.orderbook;

import com.sun.javafx.binding.StringFormatter;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Implementation of the order book's line for printing.
 */
public class OrderBookLine implements Iterator {

    /**
     * Implementation of the purchase view.
     */
    private BIDLine bidLine;

    /**
     * Implementation of the sell view.
     */
    private ASKLine askLine;

    /**
     * Sets the book, which must be convert to strings.
     *
     * @param book - the book.
     */
    public OrderBookLine(TradingOperationsBook book) {
        this.bidLine = new BIDLine(book);
        this.askLine = new ASKLine(book);
    }

    /**
     * Checks the presence of at least one element in two lists.
     *
     * @return true - if there is at least one element in the two lists, false - no more elements.
     */
    @Override
    public boolean hasNext() {
        return this.bidLine.hasNext() || this.askLine.hasNext();
    }

    /**
     * Returns the next pair of sell and purchase of the book as a string.
     * If on of the orders is missing returns "--------".
     * Elements are separated from each other by " - ".
     *
     * @return - the pair of sell and purchase as a string.
     */
    @Override
    public String next() {
        StringJoiner joiner = new StringJoiner(" - ");
        if (this.hasNext()) {
            if (this.bidLine.hasNext()) {
                joiner.add(this.bidLine.next());
            } else {
                joiner.add("---------");
            }
            if (this.askLine.hasNext()) {
                joiner.add(this.askLine.next());
            } else {
                joiner.add("---------");
            }
        } else {
            throw new NoSuchElementException();
        }
        return joiner.toString();
    }

    /**
     * The iterator of the purchases as strings.
     */
    private class BIDLine implements Iterator {

        /**
         * The iterator of collection.
         */
        private Iterator bidIterator;

        /**
         * Sets the orders book.
         *
         * @param book - the book.
         */
        BIDLine(TradingOperationsBook book) {
            this.bidIterator = book.getBIDOrders().iterator();


        }

        /**
         * Checks that book has next purchase.
         *
         * @return - true - has next purchase, false - otherwise.
         */
        @Override
        public boolean hasNext() {
            return this.bidIterator.hasNext();
        }

        /**
         * Returns next purchase as string.
         * String contains information about price and volume of the order.
         *
         * @return - the next line.
         */
        @Override
        public String next() {
            Order order = (Order) this.bidIterator.next();
            return StringFormatter.format("%s@%s", order.getVolume(), order.getPrice()).getValue();
        }
    }

    /**
     * The iterator of the sells as strings.
     */
    private class ASKLine implements Iterator {


        /**
         * The iterator of collection.
         */
        private Iterator askIterator;

        /**
         * Sets the orders book.
         *
         * @param book - the book.
         */
        ASKLine(TradingOperationsBook book) {
            this.askIterator = book.getASKOrders().iterator();

        }

        /**
         * Checks that book has next sell.
         *
         * @return - true - has next sell, false - otherwise.
         */
        @Override
        public boolean hasNext() {
            return this.askIterator.hasNext();
        }

        /**
         * Returns next sell as string.
         * String contains information about price and volume of the order.
         *
         * @return - the next line.
         */
        @Override
        public String next() {
            Order order = (Order) this.askIterator.next();
            return StringFormatter.format("%s@%s", order.getVolume(), order.getPrice()).getValue();
        }
    }
}
