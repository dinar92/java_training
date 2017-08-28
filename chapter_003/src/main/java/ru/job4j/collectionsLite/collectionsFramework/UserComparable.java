package ru.job4j.collectionsLite.collectionsFramework;

/**
 * Created by pacman on 27.08.17.
 * The comparable user.
 */
public class UserComparable implements Comparable<UserComparable> {

    /**
     * The user's name.
     */
    String name;

    /**
     * The user's age.
     */
    Integer age;

    /**
     * Default constructor.
     * @param name - user's name.
     * @param age - user's age.
     */
    public UserComparable(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @throws NullPointerException param equals to null.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(UserComparable o) {

        if (o != null) {
            return this.age.compareTo(o.age);
        }
        throw new NullPointerException();
    }
}
