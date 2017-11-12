package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class Manager.
 */
public class ManagerTest {

    /**
     * Tests leaveProfitableTrades().
     */
    @Test
    public void whenLeaveProfitableTradesThenRemovesUnprofitable() {
        OrderBookStore store = new OrderBookStore();
        Book book = new OrderBook("book1");
        store.addBook(book);
        Order bidOrder1 = new Order(Status.BUY, 1, 10.9, 5);
        Order bidOrder2 = new Order(Status.BUY, 2, 10.5, 12);
        Order askOrder1 = new Order(Status.SELL, 3, 10.6, 12);
        Order askOrder2 = new Order(Status.SELL, 4, 10.7, 1);
        store.addOrderToBook(book, bidOrder1);
        store.addOrderToBook(book, bidOrder2);
        store.addOrderToBook(book, askOrder1);
        store.addOrderToBook(book, askOrder2);

        Manager manager = new Manager();
        manager.setBook(store);
        manager.leaveProfitableTrades();

        MatcherAssert.assertThat(store.containsInBooks(bidOrder1), is(false));
        MatcherAssert.assertThat(store.containsInBooks(askOrder1), is(false));
        MatcherAssert.assertThat(store.containsInBooks(bidOrder2), is(true));
        MatcherAssert.assertThat(store.containsInBooks(askOrder2), is(true));
    }

}