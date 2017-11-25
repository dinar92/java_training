package ru.job4j.multithread;

/**
 * The implementation of user.
 */
public class User {

    /**
     * The user's ID.
     */
    private final int id;

    /**
     * Amount of the user.
     */
    private int amount;

    /**
     * Sets user's ID and amount.
     * @param id - user's ID.
     * @param amount - user's some amount.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Returns user's ID.
     * @return - ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns user's amount.
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets new amount.
     * @param amount - amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o - some object.
     * @return - true if equal, false - otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        return amount == user.amount;
    }

    /**
     * Returns a hash code value for the object.
     * @return - a hash code value.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result;
        return result;
    }
}
