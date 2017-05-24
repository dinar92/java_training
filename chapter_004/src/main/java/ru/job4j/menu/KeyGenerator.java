package ru.job4j.menu;

/**
 * Created by pacman on 11.05.17.
 * Generates unique sequence of identifiers.
 */
public interface KeyGenerator {

    /**
     * Generates the next identifier in order each time it is invoked.
     * @return generated identifier.
     */
    int getNextKey();

    /**
     * Resets to initial value.
     */
    void reset();

}
