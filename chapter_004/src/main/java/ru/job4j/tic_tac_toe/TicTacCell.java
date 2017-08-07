package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Cell;
import ru.job4j.boardGame.State;

/**
 * Created by pacman on 09.07.17.
 * The cell implementation of the cell for the tic-tac-toe game.
 */
public class TicTacCell implements Cell {

    /**
     * The state of the cell.
     */
    private State state;

    /**
     * The coordinate of the cell on a board.
     */
    private final String coord;

    /**
     * Sets default state and coordinate of the cell.
     *
     * @param state      state.
     * @param coordinate coordinate.
     */
    public TicTacCell(State state, String coordinate) {
        this.state = state;
        this.coord = coordinate;
    }

    /**
     * Returns thhe state of the cell.
     *
     * @return state.
     */
    @Override
    public State getState() {
        return this.state;
    }

    /**
     * Sets the state of the cell.
     *
     * @param state state (Enum).
     */
    @Override
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns the coordinate of a cell.
     *
     * @return coordinate.
     */
    @Override
    public String getCoord() {
        return this.coord;
    }

    /**
     * Returns the humanized state of the cell.
     *
     * @return a humanized state.
     */
    public String toString() {
        return this.state.toString();
    }
}
