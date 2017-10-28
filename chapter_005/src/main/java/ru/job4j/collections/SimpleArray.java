package ru.job4j.collectionsPro;

/**
 * Created by user on 09.09.17.
 * The implementation of the array.
 * @param <T> - the universal type.
 */
public class SimpleArray<T> {

    /**
     * The array.
     */
    private Object[] array;

    /**
     * The index of the current element.
     */
    private int index = 0;

    /**
     * Sets the size of the array.
     * @param size the size.
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Adds the T type element to the array.
     * @param element the T type element.
     */
    public void add(T element) {
        this.array[index++] = element;
    }

    /**
     * Updates the element with the specified index to the new element.
     * @param index the index of the element.
     * @param element the new element.
     */
    public void update(int index, T element) {
        this.array[index] = element;
    }

    /**
     * Removes the element with specified index.
     * @param index the index.
     */
    public void delete(int index) {
        for (int i = index; i < this.array.length; i++) {
            if ((i + 1) == this.array.length) {
                this.array[i] = null;
            } else {
                this.array[i] = this.array[i + 1];
            }
        }
    }

    /**
     * Returns the element with the specified index.
     * @param index the index of the element.
     * @return the element.
     */
    public T get(int index) {
        return (T) this.array[index];
    }
}
