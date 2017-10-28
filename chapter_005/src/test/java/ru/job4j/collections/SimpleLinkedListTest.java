package ru.job4j.collections;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 13.09.17.
 * Tests the class SimpleLinkedList.
 */
public class SimpleLinkedListTest {

    /**
     * Test add().
     */
    @Test
    public void whenAddItemThenListContainsItem() {
        Integer elem = new Integer(1);
        Integer elem2 = new Integer(2);
        int indexInList = 1;
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.add(elem);
        list.add(elem2);

        assertThat(list.get(indexInList), is(elem2));
    }

    /**
     * Tests get(), with exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIncorrectIndexThenException() {
        Integer elem = new Integer(1);
        int incorrectIndex = 1;
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(elem);

        list.get(incorrectIndex);
    }

    /**
     * Tests iterator().
     */
    @Test
    public void whenIteratorThenReturnsIteratorOfList() {
        Integer elem1 = new Integer(1);
        Integer elem2 = new Integer(2);
        Integer elem3 = new Integer(3);
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();

        list.add(elem1);
        list.add(elem2);
        list.add(elem3);

        Iterator<Integer> iterator = list.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(elem1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(elem2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(elem3));
        assertThat(iterator.hasNext(), is(false));
    }
}