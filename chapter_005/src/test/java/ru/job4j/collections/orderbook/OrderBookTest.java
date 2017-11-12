package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class OrderBook.
 * */
public class OrderBookTest {

    /**
     * Tests addOrder(Order). Will be add orders with different price.
     */
    @Test
    public void whenAddOrdersThenTheyContainsInBook() {
        Book<Order> book = new OrderBook<>("Book");
        Order bidOrder = new Order(Status.BUY, 1, 10.0, 5);
        Order askOrder = new Order(Status.SELL, 2, 3.0, 5);

        book.addOrder(bidOrder);
        book.addOrder(askOrder);

        MatcherAssert.assertThat(book.contains(bidOrder), is(true));
        MatcherAssert.assertThat(book.contains(askOrder), is(true));
    }

    /**
     * Tests addOrder(Order). Will be add two orders with same price.
     */
    @Test
    public void whenAddOrdersWithSamePriceThenAddOneOrderWithSumOfVolume() {
        Book<Order> book = new OrderBook<>("Book");
        Order bidOrder = new Order(Status.BUY, 1, 10.0, 5);
        Order bidOrder2 = new Order(Status.BUY, 2, 10.0, 5);
        Order result = new Order(Status.BUY, 1, 10.0, 10);

        book.addOrder(bidOrder);
        book.addOrder(bidOrder2);

        MatcherAssert.assertThat(book.contains(result), is(true));
        MatcherAssert.assertThat(book.contains(bidOrder), is(true));
        MatcherAssert.assertThat(book.contains(bidOrder2), is(false));
    }

    /**
     * Tests contains(Order), checks the not contained variant.
     */
    @Test
    public void whenBookNotContainsOrderThenFalse() {
        Book<Order> book = new OrderBook<>("Book");
        Order bidOrder = new Order(Status.BUY, 1, 10.0, 5);
        Order bidOrder2 = new Order(Status.BUY, 2, 10.0, 5);

       book.addOrder(bidOrder);

        MatcherAssert.assertThat(book.contains(bidOrder2), is(false));
    }

    /**
     * Tests deleteOrder(Order).
     */
    @Test
    public void whenDeleteOrderThenHeIsNotInBook() {
        Book<Order> book = new OrderBook<>("Book");
        Order bidOrder = new Order(Status.BUY, 1, 10.0, 5);
        book.addOrder(bidOrder);

        book.deleteOrder(bidOrder);

        MatcherAssert.assertThat(book.contains(bidOrder), is(false));
    }

    /**
     * Tests getById(Integer).
     */
    @Test
    public void whenEnterIDThenReturnsOrder() {
        Integer id = 1;
        Book<Order> book = new OrderBook<>("Book");
        Order bidOrder = new Order(Status.BUY, id, 10.0, 5);
        book.addOrder(bidOrder);

        Order found = book.getById(id);

        MatcherAssert.assertThat(found, is(bidOrder));
    }

    /**
     * Tests equals(Book).
     */
    @Test
    public void whenEqualToOtherBookThenReturnsTrue() {
        Order bidOrder = new Order(Status.BUY, 5, 10.0, 5);
        OrderBook book1 = new OrderBook("book");
        OrderBook book2 = new OrderBook("book");
        book1.addOrder(bidOrder);
        book2.addOrder(bidOrder);

        MatcherAssert.assertThat(book1.equals(book2), is(true));
    }
}