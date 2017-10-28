package ru.job4j.collections.mapCollection;

import java.util.Calendar;

/**
 * An user implementation.
 */
public class User {

    /**
     * User's name.
     */
    String name;

    /**
     * A count of user's children.
     */
    int children;

    /**
     * A user's birthday.
     */
    Calendar birthday;

    /**
     *  Returns a hash code for this user.
     * @return a hashcode value.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + children;
        result = 31 * result + birthday.hashCode();
        return result;
    }

    /**
     * Default constructor.
     * @param name name.
     * @param children children.
     * @param birthday birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Compares this user with another object.
     * @param object an another object.
     * @return true if the given object represents
     * equivalent to this user, otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;            // если объект равен самому себе - true.
        }
        if (!(object instanceof User)) {
            return false;           // если объект не относится к типу User или является null - false.
        }
        User user = (User) object;
        return (name.equals(user.name)) && (children == user.children) && (birthday.equals(user.birthday)); //сравнение значимых полей объектов.
    }
}
