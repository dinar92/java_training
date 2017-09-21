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
    private final int[] array;

    /**
     * The count of the current element in the array.
     */
    private int currentElem = 0;

    /**
     * The total count of elements in the array.
     */
    private final int elementsInArray;

    /**
     * Sets an instance of an one-dimensional array and
     * a total count of items in the array.
     *
     * @param array - one-dimensional array.
     */
    public TwoDimensionalArray(int[][] array) {
        this.elementsInArray = this.getAllCount(array);
        this.array = this.getOneDimensionalArray(array);
    }

    /**
     * Returns the count of total elements in the array.
     * @param array  - an array.
     *
     * @return the count.
     */
    private int getAllCount(int[][] array) {
        int count = 0;
        for (int[] line : array) {
            for (int elem : line) {
                count++;
            }
        }
        return count;
    }

    /**
     * Converts an instance of two-dimensional array to
     * an one-dimensional array.
     *
     * @param array - a two-dimensional array of ints.
     * @return - an one dimensional array.
     */
    private int[] getOneDimensionalArray(int[][] array) {
        int[] result = new int[this.getAllCount(array)];
        int counterOfItemsInResultArray = 0;

        for (int[] line : array) {
            for (int item : line) {
                result[counterOfItemsInResultArray] = item;
                counterOfItemsInResultArray++;
            }
        }
        return result;
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
        int result = 0;
        try {
            result = this.array[this.currentElem++];
        } catch (IndexOutOfBoundsException ex) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
