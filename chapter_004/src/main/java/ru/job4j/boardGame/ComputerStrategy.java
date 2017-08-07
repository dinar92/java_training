package ru.job4j.boardGame;

/**
 * Created by pacman on 17.07.17.
 * The strategy of the robot playing for tic-tac-toe.
 */
public interface ComputerStrategy {

    /**
     * Returns the cell's coordinate for moving.
     * @return coordinate.
     */
    String getMove();

}
