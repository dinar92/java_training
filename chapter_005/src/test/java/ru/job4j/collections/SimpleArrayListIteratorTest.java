package ru.job4j.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 11.09.17.
 * Tests the class SimpleArrayListIterator.
 */
public class SimpleArrayListIteratorTest {

    /**
     * The instance of the two-dimensional array.
     */
    private final String[] array = {"1", "4"};

    /**
     * Tests the hasNext(),after next twice.
     */
    @Test
    public void whenHasNextThenReturnFalse() {
        SimpleArrayListIterator<String> itArray = new SimpleArrayListIterator(array);
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
        SimpleArrayListIterator<String> itArray = new SimpleArrayListIterator(array);
        String firstElement = "1";
        String secondElement = "4";
        Assert.assertThat(itArray.next(), is(firstElement));
        Assert.assertThat(itArray.next(), is(secondElement));
    }

    /**
     * Tests next(), when elements is over - throws Exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsOverShouldException() {
        SimpleArrayListIterator<String> itArray = new SimpleArrayListIterator(array);

        while (itArray.hasNext()) {
            itArray.next();
        }

        itArray.next();
    }

    /**
     * Tests next(), when elements is null - throws Exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsNullShouldException() {
        String[] array = {null};
        SimpleArrayListIterator<String> itArray = new SimpleArrayListIterator(array);

        itArray.next();
    }
}
