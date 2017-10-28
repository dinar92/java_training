package ru.job4j.collections;

/**
 * Created by user on 09.09.17.
 * The abstract class for implementation of the store od base objects.
 *
 * @param <T> the type of the base.
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * The initial size of the store.
     */
    private int initSize = 100;

    /**
     * The store.
     */
    private SimpleArray<T> store = new SimpleArray<>(initSize);

    /**
     * Adds a base to the store.
     *
     * @param base base.
     */
    @Override
    public void add(T base) {
        this.store.add(base);
    }

    /**
     * Updates the specified base to new base.
     *
     * @param oldObject the old base.
     * @param newObject the new base.
     */
    @Override
    public void update(T oldObject, T newObject) {
        int index = 0;
        while (store.get(index) != oldObject) {
            index++;
        }
        if (store.get(index) != null) {
            store.update(index, newObject);
        }
    }

    /**
     * Removes the specified base.
     *
     * @param object - will be remove.
     */
    @Override
    public void delete(T object) {
        int index = 0;
        while (store.get(index) != object) {
            index++;
        }
        store.delete(index);
    }

    /**
     * Check for the presence of an object in an array.
     *
     * @param object - the object.
     * @return result - true if contains, false - not contains.
     */
    @Override
    public boolean contains(T object) {
        boolean result = false;
        int index = 0;
        try {
            while (!result) {
                if (store.get(index) == object) {
                    result = true;
                    break;
                } else {
                    index++;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            result = false;
        }
        return result;
    }
}
