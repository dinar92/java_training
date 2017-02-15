package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing Square.*/
public class SquareTest {

	/**Testing pic().*/
	@Test
	public void whenPicThenReturnSquare() {
		Square square = new Square();
		StringBuilder str = new StringBuilder().append("^^^^^^^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^^^^^^^\n");
		assertThat(square.pic(), is(str.toString()));
	}
}
