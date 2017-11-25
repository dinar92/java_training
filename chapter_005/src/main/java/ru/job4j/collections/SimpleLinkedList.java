package ru.job4j.collections;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 13.09.17.
 * The implementation of the simple linked list.
 * @param <E> - the universal type.
 */
@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * The link of the last item.
     */
    @GuardedBy("this") private Node<E> last;

    /**
     * The link of the first item.
     */
    @GuardedBy("this") private Node<E> first;

    /**
     * The counter of elements.
     */
    @GuardedBy("this") private int counter = 0;

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new SimpleLinkedListIterator<E>(this);
    }

    /**
     * Adds the new item to the linked list.
     * @param element - the new item.
     */
    public synchronized void add(E element) {
        if (this.first == null) {
            first = new Node<>(null, element, null);
            last = first;
        } else {
            this.last.next = new Node<>(this.last, element, null);
            this.last = this.last.next;
        }
        counter++;
    }

    /**
     * Returns the item with the specified index.
     * @param index - the index.
     * @return - the item.
     * @throws NoSuchElementException - if the item with that index is not exist.
     */
    public synchronized E get(int index) {
        if (counter == 0 || counter <= index) {
            throw new NoSuchElementException();
        }

        int current = 0;
        Node<E> node = first;
        while (current != index) {
            node = node.next;
            current++;
        }
        return node.element;
    }

    /**
     * The implementation of the node, which
     * contains information about the previous item,
     * next item and contains the current item.
     * @param <E> - the universal type.
     */
    private static class Node<E> {

        /**
         * The previous Node.
         */
        Node<E> previous;

        /**
         * The current item.
         */
        E element;

        /**
         * The next Node.
         */
        Node<E> next;

        /**
         * Sets the information about previous, current and next items.
         * @param previous - the previous item's Node.
         * @param element - the current item.
         * @param next - the next item's Node.
         */
        Node(Node<E> previous, E element, Node<E> next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }
    }
}
