package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;

/**A chess figure.*/
abstract class Figure {

    /**The cell, that indicates the position of the figure on the desk.*/
    final Cell startPosition;

    /**Indicates a color of the figure.*/
    private final String color;

    /**The board.*/
    final Board board;

    /**Constructor sets a position of the figure.
     *@param startPosition cell
     * @param color - color of the figure
     * @param board - board*/
    Figure(Cell startPosition, String color, Board board) {
        this.startPosition = startPosition;
        this.color = color;
        this.board = board;
    }

    /**Gets the figure's color.
     * @return color*/
    String getColor() {
        return this.color;
    }

    /**Clones the figure to destination.
     *@param dest - destination
     *@return cloned figure*/
    abstract Figure clone(Cell dest);

    /**Checks, that the figure can take place in a given cell.
     *@param dest destination
     *@return array of cells, that must cross the figure
     *@throws ImpossibleMoveException - if the figure can not move so*/
    abstract Cell[] way(Cell dest) throws ImpossibleMoveException;
}
