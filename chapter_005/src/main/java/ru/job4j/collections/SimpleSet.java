package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 21.09.17.
 * The simple Set implementation.
 *
 * @param <E> - an universal type.
 */
public class SimpleSet<E> implements Iterable {

    /**
     * A store.
     */
    private Object[] store = new Object[100];

    /**
     * An index of the last empty position in the store.
     */
    private int index = 0;

    /**
     * Adds element to the store.
     *
     * @param element - an element.
     */
    public void add(E element) {
        if (!contains(element)) {
            store[index++] = element;
        }
    }

    /**
     * Checks, that the element already exists in the array.
     *
     * @param element - the element.
     * @return true - if exist, false - not exist.
     */
    public boolean contains(E element) {
        boolean result = false;
        for (Object object : store) {
            if (element.equals(object)) {
                result = true;
                break;
            }
        }
        return result;
    }


    /**
     * Returns an instance of the iterator of this set.
     *
     * @return - an iterator.
     */
    public Iterator<E> iterator() {
        return new SimpleSetIterator<E>();
    }

    /**
     * The iterator of the Set.
     *
     * @param <E> - an universal type.
     */
    private class SimpleSetIterator<E> implements Iterator<E> {

        /**
         * An index of the last empty position of the iterator.
         */
        private int index = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return SimpleSet.this.store[this.index] != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            E result;
            try {
                result = (E) SimpleSet.this.store[this.index];
                if (result == null) {
                    throw new NoSuchElementException();
                } else {
                    this.index++;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}
