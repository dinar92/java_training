package ru.job4j.formatting;


/**
 * Created by pacman on 12.06.17.
 * Throws, when one of the arguments equals to null.
 */
public class InvalidArgumentException extends Exception {

    /**
     * Returns message of exception.
     * @return message.
     */
    @Override
    public String toString() {
        return "Please, check arguments for equality to null!";
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return  the detail message string of this.
     */
    @Override
    public String getMessage() {
        return this.toString();
    }
}
