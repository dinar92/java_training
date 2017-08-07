package ru.job4j.boardGame;

/**
 * Created by pacman on 09.07.17.
 * The player.
 */
public interface Player {

    /**
     * The player's moving.
     */
    void doMove();

    /**
     * Returns the state of the player.
     *
     * @return the current state.
     */
    State getState();
}
