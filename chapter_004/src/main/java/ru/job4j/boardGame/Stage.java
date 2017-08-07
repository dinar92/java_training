package ru.job4j.boardGame;

/**
 * Created by pacman on 10.07.17.
 * The stage of the game.
 */
public interface Stage {

    /**
     * Shows info about the current status of the game.
     */
    void showInfo();

    /**
     * The response on player's enter.
     */
    void action();
}
