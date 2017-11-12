package ru.job4j.collections.orderbook;

import java.util.Collection;

/**
 * Book to store all possible transactions for purchase and sale.
 *
 * The provider provides an opportunity to receive collections of buys (BID) and sales(ASK).
 *
 * @param <O> - the order.
 */
public interface TradingOperationsBook<O extends Order> extends Book<O> {

    /**
     * The getter of buys as collection of orders.
     *
     * @return - the collection of orders.
     */
    Collection<O> getBIDOrders();

    /**
     * The getter of sells as collection of orders.
     *
     * @return - the collection of orders.
     */
    Collection<O> getASKOrders();
}
