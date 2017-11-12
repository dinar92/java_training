package ru.job4j.collections.orderbook;

/**
 * Book to store orders.
 *
 * @param <O> - an order.
 */
public interface Book<O extends Order> {

    /**
     * Adds the order in the book.
     *
     * @param order - the order.
     */
    void addOrder(O order);

    /**
     * Check for content in the book order.
     *
     * @param order - the order.
     * @return true - contains, false - not contains.
     */
    boolean contains(O order);

    /**
     * Removes the order from th book.
     *
     * @param order - the order.
     */
    void deleteOrder(O order);

    /**
     * Returns a name of the book.
     *
     * @return - the name.
     */
    String getBookName();

    /**
     * Returns an order with the specified ID.
     * Returns null if the order not found.
     *
     * @param id - ID.
     * @return - the order or null if not found.
     */
    O getById(Integer id);
}
