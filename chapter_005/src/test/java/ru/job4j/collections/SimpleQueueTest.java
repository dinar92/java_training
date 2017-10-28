package ru.job4j.collectionsPro;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 20.09.17.
 * Tests the class SimpleQueue.
 */
public class SimpleQueueTest {

    /**
     * Tests push(), poll().
     */
    @Test
    public void whenFirstInItemThenFirstOutItem() {
        SimpleQueue<String> stack = new SimpleQueue<>();
        String item1 = "item1";
        String item2 = "item2";
        String item3 = "item3";

        stack.push(item1);
        stack.push(item2);
        stack.push(item3);

        assertThat(stack.poll(), is(item1));
        assertThat(stack.poll(), is(item2));
        assertThat(stack.poll(), is(item3));
    }

    /**
     * Tests that poll() returns null if the stack is empty.
     */
    @Test
    public void whenStackIsEmptyThenPollNull() {

        assertThat(new SimpleQueue<String>().poll(), is(CoreMatchers.nullValue()));
    }
}
