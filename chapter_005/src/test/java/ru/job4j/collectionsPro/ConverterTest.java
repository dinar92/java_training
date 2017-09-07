package ru.job4j.collectionsPro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 08.09.17.
 * Tests the class Converter.
 */
public class ConverterTest {

    /**
     * Returns the nested iterator.
     * @return - nested iterators.
     */
    private Iterator<Iterator<Integer>> getNestedIt() {
        return Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
    }

    /**
     * Tests convert(), next() function.
     */
    @Test
    public void whenItHasTwoInnerItShouldGetsOneIt() {
        Iterator<Integer> convert = new Converter().convert(this.getNestedIt());
        convert.next();
        int result = convert.next();
        Assert.assertThat(result, is(2));
    }

    /**
     * Tests convert(), hasNext() function.
     */
    @Test
    public void whenItHasTwoInnerItShouldGetsIsIn() {
        Iterator<Integer> convert = new Converter().convert(getNestedIt());
        boolean firstResult = true;
        boolean secondResult = false;

        convert.next();
        Assert.assertThat(convert.hasNext(), is(firstResult));

        convert.next();
        Assert.assertThat(convert.hasNext(), is(secondResult));
    }
}
