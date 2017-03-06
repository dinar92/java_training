package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Tests Cell.*/
public class CellTest {

    /**Tests setFigure(), getFigure().*/
    @Test
    public void whenSetFigureThenGetThisFigure() {
        Cell cell = new Cell(4, 5);
        Bishop bishop = new Bishop(cell, "white", new Board());
        cell.setFigure(bishop);
        assertThat(cell.getFigure(), is(bishop));
    }

    /**Tests setEmpty().*/
    @Test
    public void whenSetEmptyThenFigureIsNull() {
        Cell cell = new Cell(4, 5);
        Bishop bishop = new Bishop(cell, "white", new Board());
        cell.setFigure(bishop);
        cell.setEmpty();
        bishop = null;
        assertThat(cell.getFigure(), is(bishop));
    }
}
