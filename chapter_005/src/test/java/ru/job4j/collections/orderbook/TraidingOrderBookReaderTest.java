package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class TraidingOrderBookReader.
 */
public class TraidingOrderBookReaderTest {

    /**
     * Tests getContent().
     */
    @Test
    public void whenGetContentThenReturnsBooksContentAsString() {
        TradingOperationsBook book = new OrderBook("book1");
        Order order = new Order(Status.BUY, 1, 12.5, 5);
        Order order2 = new Order(Status.SELL, 2, 54.2, 2);
        StringJoiner content = new StringJoiner("\n")
                .add("Order book: ${book1}")
                .add("    BID       ASK")
                .add("Volume@Price  Volume@Price")
                .add("5@12.5 - 2@54.2");
        book.addOrder(order);
        book.addOrder(order2);

        TraidingOrderBookReader reader = new TraidingOrderBookReader();
        reader.setBook(book);

        MatcherAssert.assertThat(reader.getContent(), is(content.toString()));
    }
}