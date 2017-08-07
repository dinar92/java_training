package ru.job4j.boardGame;

/**
 * Created by pacman on 10.07.17.
 * The game engine for table games.
 */
public interface GameEngine {

    /**
     * Starts the bypass of game stages.
     */
    void start();

    /**
     * Initializes default game stages.
     *
     * @return instance of self.
     */
    GameEngine init();
}
