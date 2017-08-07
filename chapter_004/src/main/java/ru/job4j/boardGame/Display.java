package ru.job4j.boardGame;

/**
 * Created by pacman on 09.07.17.
 * The display for the table games.
 */
public interface Display {

    /**
     * Shows the string with new line.
     *
     * @param message that will be show.
     */
    void showln(String message);

    /**
     * Shows the string.
     *
     * @param message string.
     */
    void show(String message);
}
