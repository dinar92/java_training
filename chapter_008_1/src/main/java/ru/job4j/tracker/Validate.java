package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validate service of simple storage operations.
 */
public interface Validate<E> {
    /**
     * Adds the element to store.
     *
     * @param e - a new element.
     * @throws Exception - validation error.
     */
    void add(E e) throws Exception;

    /**
     * Updates element's data by ID.
     *
     * @param e - an updated element.
     * @throws Exception - validation error.
     */
    void update(E e) throws Exception;

    /**
     * Removes the element from store by ID.
     *
     * @param id - element's ID.
     * @throws Exception - validation error.
     */
    void delete(Integer id) throws Exception;

    /**
     * Returns List of elements.
     *
     * @return - a list of elements.
     * @throws Exception - validation error.
     */
    List<E> findAll() throws Exception;

    /**
     * Looks for a element from the repository by ID.
     *
     * @param id - ID.
     * @return  - the element found, null if not found.
     * @throws Exception - validation error.
     */
    E findById(Integer id) throws Exception;
}
