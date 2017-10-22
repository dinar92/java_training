package ru.job4j.collectionsPro.treeCollection;

/**
 * An interface for simple tree implementations.
 * @param <E> - an universal comparable type.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Adds a child-element to parent-element.
     * @param parent - a parent.
     * @param child - a child.
     * @return success of action.
     */
    boolean add(E parent, E child);
}
