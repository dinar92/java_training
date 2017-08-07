package ru.job4j.boardGame;

/**
 * Created by pacman on 09.07.17.
 * The board for the table game.
 */
public interface GameBoard {

    /**
     * Creates the specified size board filled with emptiness.
     *
     * @param boardSize size.
     */
    void init(int boardSize);

    /**
     * Returns the size of the board.
     *
     * @return size.
     */
    int getSize();

    /**
     * Returns the state of the specified cell.
     *
     * @param coordinate of the cell.
     *                   @return state of the cell.
     * @throws IllegalArgumentException - such cell is not exist.
     */
    State getState(String coordinate) throws IllegalArgumentException;

    /**
     * Sets state of the cell.
     *
     * @param coordinate coordinate.
     * @param state      state.
     * @throws IllegalArgumentException - illegal argument.
     */
    void setState(String coordinate, State state) throws IllegalArgumentException;
}
