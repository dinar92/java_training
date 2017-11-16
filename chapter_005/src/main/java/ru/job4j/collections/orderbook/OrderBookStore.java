package ru.job4j.collections.orderbook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The store of order books.
 * @param <B> - the unified book.
 * @param <O> - the unified order.
 */
public class OrderBookStore<B extends Book, O extends Order> implements BookStore<B, O>, Iterable<B> {

    /**
     * The list based store.
     */
    private final Map<String, B> store = new HashMap<>();

    /**
     * Adds the book to the store.
     * @param book - the book.
     */
    @Override
    public void addBook(B book) {
        this.store.put(book.getBookName(), book);
    }

    /**
     * Checks for content in the store of books.
     * @param book - the book.
     * @return - true - if contains, false - else.
     */
    @Override
    public boolean contains(B book) {
        return this.store.containsValue(book);
    }

    /**
     * Returns a book by his name.
     * Returns null if a book not found.
     * @param bookName - the name of the book.
     * @return - the book, or null if a book not found.
     */
    @Override
    public B getBookByName(String bookName) {
        return this.store.get(bookName);
    }

    /**
     * Removes the book from the store.
     * @param book - the book.
     */
    @Override
    public void deleteBook(B book) {
        this.store.remove(book);
    }

    /**
     * Adds new order to the specified book.
     * @param book - the book.
     * @param order - the order.
     */
    @Override
    public void addOrderToBook(B book, O order) {
        this.store.get(book.getBookName()).addOrder(order);
    }

    /**
     * Checks for content the order in the store.
     * @param order - the order.
     * @return - true - if contains, false - else.
     */
    @Override
    public boolean containsInBooks(O order) {
        boolean result = false;
        for (Map.Entry iterator : this.store.entrySet()) {
            B book = (B) iterator.getValue();
            if (book.contains(order)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes the order from the specified book.
     * @param book - the book.
     * @param order - the order.
     */
    @Override
    public void deleteOrderFromBook(B book, O order) {
        this.store.get(book.getBookName()).deleteOrder(order);
    }

    /**
     * The iterator of the store.
     * @return - the iterator.
     */
    @Override
    public Iterator<B> iterator() {
        return this.store.values().iterator();
    }
}
