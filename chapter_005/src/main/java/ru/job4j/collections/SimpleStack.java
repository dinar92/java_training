package ru.job4j.collections;

/**
 * Created by pacman on 18.09.17.
 * The implementation of a simple stack.
 *
 * @param <E> - the universal type.
 */
public class SimpleStack<E> {

    /**
     * The base store for stack elements.
     */
    private SimpleLinkedList<E> list = new SimpleLinkedList<>();

    /**
     * The counter of elements in the stack.
     */
    private int elementsCounter = 0;

    /**
     * Returns a last element from the stack and
     * removes it. Creates a new SimpleLinkedList for cutting
     * size of the stack.
     *
     * @return a last element, or null if stack is empty.
     */
    public E poll() {
        SimpleLinkedList<E> newList;
        int innerCounter = 0;
        E forReturn = null;

        if (this.elementsCounter != 0) {
            newList = new SimpleLinkedList<>();
            while (innerCounter < (elementsCounter - 1)) {
                newList.add(list.get(innerCounter++));
            }
            forReturn = list.get(--elementsCounter);
            list = newList;
        }
        return forReturn;
    }

    /**
     * Pushes the element to end of the stack.
     *
     * @param object - an element.
     */
    public void push(E object) {
        this.list.add(object);
        this.elementsCounter++;
    }

}
