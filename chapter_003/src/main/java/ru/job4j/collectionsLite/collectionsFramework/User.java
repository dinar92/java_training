package ru.job4j.collectionsLite.collectionsFramework;

/**
 * Just user.
 */
public class User {

    /**
     * User's ID.
     */
    Integer id;

    /**
     * User's name.
     */
    String name;

    /**
     * User's city.
     */
    String city;

    /**
     * Sets parameters.
     * @param id ID.
     * @param name name.
     * @param city city.
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

}
