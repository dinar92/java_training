package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import ru.job4j.chess.excepts.OccupiedWayException;
import ru.job4j.chess.excepts.ImpossibleMoveException;

/**Tests class Bishop.*/
public class BishopTest {

	/**Tests clone().*/
	@Test
	public void whenCloneThenFigureReplace() {
		Bishop fr = new Bishop(new Cell("c1"));
		Cell expectPosition = new Cell("a3");
		assertThat(fr.clone(new Cell("a3")).getCell(), samePropertyValuesAs(expectPosition));
	}

	/**Tests way().*/
	@Test
	public void whenSetCellThenGetWay() {
		final int numberOfCells = 3;
		Bishop bishop = new Bishop(new Cell("c8"));
		Cell[] cells = bishop.way(new Cell("a6"));
		Cell[] extCells = new Cell[numberOfCells];
		extCells[0] = new Cell("c8");
		extCells[1] = new Cell("b7");
		extCells[2] = new Cell("a6");
		for (int i = 0; i < cells.length; i++) {
			assertThat(cells[i], samePropertyValuesAs(extCells[i]));
		}
	}

	/**Tests OccupiedWayException in way().*/
	@Test
	public void whenOccupiedWayThenException() {
		Bishop bish = new Bishop(new Cell("g3"));
		Cell cell = new Cell("f4");
		cell.setFill();
		boolean wasExc = false;
		try {
			bish.way(new Cell("e5"));
		} catch (OccupiedWayException owe) {
			wasExc = true;
		}
		assertThat(wasExc, is(true));
	}

	/**Tests ImpossibleMoveException in way().*/
	@Test
	public void whenImpossibleMoveThenException() {
		Bishop bish = new Bishop(new Cell("d3"));
		boolean wasExc = false;
		try {
			bish.way(new Cell("a3"));
		} catch (ImpossibleMoveException ime) {
			wasExc = true;
		}
		assertThat(wasExc, is(true));
	}
}
