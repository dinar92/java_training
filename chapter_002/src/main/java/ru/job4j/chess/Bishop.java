package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;
import java.util.Arrays;

/**A rook implementation.
 * @author gimazetdinov
 * @version 1.1*/
class Bishop extends Figure {

    /**Sets a position.
     * @param startPosition - position
     * @param color - color of a figure
     * @param board - board*/
    Bishop(Cell startPosition, String color, Board board) {
        super(startPosition, color, board);
    }

    /** Horizontal coordinate.*/
    private int horCoord = this.position.horCoord;

    /**Vertical coordinate.*/
    private int vertCoord = this.position.vertCoord;

    /**Clones the figure to destination.
     *@param dest - destination
     *@return cloned figure*/
    Figure clone(Cell dest) {
        this.horCoord = dest.horCoord;
        this.vertCoord = dest.vertCoord;
        return this;
    }

    /** The max size of the coordinate.*/
    private final int size = 8;

    /**The array of crossed cells.*/
    private Cell[] cells = new Cell[size];

    /**Checks, that the figure can take place in a given cell.
     * @param dest destination
     * @return array of cells, that must cross the figure
     * @throws ImpossibleMoveException - if the figure can not move so*/
    Cell[] way(Cell dest) throws ImpossibleMoveException {
        int row, column, counter;
        for (row = this.horCoord, column = this.vertCoord, counter = 0; row >= 0 && column <= 7; row--, column++, counter++) {
            cells[counter] = this.board.desk[row][column];
            if (cells[counter].equals(dest)) {
                return Arrays.copyOf(cells, counter + 1);
            }
        }
        for (row = this.horCoord, column = this.vertCoord, counter = 0; row <= 7 && column <= 7; row++, column++, counter++) {
            cells[counter] = this.board.desk[row][column];
            if (cells[counter].equals(dest)) {
                return Arrays.copyOf(cells, counter + 1);
            }
        }
        for (row = this.horCoord, column = this.vertCoord, counter = 0; row <= 7 && column >= 0; row++, column--, counter++) {
            cells[counter] = this.board.desk[row][column];
            if (cells[counter].equals(dest)) {
                return Arrays.copyOf(cells, counter + 1);
            }
        }
        for (row = this.horCoord, column = this.vertCoord, counter = 0; row >= 7 && column >= 7; row--, column--, counter++) {
            cells[counter] = this.board.desk[row][column];
            if (cells[counter].equals(dest)) {
                return Arrays.copyOf(cells, counter + 1);
            }
        }
        throw new ImpossibleMoveException("So move is impossible.");
    }
}
