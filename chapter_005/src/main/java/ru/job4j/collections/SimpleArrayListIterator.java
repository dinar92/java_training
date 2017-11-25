package ru.job4j.collections;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 11.09.17.
 * The iterator for SimpleArrayList.
 *
 * @param <E> the universal type.
 */
@ThreadSafe
public class SimpleArrayListIterator<E> implements Iterator {

    /**
     * The array.
     */
    @GuardedBy("this") private final E[] array;

    /**
     * The current index of the array.
     */
    @GuardedBy("this")private int index = 0;

    /**
     * Sets the array with E type.
     *
     * @param array - the array.
     */
    public SimpleArrayListIterator(E[] array) {
        this.array = array;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public synchronized boolean hasNext() {
        boolean result = true;
        try {
            if (this.array[index] == null) {
                result = false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public synchronized E next() {
        Object result;
        try {
            result = this.array[index];
            if (result == null) {
                throw new NoSuchElementException();
            } else {
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException();
        }
        return (E) result;
    }
}