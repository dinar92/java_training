package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 06.09.17.
 * Creates the array of the prime number.
 */
public class PrimeIt implements Iterator {

    /**
     * The array of the prime numbers.
     */
    private final int[] array;

    /**
     * The current index.
     */
    private int indexCounter = 0;

    /**
     * Sets the array.
     *
     * @param array - the array of numbers.
     */
    public PrimeIt(final int[] array) {
        this.array = getPrimeArray(array);
    }

    /**
     * Returns the prime array from the array of numbers.
     *
     * @param array the array of numbers.
     * @return the array of prime numbers.
     */
    private int[] getPrimeArray(int[] array) {
        int primeCounter = 0;
        for (int elem : array) {
            if (isPrime(elem)) {
                primeCounter++;
            }
        }
        int[] primeArray = new int[primeCounter];
        int index = 0;
        for (int i = 0; i < primeArray.length; i++) {
            while (index < array.length) {
                if (isPrime(array[index])) {
                    primeArray[i] = array[index++];
                    break;
                }
                index++;
            }
        }
        return primeArray;
    }

    /**
     * Checks for a prime number.
     *
     * @param num the number.
     * @return is prime, or not.
     */
    private boolean isPrime(int num) {
        boolean result = true;
        if (num < 2) {
            result = false;
        }
        for (int i = 2; i * i <= num; i++) {
            if ((num % i) == 0) {
                result = false;
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
        return this.indexCounter < this.array.length;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        if (this.indexCounter == this.array.length) {
            throw new NoSuchElementException();
        }
        return this.array[this.indexCounter++];
    }
}
