package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**Testing Paint.*/
public class PaintTest {

	/**Testing draw().*/
	@Test
	public void whenTriangleThenDrawsTriangle() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Paint paint = new Paint();
		paint.draw(new Triangle());
		StringBuilder str = new StringBuilder().append("   ^   \n")
								.append("  ^ ^  \n")
								.append(" ^   ^ \n")
								.append("^^^^^^^\n");
		assertThat(out.toString(), is(str.toString()));
	}

	/**Testing draw().*/
	@Test
	public void whenSquareThenDrawsSquare() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Paint paint = new Paint();
		paint.draw(new Square());
		StringBuilder str = new StringBuilder().append("^^^^^^^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^^^^^^^\n");
		assertThat(out.toString(), is(str.toString()));
	}

}
