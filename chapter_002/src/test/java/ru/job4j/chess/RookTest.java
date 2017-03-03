package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import ru.job4j.chess.excepts.OccupiedWayException;
import ru.job4j.chess.excepts.ImpossibleMoveException;

/**Tests class Bishop.*/
public class RookTest {

    /**Tests clone().*/
    @Test
    public void whenCloneThenFigureReplace() {
        Rook fr = new Rook(new Cell("c1"));
        Cell expectPosition = new Cell("a3");
        assertThat(fr.clone(new Cell("a3")).getCell(), samePropertyValuesAs(expectPosition));
    }

    /**Tests way().*/
    @Test
    public void whenSetCellThenGetWay() {
        final int numberOfCells = 3;
        Rook rook = new Rook(new Cell("f5"));
        Cell[] cells = rook.way(new Cell("f7"));
        Cell[] extCells = new Cell[numberOfCells];
        extCells[0] = new Cell("f5");
        extCells[1] = new Cell("f6");
        extCells[2] = new Cell("f7");
        for (int i = 0; i < cells.length; i++) {
            assertThat(cells[i], samePropertyValuesAs(extCells[i]));
        }
    }

    /**Tests OccupiedWayException in way().*/
    @Test
    public void whenOccupiedWayThenException() {
        Rook rook = new Rook(new Cell("d4"));
        Cell cell = new Cell("d3");
        cell.setFill();
        boolean wasExc = false;
        try {
            rook.way(new Cell("d2"));
        } catch (OccupiedWayException owe) {
            wasExc = true;
        }
        assertThat(wasExc, is(true));
    }

    /**Tests ImpossibleMoveException in way().*/
    @Test
    public void whenImpossibleMoveThenException() {
        Rook rook = new Rook(new Cell("g3"));
        boolean wasExc = false;
        try {
            rook.way(new Cell("c4"));
        } catch (ImpossibleMoveException ime) {
            wasExc = true;
        }
        assertThat(wasExc, is(true));
    }
}
