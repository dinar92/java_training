package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * A store of users.
 * A thread-safe singleton.
 */
public class MemoryStore implements Store {

    /**
     * A singleton instance of the store.
     */
    private static Store store = new MemoryStore();

    /**
     * A thread-safe storage of users.
     */
    private List<User> list = new CopyOnWriteArrayList<>();

    private MemoryStore() {
    }

    /**
     * Gets a singleton instance of store.
     *
     * @return - the instance of store.
     */
    public static Store getInstance() {
        return store;
    }

    /**
     * Adds the user to store.
     *
     * @param user - a new user.
     */
    @Override
    public void add(User user) {
        this.list.add(user);
    }

    /**
     * Updates user's data by ID.
     *
     * @param newUser - an updated user.
     */
    @Override
    public void update(User newUser) {
        for (int index = 0; index < this.list.size(); index++) {
            if (this.list.get(index).getId() == newUser.getId()) {
                this.list.set(index, newUser);
            }
        }

    }

    /**
     * Removes the user from store by ID.
     *
     * @param id - user's ID.
     */
    @Override
    public void delete(Integer id) {
        this.list.remove(this.findById(id));
    }

    /**
     * Returns List of users.
     *
     * @return - a list of users.
     */
    @Override
    public List<User> findAll() {
        return this.list;
    }

    /**
     * Looks for a user from the repository by ID.
     *
     * @param id -ID.
     * @return - the user found, null if not found.
     */
    @Override
    public User findById(Integer id) {
        User found = null;
        for (User user :
                this.list) {
            if (user.getId() == id) {
                found = user;
            }
        }
        return found;
    }
}
