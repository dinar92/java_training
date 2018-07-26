package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A store of users.
 */
public interface Store<E> {

    /**
     * Adds an element to store.
     *
     * @param e - a new element.
     */
    void add(E e);

    /**
     * Updates element's data by ID.
     *
     * @param e - an updated element.
     */
    void update(E e);

    /**
     * Removes the element from store by ID.
     *
     * @param id - element's ID.
     */
    void delete(Integer id);

    /**
     * Returns List of elements.
     *
     * @return - a list of elements.
     */
    List<E> findAll();

    /**
     * Looks for a element from the repository by ID.
     *
     * @param id - ID.
     * @return - the element found.
     */
    E findById(Integer id);
}
