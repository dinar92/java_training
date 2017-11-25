package ru.job4j.collections;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Created by pacman on 11.09.17.
 * The simple implementation of the array list.
 * @param <E> - the object's type.
 */
@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * The array.
     */
    @GuardedBy("this") private Object[] array;

    /**
     * The current index of the array.
     */
    @GuardedBy("this") private int index = 0;

    /**
     * Sets the length of the initial array.
     *
     * @param capacity - length.
     */
    public SimpleArrayList(int capacity) {
        this.array = new Object[capacity];
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public synchronized Iterator iterator() {
        return new SimpleArrayListIterator<>(this.array);
    }

    /**
     * Adds the E type object to the array. Is dynamically extensible.
     * When overfilling expands two times.
     *
     * @param element - the E type object.
     */
    public synchronized void add(E element) {
        if (this.index == this.array.length) {
            this.expand();
        }
        this.array[index++] = element;
    }

    /**
     * Epands the current array two times.
     */
    private void expand() {
        Object[] newArray = new Object[this.array.length * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }

    /**
     * Returns the E type object with specified index.
     * @param index - the index.
     * @return - E type object.
     */
    public synchronized E get(int index) {
        return (E) this.array[index];
    }
}
