package ru.job4j.collections.orderbook;

/**
 * The store of order books.
 * @param <B> - the unified book.
 * @param <O> - the unified order.
 */
public interface BookStore<B extends Book, O extends Order> {

    /**
     * Adds the book to the store.
     * @param book - the book.
     */
    void addBook(B book);

    /**
     * Checks for content in the store of books.
     * @param book - the book.
     * @return - true - if contains, false - else.
     */
    boolean contains(B book);

    /**
     * Returns a book by his name.
     * Returns null if a book not found.
     * @param bookName - the name of the book.
     * @return - the book, or null if a book not found.
     */
    B getBookByName(String bookName);

    /**
     * Removes the book from the store.
     * @param book - the book.
     */
    void deleteBook(B book);

    /**
     * Adds new order to the specified book.
     * @param book - the book.
     * @param order - the order.
     */
    void addOrderToBook(B book, O order);

    /**
     * Checks for content the order in the store.
     * @param order - the order.
     * @return - true - if contains, false - else.
     */
    boolean containsInBooks(O order);

    /**
     * Removes the order from the specified book.
     * @param book - the book.
     * @param order - the order.
     */
    void deleteOrderFromBook(B book, O order);
}
