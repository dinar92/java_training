package ru.job4j.multithread.bomberman;

/**
 * Attempt to use a board that was not initialized.
 */
public class BoardIsNotInitException extends Exception {

    /**
     * The message of error.
     *
     * @return - the message.
     */
    @Override
    public String getMessage() {
        return "Board was not initialized";
    }
}
