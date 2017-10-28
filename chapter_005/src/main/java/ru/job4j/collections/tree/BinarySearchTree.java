package ru.job4j.collections.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A binary tree implementation.
 *
 * @param <E> - an universal type.
 */
public class BinarySearchTree<E extends Comparable> implements Iterable<E> {

    /**
     * A root of the tree.
     */
    private Node<E> root = new Node<>();

    @Override
    public Iterator<E> iterator() {
        return new BinaryTreeIterator<>();
    }

    private class BinaryTreeIterator<E> implements Iterator<E> {

        private final List<E> container = new LinkedList<>();
        private final Iterator<E> iterator;

        BinaryTreeIterator() {
            this.fillContainer(container, (Node<E>) BinarySearchTree.this.root);
            iterator = container.iterator();

        }

        private void fillContainer(List list, Node<E> root) {
            if (root != null && root.element != null) {
                fillContainer(list, root.leftChild);
                list.add(root.element);
                fillContainer(list, root.rightChild);
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next();
        }
    }

    /**
     * The knot of the knot's branch. Contains only two child and data.
     * The left child is equal or less than data, the right child is more than data.
     *
     * @param <E> - an universal type.
     */
    private class Node<E> {

        /**
         * Data.
         */
        E element;

        /**
         * The left child is equal or less than data.
         */
        Node<E> leftChild;

        /**
         * The right child is mre than data.
         */
        Node<E> rightChild;
    }

    /**
     * Adds new element to the tree.
     *
     * @param element - the new element.
     */
    public void add(E element) {
        this.findPositionForInsertion(element, root).element = element;
    }

    /**
     * Returns a knot which must contains the specified element.
     *
     * @param element - an element must to be added.
     * @param knot    - a start knot for searching.
     * @return - a node which must contains the element.
     */
    private Node<E> findPositionForInsertion(E element, Node<E> knot) {
        Node<E> result;
        if (knot == null) {             //потомок не инициализирован.
            knot = new Node<>();
            result = knot;
        } else if (knot.element == null) {  //первый элемент дерева.
            result = knot;
        } else if (element.compareTo(knot.element) > 0) {
            if (knot.rightChild == null) {
                knot.rightChild = findPositionForInsertion(element, knot.rightChild);
                result = knot.rightChild;
            } else {
                result = findPositionForInsertion(element, knot.rightChild);
            }
        } else {
            if (knot.leftChild == null) {
                knot.leftChild = findPositionForInsertion(element, knot.leftChild);
                result = knot.leftChild;
            } else {
                result = findPositionForInsertion(element, knot.leftChild);
            }
        }
        return result;
    }
}
