package ru.job4j.menu;

/**
 * Created by pacman on 17.05.17.
 * Generates, when the item isn't exist.
 */
public class NotFoundItemException extends Exception {

    /**
     * The message of error.
     * @return message.
     */
    public String toString() {
        return "Not found item with such identifier!";
    }
}
