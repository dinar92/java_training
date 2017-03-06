package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.chess.excepts.ImpossibleMoveException;

/**Tests Bishop*/
public class BishopTest {

    /**Tests clone().*/
    @Test
    public void whenCloneFigureThenCloned() {
        Cell source = new Cell(4, 5);
        Cell destination = new Cell(6, 8);
        Bishop bishop = new Bishop(source, "white", new Board());
        assertThat(bishop.clone(destination), is(bishop));
    }

    /**Tests way().*/
    @Test
    public void whenWayThenGetWayInArray() {
        Board board = new Board();
        Bishop bishop = new Bishop(board.desk[2][0], "white", board);

        Cell[] expect = new Cell[3];
        expect[0] = board.desk[2][0];
        expect[1] = board.desk[1][1];
        expect[2] = board.desk[0][2];

        for(int i = 0; i < expect.length; i++) {
            assertThat(bishop.way(board.desk[0][2])[i], is(expect[i]));
        }
    }

    /**Test move().*/
    @Test
    public void whenImpossibleMoveThenImpossibleMoveException() {
        Board board = new Board();
        Bishop bishop = new Bishop(board.desk[2][0], "white", board);
        boolean isExcept = false;
        try {
            bishop.way(board.desk[0][3]);
        } catch (ImpossibleMoveException ime) {
            isExcept = true;
        }
        assertThat(isExcept, is(true));
    }

    /**Tests getColor().*/
    @Test
    public void whenGetColorThenColor() {
        Bishop bishop = new Bishop(new Cell(2, 0), "black", new Board());
        assertThat(bishop.getColor(), is("black"));
    }
}
