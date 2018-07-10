package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A store of users.
 */
public interface Store {

    /**
     * Adds the user to store.
     *
     * @param user - a new user.
     */
    void add(User user);

    /**
     * Updates user's data by ID.
     *
     * @param user - an updated user.
     */
    void update(User user);

    /**
     * Removes the user from store by ID.
     *
     * @param id - user's ID.
     */
    void delete(Integer id);

    /**
     * Returns List of users.
     *
     * @return - a list of users.
     */
    List<User> findAll();

    /**
     * Looks for a user from the repository by ID.
     *
     * @param id -ID.
     * @return- the user found.
     */
    User findById(Integer id);
}
