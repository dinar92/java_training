package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing Triangle.*/
public class TriangleTest {

	/**Testing pic().*/
	@Test
	public void whenPicThenReturnTriangle() {
		Triangle triangle = new Triangle();
		StringBuilder str = new StringBuilder().append("   ^   \n")
								.append("  ^ ^  \n")
								.append(" ^   ^ \n")
								.append("^^^^^^^\n");
		assertThat(triangle.pic(), is(str.toString()));
	}
}
