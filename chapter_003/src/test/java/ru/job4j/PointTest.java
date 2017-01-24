package ru.job4j;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**Test of Point class.*/
public class PointTest {

	/**Test of distanceTo() method.*/
	@Test
	public void whenSetTwoPointThenGetBetweenDistance() {
		final double x1 = 4.1, y1 = 5.5, x2 = 6.5, y2 = 7.0;
		final double expectDistance = 2.83, error = 0.01;
		Point point1 = new Point(x1, y1);
		Point point2 = new Point(x2, y2);
		double distance = point1.distanceTo(point2);
		assertThat(distance, closeTo(expectDistance, error));
	}
}
