package ru.job4j.collectionsPro;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by user on 09.09.17.
 * Tests the class SimpleArray.
 */
public class SimpleArrayTest {

    /**
     * Tests add().
     */
    @Test
    public void whenAddElementThenElementInArray() {
        int size = 1;
        String text = "test";
        SimpleArray<String> array = new SimpleArray<>(size);

        array.add(text);

        assertThat(array.get(0), is(text));
    }

    /**
     * Tests update().
     */
    @Test
    public void whenUpdateElementThenGetUpdatedElement() {
        int size = 1;
        Integer oldInt = new Integer(1);
        Integer newInt = new Integer(2);
        SimpleArray<Integer> array = new SimpleArray<>(size);
        array.add(oldInt);

        array.update(0, newInt);

        assertThat(array.get(0), is(newInt));
    }

    /**
     * Tests delete().
     */
    @Test
    public void whenDeleteElementThenRemovedFromArray() {
        int size = 2;
        int getIndex = 0;
        SimpleArray<Integer> array = new SimpleArray<>(size);
        Integer first = new Integer(1);
        Integer second = new Integer(2);
        array.add(first);
        array.add(second);

        Integer verifiable = array.get(getIndex);
        array.delete(getIndex);

        assertThat(array.get(getIndex), IsNot.not(verifiable));
    }
}