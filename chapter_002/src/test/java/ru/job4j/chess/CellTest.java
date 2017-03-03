package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Tests class Cell.*/
public class CellTest {

	/**Tests convertToPos().*/
	@Test
	public void whenSetPositionThenGetArrayOfInts() {

		Cell cell = new Cell("c1");
		int[] coords = cell.convertToPos("c1");
		int[] expect = {0, 2};
		assertThat(coords, is(expect));

	}

	/**Tests isEmpty().*/
	@Test
	public void whenIsNotEmptyThenFalse() {

		Cell cell = new Cell("a1");
		cell.setFill();
		assertThat(cell.isEmpty(), is(false));

	}


	/**Tests getPosition().*/
	@Test
	public void whenGetPositionThenPosition() {

		Cell cell = new Cell("b1");
		String expect = "b1";
		assertThat(cell.getPosition(), is(expect));

	}

	/**Tests setPosition().*/
	@Test
	public void whenSetPositionThenPosition() {

		Cell cell = new Cell("g1");
		cell.setPosition("b1");
		String expect = "b1";
		assertThat(cell.getPosition(), is(expect));

	}

	/**Tests setFill().*/
	@Test
	public void whenSetFillThenFill() {

		Cell cell = new Cell("g1");
		cell.setFill();
		boolean expect = false;
		assertThat(cell.isEmpty(), is(expect));

	}

	/**Tests setEmpty().*/
	@Test
	public void whenSetEmptyThenEmpty() {

		Cell cell = new Cell("g1");
		cell.setFill();
		cell.setEmpty();
		boolean expect = true;
		assertThat(cell.isEmpty(), is(expect));

	}
}
