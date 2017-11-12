package ru.job4j.collections.orderbook;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class OrderBookLine.
 */
public class OrderBookLineTest {

    /**
     * Tests hasNext().
     */
    @Test
    public void whenBookHasLineThenGetString() {
        TradingOperationsBook book = new OrderBook("book1");
        book.addOrder(new Order(Status.BUY, 1, 10.2, 5));
        book.addOrder(new Order(Status.SELL, 2, 12.0, 12));

        OrderBookLine lineReader = new OrderBookLine(book);

        MatcherAssert.assertThat(lineReader.hasNext(), is(true));
        MatcherAssert.assertThat(lineReader.next(), is("5@10.2 - 12@12.0"));
    }

    /**
     * Tests hasNext().
     */
    @Test
    public void whenBookHasNotNextLineThenGetFalse() {
        TradingOperationsBook book = new OrderBook("book1");
        book.addOrder(new Order(Status.BUY, 1, 10.2, 5));

        OrderBookLine lineReader = new OrderBookLine(book);

        lineReader.next();
        MatcherAssert.assertThat(lineReader.hasNext(), is(false));
    }

    /**
     * Tests next() without next elements.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenBookHasNotNextOrdersThenException() {
        TradingOperationsBook book = new OrderBook("book1");
        book.addOrder(new Order(Status.BUY, 1, 10.2, 5));
        OrderBookLine lineReader = new OrderBookLine(book);

        lineReader.next();
        lineReader.next();
    }

    /**
     * Tests next(), when next line contains only one order.
     */
    @Test
    public void whenOnlyBuyOrderThenSellIsChangedToDashes() {
        TradingOperationsBook book = new OrderBook("book1");
        book.addOrder(new Order(Status.BUY, 1, 10.2, 5));
        OrderBookLine lineReader = new OrderBookLine(book);

        MatcherAssert.assertThat(lineReader.next(), is("5@10.2 - ---------"));
    }

    /**
     * Tests next(), when next line contains only one order.
     */
    @Test
    public void whenOnlySellOrderThenBuyIsChangedToDashes() {
        TradingOperationsBook book = new OrderBook("book1");
        book.addOrder(new Order(Status.SELL, 1, 10.2, 5));
        OrderBookLine lineReader = new OrderBookLine(book);

        MatcherAssert.assertThat(lineReader.next(), is("--------- - 5@10.2"));
    }
}