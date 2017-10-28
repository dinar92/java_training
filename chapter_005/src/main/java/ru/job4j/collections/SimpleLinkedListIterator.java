package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 13.09.17.
 * @param <E> - the universal type.
 *
 */
public class SimpleLinkedListIterator<E> implements Iterator<E> {

    /**
     * The instance of the linked list.
     */
    private SimpleLinkedList<E> list;

    /**
     * The index of the current element.
     */
    private int index = 0;

    /**
     * Sets the instance of the SimpleLinkedList<E>.
     * @param list - SimpleLinkedList<E>.
     */
    public SimpleLinkedListIterator(SimpleLinkedList<E> list) {
        this.list = list;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        try {
            list.get(index);
        } catch (NoSuchElementException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     */
    @Override
    public E next() {
        return list.get(index++);
    }
}
