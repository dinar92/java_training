package ru.job4j.boardGame;

/**
 * Created by pacman on 09.07.17.
 * The cell for the table games.
 */
public interface Cell {
    /**
     * Returns thhe state of the cell.
     * @return state.
     */
    State getState();

    /**
     * Sets the state of the cell.
     * @param state state (Enum).
     */
    void setState(State state);

    /**
     * Returns the coordinate of a cell.
     *
     * @return coordinate.
     */
    String getCoord();
}
