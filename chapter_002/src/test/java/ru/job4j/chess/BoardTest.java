package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.chess.excepts.FigureNotFoundException;

/**Tests class Board.*/
public class BoardTest {

	/**Tests move().*/
	@Test
	public void whenMoveBishopThenCorrectWork() {
		Board board = new Board();
		board.addFigure(new Bishop(new Cell("c1")));
		boolean result = board.move(new Cell("c1"), new Cell("a3"));
		assertThat(result, is(true));
	}

	/**Tests FigureNotFoundException in move().*/
	@Test
	public void whenFigureNotFoundThenException() {
		Board board = new Board();
		boolean wasExc = false;
		try {
			board.move(new Cell("d4"), new Cell("c4"));
		} catch (FigureNotFoundException fnf) {
			wasExc = true;
		}
		assertThat(wasExc, is(true));
	}
}
