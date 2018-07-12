package ru.job4j.tracker;

import java.util.*;
import java.util.function.Consumer;

/**
 * Validation service wrapper for MemoryStore.
 * The thread-safe singleton.
 */
public class ValidateService implements Validate {

    /**
     * A thread-safe instance of validator.
     */
    private static final Validate validate = new ValidateService();

    /**
     * The store of users.
     */
    private Store store = MemoryStore.getInstance();

    private ValidateService() {
    }

    /**
     * Gets instance of validator.
     *
     * @return - the validator.
     */
    public static Validate getInstance() {
        return validate;
    }

    /**
     * Adds the user to store.
     *
     * @param user - a new user.
     * @throws NullPointerException     - if user's value is null.
     * @throws IllegalArgumentException - if specified user already exists.
     */
    @Override
    public void add(User user) throws NullPointerException, IllegalArgumentException {
        if (user == null) {
            throw new NullPointerException("Null values not support");
        } else if (this.store.findById(user.getId()) != null) {
            throw new IllegalArgumentException("User with this ID already exists");
        }
        this.store.add(user);
    }

    /**
     * Updates user's data by ID.
     *
     * @param user - an updated user.
     * @throws NoSuchElementException - if a specified user is not found.
     * @throws NullPointerException   - if user is null or if user's ID is null.
     */
    @Override
    public void update(User user) throws NoSuchElementException, NullPointerException {
        if (user == null) {
            throw new NullPointerException();
        }
        if (hasAccess(user.getId())) {
            this.store.update(user);
        }
    }

    /**
     * Removes the user from store by ID.
     *
     * @param id - user's ID.
     * @throws NullPointerException   - if ID is null.
     * @throws NoSuchElementException - a user with such ID not found
     */
    @Override
    public void delete(Integer id) throws NoSuchElementException, NullPointerException {
        if (hasAccess(id)) {
            this.store.delete(id);
        }
    }

    /**
     * Returns list of users.
     *
     * @return - a list of users.
     */
    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }

    /**
     * Looks for a user from the repository by ID.
     *
     * @param id -ID.
     * @return - the user found, null if not found.
     * @throws Exception - validation error.
     */
    @Override
    public User findById(Integer id) {
        return (hasAccess(id)) ? this.store.findById(id) : null;
    }

    /**
     * Validates user's ID.
     *
     * @param id - ID.
     * @return - true if ID is correct, throws exception - otherwise.
     * @throws NullPointerException   - if ID is null.
     * @throws NoSuchElementException - a user with such ID not found.
     */
    private boolean hasAccess(final Integer id) throws NullPointerException, NoSuchElementException {
        if (id == null) {
            throw new NullPointerException("ID must not be null");
        } else if (store.findById(id) == null) {
            throw new NoSuchElementException("User with such ID not found");
        }
        return true;
    }
}
