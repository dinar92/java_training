package ru.job4j.collectionsPro;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The optimized set to fast insertion.
 *
 * @param <E> - the universal type.
 */
public class OptimizedSimpleSet<E extends Comparable> implements Iterable<E> {

    /**
     * The conditional size of the array.
     */
    private final int conditionalSize = 100;

    /**
     * A store.
     */
    private Object[] store = new Object[conditionalSize];

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
            int position = contains(element);
            if (position != -1) {
                System.arraycopy(store, position, store, position + 1, conditionalSize - (position + 1));
                store[position] = element;
                index++;
        }
    }

    /**
     * Checks, that the element already exists in the array
     * and returns the position to which you must to insert a new object,
     * or -1 if element already is exist in the store.
     *
     * @param element - the element.
     * @return - the position to which you must to insert a new object or -1.
     */
    private int contains(E element) {
        int result = -1;
        if (index == 0) {
            result = 0;
        } else {
            int lowSide = 0;
            int highSide = index - 1;
            int middle;
            while (true) {
                middle = lowSide + (highSide - lowSide) / 2;
                if (((E) element).compareTo((E) store[middle]) == 0) {
                    break;
                }
                if (((E) element).compareTo((E) store[middle]) > 0) {
                    lowSide = middle + 1;
                }
                if (((E) element).compareTo((E) store[middle]) < 0) {
                    highSide = middle - 1;
                }
                if (store[lowSide] == null || ((E) store[lowSide]).compareTo((E) element) > 0) {
                    result = lowSide;
                    break;
                }
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
        return new OptimizedSimpleSetIterator<E>();
    }

    /**
     * The iterator of the Set.
     *
     * @param <E> - an universal type.
     */
    private class OptimizedSimpleSetIterator<E> implements Iterator<E> {

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
            return OptimizedSimpleSet.this.store[this.index] != null;
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
                result = (E) OptimizedSimpleSet.this.store[this.index];
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
