package ru.job4j.collections.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of the tree structure.
 *
 * @param <E> - an universal comparable type.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * A root of the tree.
     */
    private final Node<E> root = new Node<>();

    /**
     * Adds a child-element to parent-element.
     *
     * @param parent - a parent.
     * @param child  - a child.
     * @return success of action.
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> searchNode;
        if (root.element == null) {                   //if tree is empty, creates a root.
            root.element = parent;
            searchNode = root;
        } else {
            if (this.findNode(child, root) != null) {   //checks duplicates.
                return false;
            }
            searchNode = this.findNode(parent, root);
            if (searchNode == null) {  //verifies the existence of a parent.
                return false;
            }
        }
        Node<E> childToAdd = new Node<>();
        childToAdd.element = child;
        return searchNode.child.add(childToAdd);
    }

    /**
     * Searches for an element in the tree using recursion.
     * Returns the Node with found element or null if element not found.
     *
     * @param element     - an element to search for.
     * @param rootNode - an initial node for search.
     * @return - Node with element or null if not found.
     */
    private Node<E> findNode(E element, Node<E> rootNode) {
        Node<E> result = null;
        if (element.compareTo(rootNode.element) == 0) {
            result = rootNode;
        } else {
            for (Iterator<Node<E>> iterator = rootNode.child.iterator(); iterator.hasNext();) {
                result = findNode(element, iterator.next());
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Checks the Tree for binary.
     * @return true - if binary, false - not.
     */
    public boolean isBinary() {
        boolean result = true;
        for (Iterator<E> iterator = this.iterator(); iterator.hasNext();) {
            if (this.findNode(iterator.next(), this.root).child.size() > 2) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Iterator of the tree.
     *
     * @return - an iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>();
    }

    /**
     * Provides iteration of the tree.
     *
     * @param <E> - the universal type from outer class.
     */
    private class TreeIterator<E> implements Iterator<E> {

        /**
         * A container of all elements from the tree.
         */
        List<E> allElements = new LinkedList<>();

        /**
         * An iterator of the container of elements from the tree.
         */
        Iterator<E> iterator;

        /**
         * Fills the container with elements.
         * Sets his iterator.
         */
        TreeIterator() {
            collectToList(allElements, (Node<E>) Tree.this.root);
            iterator = allElements.iterator();
        }

        /**
         * Collects all elements of tree to specified list from the specified Node.
         * Uses recursion.
         *
         * @param list - the container for adding elements.
         * @param root - the root Node.
         */
        private void collectToList(List<E> list, Node<E> root) {
            if (root.element != null) {
                list.add(root.element);
                for (Iterator<Node<E>> iterator = root.child.iterator(); iterator.hasNext();) {
                    collectToList(list, iterator.next());
                }
            }
        }

        /**
         * Returns true if the iteration has more elements.
         * @return true - has more, false - hasn't more.
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         */
        @Override
        public E next() {
            return iterator.next();
        }
    }

    /**
     * A node of the tree. Contains the value and
     * list of child.
     * @param <E> - an universal type.
     */
    private class Node<E> {
        /**
         * An elment of the node.
         */
        E element;
        /**
         * List of child of the node.
         */
        List<Node<E>> child = new LinkedList<>();
    }
}
