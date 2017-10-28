package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Created by pacman on 22.09.17.
 * Tests class SimpleLinkedSet.
 */
public class SimpleLinkedSetTest {

    /**
     * Tests add().
     */
    @Test
    public void whenAddNewElementThenItInSet() {
        String element = "test1";
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();

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
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
        set.add(element);
        set.add(element2);
        Iterator<String> iterator = set.iterator();

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
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();

        set.add(element);

        assertThat(set.contains(element2), is(false));
    }

    /**
     * Tests next(), when set not contains any element.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenElementNotExistInSetThenException() {
        String element = "test1";
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();

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
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
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