package ru.job4j.multithread.bomberman;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * A Bomberman game's board.
 * Based on Locks.
 * Has a square shape.
 */
@ThreadSafe
public class Board {

    /**
     * A base of the board.
     */
    private final Cell[][] board;

    /**
     * The state of the board.
     */
    @GuardedBy("this")
    private boolean inited = false;

    /**
     * Sets the size of the board.
     *
     * @param sizeOfSide - the size.
     */
    public Board(int sizeOfSide) {
        this.board = new Cell[sizeOfSide][sizeOfSide];
    }

    /**
     * Builds the board.
     */
    public synchronized void init() {
        if (!inited) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = new Cell(i, j);
                }
            }
            this.inited = true;
        }
    }

    /**
     * Returns the Lock instance, which releases the board's cell,
     * with specified coordinates.
     *
     * @param axisX - X-axis coordinate.
     * @param axisY - Y-axis coordinate.
     * @return - the cell.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public synchronized Cell getCell(int axisX, int axisY) throws BoardIsNotInitException {
        if (!inited) {
            throw new BoardIsNotInitException();
        }
        return this.board[axisX][axisY];
    }

    /**
     * Returns the size of the board's size.
     *
     * @return size.
     */
    public int getSideSize() {
        return this.board.length;
    }
}


