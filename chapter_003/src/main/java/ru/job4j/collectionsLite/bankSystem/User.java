package ru.job4j.collectionsLite.bankSystem;

/**
 * Created by pacman on 02.09.17.
 * Implements the bank client.
 */
public class User {

    /**
     * User's name.
     */
    private final String name;

    /**
     * User's passport number.
     */
    private final int passport;

    /**
     * Sets user's name and passport data.
     *
     * @param name     user's name.
     * @param passport user's passport data.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Compare users.
     *
     * @param o the another user.
     * @return result of compare.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (passport != user.passport) {
            return false;
        }
        return name.equals(user.name);
    }

    /**
     * Returns the hashCode.
     *
     * @return the hashCode.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport;
        return result;
    }
}
