package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;

/**
 * Created by pacman on 12.07.17.
 * Shows the message to the console.
 */
public class ConsoleDisplay implements Display {
    /**
     * Shows the string with new line.
     *
     * @param message that will be show.
     */
    @Override
    public void showln(String message) {
        System.out.println(message);
    }

    /**
     * Shows the string.
     *
     * @param message string.
     */
    public void show(String message) {
        System.out.print(message);
    }
}
