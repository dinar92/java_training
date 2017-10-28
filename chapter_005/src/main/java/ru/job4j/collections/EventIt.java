package ru.job4j.collectionsPro;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 06.09.17.
 * Iterate onle event numbers from the array.
 */
public class EventIt implements Iterator {

    /**
     * The array of numbers.
     */
    private final int[] array;

    /**
     * The current index.
     */
    private int index = 0;

    /**
     * Sets thr array of numbers.
     *
     * @param array the array.
     */
    public EventIt(final int[] array) {
        this.array = this.getEventArray(array);
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        if (this.index == this.array.length) {
            throw new NoSuchElementException();
        }
        return this.array[this.index++];
    }

    /**
     * Returns the event array.
     *
     * @param array - the array of numbers.
     * @return the array.
     */
    private int[] getEventArray(final int[] array) {
        int eventCounter = 0;
        for (int elem : array) {
            if ((elem % 2) == 0) {
                eventCounter++;
            }
        }
        int[] eventArray = new int[eventCounter];
        int j = 0;
        for (int i = 0; i < eventArray.length; i++) {
            while (j < array.length) {
                if ((array[j] % 2) == 0) {
                    eventArray[i] = array[j++];
                    break;
                }
                j++;
            }
        }
        return eventArray;
    }
}
