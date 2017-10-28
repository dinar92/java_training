package ru.job4j.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 06.09.17.
 * Tests the class EventIt.
 */
public class EventItTest {

    /**
     * The instance of the two-dimensional array.
     */
    private final int[] array = {1, 4, 5, 6, 1};

    /**
     * Tests the hasNext(),after next twice.
     */
    @Test
    public void whenHasNextThenReturnFalse() {
        EventIt itArray = new EventIt(array);
        boolean expectResult = false;
        itArray.next();
        itArray.next();

        Assert.assertThat(itArray.hasNext(), is(expectResult));
    }

    /**
     * Tests next(), in the normal mode.
     */
    @Test
    public void whenGetNextShouldReturnsAllElements() {
        EventIt itArray = new EventIt(array);
        int firstEventElement = 4;
        int secondEventElement = 6;
        Assert.assertThat(itArray.next(), is(firstEventElement));
        Assert.assertThat(itArray.next(), is(secondEventElement));
    }

    /**
     * Tests next(), when elements is over.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsOverShouldException() {
        EventIt itArray = new EventIt(array);

        while (itArray.hasNext()) {
            itArray.next();
        }

        itArray.next();
    }
}
