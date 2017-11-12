package ru.job4j.collections.orderbook;

import com.sun.javafx.binding.StringFormatter;

import java.util.StringJoiner;

/**
 * Reads all lines from the book and returns its content as a strings.
 */
public class TraidingOrderBookReader {

    /**
     * The instance of the book.
     */
    private TradingOperationsBook book;

    /**
     * Sets the book.
     * @param book - the book.
     */
    public void setBook(TradingOperationsBook book) {
        this.book = book;
    }

    /**
     * Returns the title of the book as a book's name and order's columns.
     * @return - the title.
     */
    private String getTitle() {
        String title = StringFormatter.format("Order book: ${%s}", this.book.getBookName()).getValue();
        return new StringJoiner("\n")
                .add(title)
                .add("    BID       ASK")
                .add("Volume@Price  Volume@Price").toString();

    }

    /**
     * Returns content of the book.
     *
     * @return - content.
     */
    public String getContent() {
        StringJoiner joiner = new StringJoiner("\n");
        OrderBookLine lineIterator = new OrderBookLine(this.book);
        joiner.add(this.getTitle());
        for (OrderBookLine it = lineIterator; it.hasNext();) {
            joiner.add(it.next());
        }
        return joiner.toString();
    }
}
