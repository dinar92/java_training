package ru.job4j.collections.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class Tree.
 */
public class TreeTest {

    /**
     * Tests add(E parent, E child).
     */
    @Test
    public void whenAddElementToTreeShouldInTree() {
        SimpleTree<String> tree = new Tree<>();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";

        tree.add(element1, element2);
        tree.add(element2, element3);
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
        SimpleTree<String> tree = new Tree<>();
        String parent = "Parent";
        String child = "Child";

        tree.add(parent, child);
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
        SimpleTree<String> tree = new Tree<>();
        Iterator<String> iterator = tree.iterator();

        Assert.assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Test isBinary() - true.
     */
    @Test
    public void whenTreeIsBinaryThenTrue() {
        Tree<String> tree = new Tree<>();
        String element1 = "element1";
        String element2 = "element2";
        String element3 = "element3";
        String element4 = "element4";

        tree.add(element1, element2);
        tree.add(element2, element3);
        tree.add(element2, element4);

        Assert.assertThat(tree.isBinary(), is(true));
    }
}