package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.chess.excepts.FigureNotFoundException;
import ru.job4j.chess.excepts.OccupiedWayException;

/**Tests Board.*/
public class BoardTest {

    /**Tests move().*/
    @Test
    public void whenMoveThenMoved() {
        Board board = new Board();
        board.addFigure(new Bishop(board.desk[2][0], "white", board));
        board.fillBoard();
        assertThat(board.move(board.desk[2][0], board.desk[0][2]), is(true));
    }

    /**Tests move().*/
    @Test
    public void whenFigureNotFoundThenFigureNotFoundException() {
        Board board = new Board();
        board.fillBoard();
        boolean isExcept = false;
        try {
            board.move(board.desk[2][0], board.desk[0][2]);
        } catch (FigureNotFoundException fnfe) {
            isExcept = true;
        }
        assertThat(isExcept, is(true));
    }

    /**Test move().*/
    @Test
    public void whenOccupiedWayThenOccupiedWayException() {
        Board board = new Board();
        board.addFigure(new Bishop(board.desk[2][0], "white", board));
        board.addFigure(new Bishop(board.desk[1][1], "white", board));
        board.fillBoard();
        boolean isExcept = false;
        try {
            board.move(board.desk[2][0], board.desk[0][2]);
        } catch (OccupiedWayException owe) {
            isExcept = true;
        }
        assertThat(isExcept, is(true));
    }
}
