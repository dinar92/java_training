package ru.job4j.collectionsPro;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 06.09.17.
 * Returns any element from the two-dimensional array if ints.
 */
public class TwoDimensionalArray implements Iterator {

    /**
     * The instance of the two-dimensional array.
     */
    private final int[][] array;

    /**
     * The count of the current element in the array.
     */
    private int currentElem = 0;

    /**
     * The total count of elements in the array.
     */
    private final int elementsInArray;

    /**
     * Sets the instance if the array.
     *
     * @param array - the two-dimensional array of ints.
     */
    public TwoDimensionalArray(int[][] array) {
        this.array = array;
        this.elementsInArray = this.getAllCount();
    }

    /**
     * Returns the count of total elements in the array.
     *
     * @return the count.
     */
    private int getAllCount() {
        int count = 0;
        for (int[] line : this.array) {
            for (int elem : line) {
                count++;
            }
        }
        return count;
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
        return this.currentElem < this.elementsInArray;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        int innerCounter = 0;
        int result = 0;
        if (this.currentElem == this.elementsInArray) {
            throw new NoSuchElementException();
        }
        exit:
        for (int[] line : this.array) {
            for (int element : line) {
                if (innerCounter == currentElem) {
                    result = element;
                    currentElem++;
                    break exit;
                } else {
                    innerCounter++;
                }
            }
        }
        return result;
    }
}
