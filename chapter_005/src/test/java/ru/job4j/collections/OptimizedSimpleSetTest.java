package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Tests class OptimizedSimpleSet.
 */
public class OptimizedSimpleSetTest {

    /**
     * Returns a OptimizedSimpleSet of specified strings.
     * @param args - the specified strings.
     * @return - the OptimizedSimpleSet.
     */
    private OptimizedSimpleSet getFilledSetWithStrings(String... args) {
        OptimizedSimpleSet<String> set = new OptimizedSimpleSet<>();
        for (String arg : args) {
            set.add(arg);
        }
        return set;
    }

    /**
     * Tests add().
     */
    @Test
    public void whenAddingContainedValueThenNothing() {
        OptimizedSimpleSet<Integer> set = new OptimizedSimpleSet<>();
        Integer one = 1;
        Integer two = 2;
        Integer three = 3;

        set.add(one);
        set.add(two);
        set.add(three);
        set.add(two);

        Iterator<Integer> iterator = set.iterator();

        assertThat(iterator.next(), is(one));
        assertThat(iterator.next(), is(two));
        assertThat(iterator.next(), is(three));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Tests next(), in the normal mode.
     */
    @Test
    public void whenGetNextShouldReturnsAllElements() {
        String firstElement = "1";
        String secondElement = "4";
        OptimizedSimpleSet<String> set = getFilledSetWithStrings(firstElement, secondElement);
        Iterator<String> iterator = set.iterator();
        assertThat(iterator.next(), is(firstElement));
        assertThat(iterator.next(), is(secondElement));
    }

    /**
     * Tests next(), when elements is over - throws Exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementsIsOverShouldException() {
        String firstElement = "1";
        String secondElement = "4";
        OptimizedSimpleSet<String> set = getFilledSetWithStrings(firstElement, secondElement);
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        iterator.next();
    }
}