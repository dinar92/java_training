package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Cell;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.State;

/**
 * Created by pacman on 10.07.17.
 * The board for tic-tac-toe.
 */
public class TicTacBoard implements GameBoard {

    /**
     * The array of cells implements the board.
     */
    private Cell[][] board = new Cell[0][0];

    /**
     * Creates the specified size board filled with emptiness and sets start coordinates of cells.
     *
     * @param boardSize size.
     */
    @Override
    public void init(int boardSize) {
        this.board = new Cell[boardSize][boardSize];
        for (int row = 0, rowChar = 97; row < boardSize; row++, rowChar++) {
            for (int column = 0, colChar = 49; column < boardSize; column++, colChar++) {
                this.board[row][column] = new TicTacCell(TicTacState.EMPTY, String.valueOf(((char) rowChar)) + String.valueOf(((char) colChar)));
            }
        }
    }

    /**
     * Returns the size of the board.
     *
     * @return size.
     */
    @Override
    public int getSize() {
        return this.board.length;
    }

    /**
     * Returns the state of the specified cell.
     *
     * @param coordinate of the cell.
     * @return state of cell.
     * @throws IllegalArgumentException - such cell is not exist.
     */
    public State getState(String coordinate) throws IllegalArgumentException {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column].getCoord().equals(coordinate)) {
                    return board[row][column].getState();
                }
            }
        }
        throw new IllegalArgumentException("Such cell is not exist!");
    }

    /**
     * Sets state of the cell.
     *
     * @param coordinate coordinate.
     * @param state      state.
     * @throws IllegalArgumentException - illegal argument.
     */
    public void setState(String coordinate, State state) throws IllegalArgumentException {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (this.board[row][column].getCoord().equals(coordinate)) {
                    this.board[row][column].setState(state);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Such cell is not exist!");
    }
}
