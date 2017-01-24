package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Test of Max class.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class MaxTest {

	/**Test max() method which returns max value of two.*/
	@Test
	public void whenFiveAndTwoThenFive() {
		final int first = 5;
		final int second = 2;
		final int expected = 5;

		Max obj = new Max();
		assertThat(obj.max(first, second), is(expected));
	}
}
