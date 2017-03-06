package ru.job4j.chess;

/**
 * A chess board's cell.
 * @author gimazetdinov
 * @version 1.1
 */
public class Cell {

    /**The horizontal coordinate.*/
    public final int horCoord;

    /**The vertical coordinate.*/
    public final int vertCoord;

    /**Sets coordinates.
     * @param horCoord - the horizontal coordinate
     * @param vertCoord - the vertical coordinate*/
    Cell(int horCoord, int vertCoord) {
        this.horCoord = horCoord;
        this.vertCoord = vertCoord;
    }

    /**The figure in the cell.*/
    private Figure figure;

    /**Sets a figure.
     * @param figure figure*/
    void setFigure(Figure figure) {
        this.figure = figure;
    }

    /**Gets figure.
     * @return figure*/
    Figure getFigure() {
        return this.figure;
    }

    /**Cleans the cell.*/
    void setEmpty() {
        this.figure = null;
    }
}
