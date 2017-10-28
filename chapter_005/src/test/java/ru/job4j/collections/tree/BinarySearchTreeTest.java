package ru.job4j.collections.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;

public class BinarySearchTreeTest {

    /**
     * Tests add(E element).
     */
    @Test
    public void whenAddElementToTreeShouldInTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";

        tree.add(element1);
        tree.add(element2);
        tree.add(element3);
        Iterator<String> iterator = tree.iterator();

        Assert.assertThat(iterator.next(), is(element1));
        Assert.assertThat(iterator.next(), is(element2));
        Assert.assertThat(iterator.next(), is(element3));
    }

    /**
     * Tests Iterator, hasNext() with normal mode.
     */
    @Test
    public void whenHasNextThenReturnTrue() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        String parent = "Parent";
        String child = "Child";

        tree.add(parent);
        tree.add(child);
        Iterator<String> iterator = tree.iterator();

        Assert.assertThat(iterator.hasNext(), is(true));
        iterator.next();
        Assert.assertThat(iterator.hasNext(), is(true));
    }

    /**
     * Tests Iterator, hasNext() without elements.
     */
    @Test
    public void whenHasNotNextThenReturnFalse() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        Iterator<String> iterator = tree.iterator();

        Assert.assertThat(iterator.hasNext(), is(false));
    }
}