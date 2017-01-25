package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**Test of Triangle class.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class TriangleTest {

	/**Testing area() method of Triangle class with correct points.*/
	@Test
	public void whenSetPointsThenReturnArea() {
		final double x1 = 4.1, y1 = 5.5;
		final double x2 = 6.5, y2 = 7.0;
		final double x3 = 4.5, y3 = 6.0;

		Point point1 = new Point(x1, y1);
		Point point2 = new Point(x2, y2);
		Point point3 = new Point(x3, y3);

		final double expectedArea = 0.29;
		final double error = 0.01;

		Triangle triangle = new Triangle(point1, point2, point3);
		assertThat(triangle.area(), closeTo(expectedArea, error));
	}

	/**Testing area() method of Triangle class width incorrect points.*/
	@Test
	public void whenSetIncorrPointsThenReturnNaN() {
		final double x1 = 4.1, y1 = 5.5;
		final double x2 = 6.5, y2 = 7.0;
		final double x3 = 6.5, y3 = 7.0;

		Point point1 = new Point(x1, y1);
		Point point2 = new Point(x2, y2);
		Point point3 = new Point(x3, y3);

		final double expectedArea = Double.NaN;
		//final double error = 0.01;

		Triangle triangle = new Triangle(point1, point2, point3);
		assertThat(triangle.area(), is(expectedArea));
	}
}
