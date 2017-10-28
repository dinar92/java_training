package ru.job4j.collectionsPro;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 22.09.17.
 * The simple implementation of the linked set.
 * @param <E> - an universal type.
 */
public class SimpleLinkedSet<E> implements Iterable<E> {

    /**
     * A first node.
     */
    private Node<E> first;

    /**
     * A last node.
     */
    private Node<E> last;

    /**
     * Adds new Element to the set if it's unique.
     * @param element - an element.
     */
    public void add(E element) {
        if (!contains(element)) {
            if (first == null) {
                first = new Node<>(null, element, null);
                last = first;
            } else {
                last.next = new Node<>(last, element, null);
                last = last.next;
            }
        }
    }

    /**
     * Checks, that the element already exists in the array.
     *
     * @param element - the element.
     * @return true - if exist, false - not exist.
     */
    public boolean contains(E element) {
        boolean result = false;
        for (E item : this) {
            if (item.equals(element)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * A node.
     * @param <E> - an universal type.
     */
    private static class Node<E> {

        /**
         * a previous Node.
         */
        Node<E> previous;

        /**
         * An element.
         */
        E element;

        /**
         * A last Node.
         */
        Node<E> next;

        /**
         * Sets parameters.
         * @param previous - a previous node.
         * @param element - an element.
         * @param next - a last node.
         */
        Node(Node<E> previous, E element, Node<E> next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedSetIterator<>();
    }

    /**
     * The iterator of the Set.
     *
     * @param <E> - an universal type.
     */
    private class LinkedSetIterator<E> implements Iterator<E> {

        /**
         * Start position of the counter of elements from the set.
         */
        Node<E> current = (Node<E>) SimpleLinkedSet.this.first;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            E result;
            if (current != null) {
                result = current.element;
                current = current.next;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}
