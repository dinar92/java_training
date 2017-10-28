package ru.job4j.collectionsPro;

/**
 * Created by pacman on 20.09.17.
 * The simple implementation of the queue.
 *
 * @param <E> - an universal type.
 */
public class SimpleQueue<E> {

    /**
     * The base store for stack elements.
     */
    private SimpleLinkedList<E> list = new SimpleLinkedList<>();

    /**
     * The counter of elements in the stack.
     */
    private int elementsCounter = 0;

    /**
     * Returns a first element from the stack and
     * removes it. Creates a new SimpleLinkedList for cutting
     * size of the stack.
     *
     * @return a first element, or null if stack is empty.
     */
    public E poll() {
        SimpleLinkedList<E> newList;
        int innerCounter = 0;
        E forReturn = null;

        if (this.elementsCounter != 0) {
            newList = new SimpleLinkedList<>();
            forReturn = list.get(innerCounter++);
            while (innerCounter < elementsCounter) {
                newList.add(list.get(innerCounter++));
            }
            elementsCounter--;
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
