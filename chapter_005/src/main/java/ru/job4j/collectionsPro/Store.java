package ru.job4j.collectionsPro;

/**
 * Created by user on 09.09.17.
 * The store of bases.
 * @param <T> The type of the base.
 */
public interface Store<T extends Base> {

    /**
     * Adds the base.
     * @param base base.
     */
    void add(T base);

    /**
     * Updates the base.
     * @param oldObject the old base.
     * @param newObject the new base.
     */
    void update(T oldObject, T newObject);

    /**
     * Removes the base with specified object.
     * @param object - will be remove.
     */
    void delete(T object);

    /**
     * Check for the presence of an object in an array.
     * @param object - the object.
     * @return result - true if contains, false - not contains.
     */
    boolean contains(T object);
}
