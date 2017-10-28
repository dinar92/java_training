package ru.job4j.collections;

/**
 * Created by user on 09.09.17.
 * The base for the item implementation.
 */
public abstract class Base {
    /**
     * ID.
     */
    private String id = new String("");


    /**
     * Returns id.
     *
     * @return id.
     */
    public String getId() {

        return this.id;

    }


    /**
     * Sets id.
     *
     * @param id - the ID.
     */
    public void setId(String id) {

        this.id = id;

    }

}
