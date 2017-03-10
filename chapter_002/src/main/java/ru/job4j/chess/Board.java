package ru.job4j.chess;

import ru.job4j.chess.excepts.FigureNotFoundException;
import ru.job4j.chess.excepts.ImpossibleMoveException;
import ru.job4j.chess.excepts.OccupiedWayException;

/**The board implementation.
 * @author gimazetdinov
 * @version 1.1*/
public class Board {

    /**Calls method, that fills the board of cells.*/
    public Board() {
        this.openBoard();
    }

    /**A sides of the board.*/
    private final int boardSide = 8;

    /**Amount of figures.*/
    private final int countOfFigures = 32;

    /**The board contains cells.*/
    final Cell[][] desk = new Cell[boardSide][boardSide];

    /**A store for figures.*/
    private Figure[] figures = new Figure[countOfFigures];

    /**The figures counter.*/
    private int counterOfFigures = 0;

    /**Fills the board of cells.*/
    private void openBoard() {
        for (int row = 0; row < desk.length; row++) {
            for (int column = 0; column < desk[row].length; column++) {
                this.desk[row][column] = new Cell(row, column);
            }
        }
    }

    /**Adds a new figure in the store.
     * @param figure - a chess figure.*/
    public void addFigure(Figure figure) {
        this.figures[counterOfFigures++] = figure;
    }

    /**Moves the figure from source cell to destination cell.
     * @param source - source cell
     * @param dest - destination cell
     * @return success move
     * @throws ImpossibleMoveException - if the figure can not move so
     * @throws FigureNotFoundException - if cell has not a figure
     * @throws OccupiedWayException - if on the way is another figure*/
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        for (Figure figure : figures) {
            if (figure != null && figure.position.equals(source)) {
                for (Cell cell : figure.way(dest)) {
                    if (dest.equals(cell)) {
                        this.checkMove(cell, figure);
                        return true;
                    }
                    for (Figure otherFigure : figures) {
                        if (otherFigure != null && !figure.equals(otherFigure) && otherFigure.getClass() != Knight.class) {
                           if (cell.equals(otherFigure.position)) {
                                throw new OccupiedWayException("An another figure there is on the way.");
                            }
                        }
                    }
                }
            } else {
                throw new FigureNotFoundException("Cell haven't any figure");
            }
        }
        return false;
    }

    /**Checks figure's move to destination cell.
     * @param cell - destination cell
     * @param figure - figure
     * @throws OccupiedWayException - if on the way is another figure*/
    private void checkMove(Cell cell, Figure figure) throws OccupiedWayException {
        for (Figure otherFigure : figures) {
            if (otherFigure != null && cell.equals(otherFigure.position)) {
                if (otherFigure.getColor().equals(figure.getColor())) {
                    throw new OccupiedWayException("Your figure there is already.");
                } else {
                    figure.clone(cell);
                }
            }
        }
    }
}
