package ru.job4j.collections.orderbook;

import java.util.*;

/**
 * Book to store all possible transactions for purchase and sale.
 * Purchases are sorted in descending order, sales are sorted in ascending order.
 * Orders with the same prices are summarized by quantity.
 * Provides an opportunity to receive collections of buys (BID) and sales(ASK).
 *
 * @param <O> - the universal order.
 */
public class OrderBook<O extends Order> implements TradingOperationsBook<O> {

    /**
     * The store of offers of purchases.
     * Sorted in descending order.
     */
    private final Map<Double, O> bid;

    /**
     * The store of offers of sells.
     * Sorted in ascending order.
     */
    private final Map<Double, O> ask;

    /**
     * The name of the book.
     */
    private final String bookName;

    /**
     * Sets the name of the book.
     *
     * @param bookName
     */
    public OrderBook(String bookName) {
        bid = new TreeMap<>((o1, o2) -> -o1.compareTo(o2));
        ask = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
        this.bookName = bookName;
    }

    /**
     * Adds the order in the book.
     *
     * @param order - the order.
     */
    @Override
    public void addOrder(O order) {
        if (order != null) {
            Map<Double, O> operation;
            if (order.getStatus() == Status.BUY) {
                operation = this.bid;
            } else {
                operation = this.ask;
            }
            if (operation.containsKey(order.getPrice())) {
                Order tmp = operation.get(order.getPrice());
                tmp.setVolume(tmp.getVolume() + order.getVolume());
            } else {
                operation.put(order.getPrice(), order);
            }
        }
    }

    /**
     * Check for content in the book order.
     *
     * @param order - the order.
     * @return true - contains, false - not contains.
     */
    @Override
    public boolean contains(O order) {
        return this.bid.containsValue(order) || this.ask.containsValue(order);
    }

    /**
     * Removes the order from th book.
     *
     * @param order - the order.
     */
    @Override
    public void deleteOrder(O order) {
        if (order != null) {
            if (order.getStatus() == Status.BUY) {
                this.bid.remove(this.getById(order.getId()).getPrice());
            } else {
                this.ask.remove(this.getById(order.getId()).getPrice());
            }
        }
    }

    /**
     * Returns an order with the specified ID.
     * Returns null if the order not found.
     *
     * @param id - ID.
     * @return - the order or null if not found.
     */
    public O getById(Integer id) {
        O result = null;
        for (O order : this.bid.values()) {
            if (order.getId().equals(id)) {
                result = order;
                break;
            }
        }
        if (result == null) {
            for (O order : this.ask.values()) {
                if (order.getId().equals(id)) {
                    result = order;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * The getter of buys as collection of orders.
     *
     * @return - the collection of orders.
     */
    @Override
    public Collection<O> getBIDOrders() {
        return this.bid.values();
    }

    /**
     * The getter of sells as collection of orders.
     *
     * @return - the collection of orders.
     */
    @Override
    public Collection<O> getASKOrders() {
        return this.ask.values();
    }

    /**
     * Returns a name of the book.
     *
     * @return - the name.
     */
    @Override
    public String getBookName() {
        return this.bookName;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderBook)) {
            return false;
        }

        OrderBook<?> orderBook = (OrderBook<?>) o;

        if (bid != null ? !bid.equals(orderBook.bid) : orderBook.bid != null) {
            return false;
        }
        if (ask != null ? !ask.equals(orderBook.ask) : orderBook.ask != null) {
            return false;
        }
        return bookName != null ? bookName.equals(orderBook.bookName) : orderBook.bookName == null;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (ask != null ? ask.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        return result;
    }
}
