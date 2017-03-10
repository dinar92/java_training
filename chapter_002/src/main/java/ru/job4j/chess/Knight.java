package ru.job4j.chess;

import ru.job4j.chess.excepts.ImpossibleMoveException;

/**A knight implementation.*/
class Knight extends Figure {

    /**Sets a position.
     * @param startPosition - position
     * @param color - color of a figure
     * @param board - board*/
    Knight(Cell startPosition, String color, Board board) {
        super(startPosition, color, board);
    }


    /**Horizontal coordinate.*/
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

    /**Checks, that the figure can take place in a given cell.
     *@param dest destination
     *@return array of cells, that must cross the figure
     *@throws ImpossibleMoveException - if the figure can not move so*/
    Cell[] way(Cell dest) throws ImpossibleMoveException {
        return new Cell[0];
    }
}
