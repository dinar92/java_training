package ru.job4j.collectionsPro;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 06.09.17.
 * Tests the class PrimeIt.
 */
public class PrimeItTest {

    /**
     * The instance of the two-dimensional array.
     */
    private final int[] array = {1, 4, 5, 6, 1};

    /**
     * Tests the hasNext(),after next twice.
     */
    @Test
    public void whenHasNextThenReturnFalse() {
        PrimeIt itArray = new PrimeIt(array);
        boolean expectResult = false;
        itArray.next();

        Assert.assertThat(itArray.hasNext(), is(expectResult));
    }

    /**
     * Tests next(), in the normal mode.
     */
    @Test
    public void whenGetNextShouldReturnsAllElements() {
        PrimeIt itArray = new PrimeIt(array);
        int prime = 5;
        Assert.assertThat(itArray.next(), is(prime));
    }

    /**
     * Tests next(), when elements is over.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsOverShouldException() {
        PrimeIt itArray = new PrimeIt(array);

        while (itArray.hasNext()) {
            itArray.next();
        }

        itArray.next();
    }
}
