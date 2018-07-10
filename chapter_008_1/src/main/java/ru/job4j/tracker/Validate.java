package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validate service of simple storage operations.
 */
public interface Validate {
    /**
     * Adds the user to store.
     *
     * @param user - a new user.
     * @throws Exception - validation error.
     */
    void add(User user) throws Exception;

    /**
     * Updates user's data by ID.
     *
     * @param user - an updated user.
     * @throws Exception - validation error.
     */
    void update(User user) throws Exception;

    /**
     * Removes the user from store by ID.
     *
     * @param id - user's ID.
     * @throws Exception - validation error.
     */
    void delete(Integer id) throws Exception;

    /**
     * Returns List of users.
     *
     * @return - a list of users.
     * @throws Exception - validation error.
     */
    List<User> findAll() throws Exception;

    /**
     * Looks for a user from the repository by ID.
     *
     * @param id -ID.
     * @return  - the user found, null if not found.
     * @throws Exception - validation error.
     */
    User findById(Integer id) throws Exception;
}
