package ru.job4j.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 06.09.17.
 * Tests the class TwoDimensionalArray.
 */
public class TwoDimensionalArrayTest {

    /**
     * The instance of the two-dimensional array.
     */
    private final int[][] array = {{1, 2},
            {3, 4}};

    /**
     * Tests the hasNext().
     */
    @Test
    public void whenHasNextThenReturnTrue() {
        TwoDimensionalArray itArray = new TwoDimensionalArray(array);
        boolean expectResult = true;
        itArray.next();
        itArray.next();
        itArray.next();

        Assert.assertThat(itArray.hasNext(), is(expectResult));
    }

    /**
     * Tests next(), in the normal mode.
     */
    @Test
    public void whenGetNextShouldReturnsAllElements() {
        TwoDimensionalArray itArray = new TwoDimensionalArray(array);
        int currentElement = 1;
        while (itArray.hasNext()) {
            Assert.assertThat(itArray.next(), is(currentElement++));
        }
    }

    /**
     * Tests next(), when elements is over.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsOverShouldException() {
        TwoDimensionalArray itArray = new TwoDimensionalArray(array);

        while (itArray.hasNext()) {
            itArray.next();
        }

        itArray.next();
    }
}
