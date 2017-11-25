package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * The storage of users.
 * @param <U> - a type which extends from User.
 */
@ThreadSafe
public class UserStorage<U extends User> {

    /**
     * A base of the storage.
     * Key - user's ID.
     * Value - the user.
     */
    @GuardedBy("this") private final Map<Integer, U> store = new HashMap<>();

    /**
     * Adds user to the storage.
     * @param user - the new user.
     */
    public synchronized void addUser(U user) {
        this.store.put(user.getId(), user);
    }

    /**
     * Removes user from the storage.
     * @param user - the user.
     */
    public synchronized void deleteUser(U user) {
        this.store.remove(user.getId());
    }

    /**
     * Updates the user's state.
     * @param user - the user.
     */
    public synchronized void updateUser(U user) {
        this.store.replace(user.getId(), user);
    }

    /**
     * Returns the User with the specified ID.
     * @param id - user's ID.
     * @return - found User, null - if not found.
     */
    public U getUserById(int id) {
        return this.store.get(id);
    }

    /**
     * Transfers amount from the User with fromId to
     * the User with toId.
     * @param fromId - the ID of the User who deducts amount.
     * @param toId - the ID of the User to whom the amount is credited.
     * @param amount - operation's amount.
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        U from = this.store.get(fromId);
        U to = this.store.get(toId);
        if (isValid(from, amount)) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
        }
    }

    /**
     * Determines whether the user has enough funds to write-off.
     * @param from - the ID of the User who deducts amount.
     * @param amount - operation's amount.
     * @return - true if enough, false - otherwise.
     */
    private boolean isValid(U from, int amount) {
        return !(from.getAmount() - amount < 0);
    }
}
