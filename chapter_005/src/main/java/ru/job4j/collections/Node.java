package ru.job4j.collections;

/**
 * Created by pacman on 21.09.17.
 * The Node.
 * @param <E> - an universal type.
 */
public class Node<E> {
    /**
     * The element of the node.
     */
    E element;

    /**
     * Default constructor.
     * @param element - element.
     */
    public Node(E element) {
        this.element = element;
    }

    /**
     * A next node.
     */
    Node<E> next;
}
