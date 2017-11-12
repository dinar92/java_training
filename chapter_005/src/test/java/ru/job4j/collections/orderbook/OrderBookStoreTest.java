package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class OrderBookStore.
 */
public class OrderBookStoreTest {

    OrderBookStore store = new OrderBookStore();
    String nameOfTheBook = "book";
    Book book = new OrderBook(nameOfTheBook);
    Order order = new Order(Status.BUY, 2, 120.0, 23);

    /**
     * Tests addBook(Book).
     */
    @Test
    public void whenAddBookThenBookInStore() {

        store.addBook(book);

        MatcherAssert.assertThat(store.contains(book), is(true));
    }

    /**
     * Test getBookByName(String name).
     */
    @Test
    public void whenEnterNameThenReturnsBook() {


        store.addBook(book);

        MatcherAssert.assertThat(store.getBookByName(nameOfTheBook), is(book));
    }

    /**
     * Tests addOrderToBook(Book, Order).
     */
    @Test
    public void whenAddOrderToBookThenOrderInBook() {

        store.addBook(book);
        store.addOrderToBook(book, order);

        MatcherAssert.assertThat(book.contains(order), is(true));
    }

    /**
     * Tests containsInBooks(Order).
     */
    @Test
    public void whenOrderInStoreThenReturnTrue() {
        store.addBook(book);
        store.addOrderToBook(book, order);

        MatcherAssert.assertThat(store.containsInBooks(order), is(true));
    }

    /**
     * Tests deleteOrderFromBook(Book, Order).
     */
    @Test
    public void whenDeleteFromStoreThenNotContainsInStore() {
        store.addBook(book);
        store.addOrderToBook(book, order);
        store.deleteOrderFromBook(book, order);

        MatcherAssert.assertThat(store.containsInBooks(order), is(false));
    }
}