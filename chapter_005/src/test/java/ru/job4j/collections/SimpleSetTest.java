package ru.job4j.collections;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by pacman on 21.09.17.
 * Tests the class SimpleSet.
 */
public class SimpleSetTest {

    /**
     * Tests add().
     */
    @Test
    public void whenAddNewElementThenItInSet() {
        String element = "test1";
        SimpleSet<String> set = new SimpleSet<>();

        set.add(element);

        assertThat(set.contains(element), is(true));
    }

    /**
     * Tests add(), when adding the same elements.
     */
    @Test
    public void whenAddingTheSameThenItNotAdded() {
        String element = "test1";
        String element2 = "test1";
        SimpleSet<String> set = new SimpleSet<>();
        Iterator<String> iterator = set.iterator();
        set.add(element);
        set.add(element2);

        //contains only one element.
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(element));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Tests contains(), when element not exist in the set.
     */
    @Test
    public void whenElementNotInSetThenNotContains() {
        String element = "test1";
        String element2 = "Not exist";
        SimpleSet<String> set = new SimpleSet<>();

        set.add(element);

        assertThat(set.contains(element2), is(false));
    }

    /**
     * Tests next(), when set not contains any element.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementNotExistInSetThenException() {
        String element = "test1";
        SimpleSet<String> set = new SimpleSet<>();

        set.add(element);
        Iterator<String> iterator = set.iterator();

        iterator.next();
        iterator.next();
    }

    /**
     * Tests iterator.next().
     */
    @Test
    public void whenHasNextThenGetNextElementFromSet() {
        String element = "test1";
        String element2 = "test2";
        String element3 = "test3";
        SimpleSet<String> set = new SimpleSet<>();
        set.add(element);
        set.add(element2);
        set.add(element3);

        Iterator<String> iterator = set.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(element));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(element2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(element3));
    }
}