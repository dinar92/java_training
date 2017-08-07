package ru.job4j.tic_tac_toe.exception;

/**
 * Created by pacman on 12.07.17.
 * Range matching error.
 */
public class OutOfRangeException extends Exception {

    /**
     * The min limit.
     */
    private final int min;

    /**
     * The max limit.
     */
    private final int max;

    /**
     * Sets the min and max limits.
     *
     * @param min min limit inclusive.
     * @param max max limit inclusive.
     */
    public OutOfRangeException(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * The error message.
     *
     * @return message.
     */
    public String toString() {
        return "Your choice is out of range " + this.min + " - " + this.max;
    }
}
