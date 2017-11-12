package ru.job4j.collections.orderbook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The store of order books.
 * @param <B> - the unified book.
 * @param <O> - the unified order.
 */
public class OrderBookStore<B extends Book, O extends Order> implements BookStore<B, O>, Iterable<B> {

    /**
     * The list based store.
     */
    private final List<B> store = new LinkedList<>();

    /**
     * Adds the book to the store.
     * @param book - the book.
     */
    @Override
    public void addBook(B book) {
        this.store.add(book);
    }

    /**
     * Checks for content in the store of books.
     * @param book - the book.
     * @return - true - if contains, false - else.
     */
    @Override
    public boolean contains(B book) {
        return this.store.contains(book);
    }

    /**
     * Returns a book by his name.
     * Returns null if a book not found.
     * @param bookName - the name of the book.
     * @return - the book, or null if a book not found.
     */
    @Override
    public B getBookByName(String bookName) {
        B result = null;
        for (B book : this) {
            if (book.getBookName().equals(bookName)) {
                result = book;
                break;
            }
        }
        return result;
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
        for (Book iterator : this.store) {
            if (iterator.equals(book)) {
                iterator.addOrder(order);
            }
        }
    }

    /**
     * Checks for content the order in the store.
     * @param order - the order.
     * @return - true - if contains, false - else.
     */
    @Override
    public boolean containsInBooks(O order) {
        boolean result = false;
        for (Book iterator : this.store) {
            if (iterator.contains(order)) {
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
        for (Book iterator : this.store) {
            if (iterator.equals(book)) {
                iterator.deleteOrder(order);
            }
        }
    }

    /**
     * The iterator of the store.
     * @return - the iterator.
     */
    @Override
    public Iterator<B> iterator() {
        return this.store.iterator();
    }
}
