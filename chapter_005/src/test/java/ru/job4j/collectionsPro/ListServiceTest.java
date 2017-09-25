package ru.job4j.collectionsPro;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Created by pacman on 21.09.17.
 */
public class ListServiceTest {


    /**
     * Tests hasCycle().
     */
    @Test
    public void whenHasCycleThenTrue() {

        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = three;


        assertThat(new ListService().hasCycle(one), is(true));
    }

    /**
     * Tests hasCycle(), when is not cycle.
     */
    @Test
    public void whenHasNotCycleThenFalse() {

        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);

        one.next = two;
        two.next = three;

        assertThat(new ListService().hasCycle(one), is(false));

    }

}