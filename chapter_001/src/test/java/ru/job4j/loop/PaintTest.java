package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class for paint pyramids.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class PaintTest {

	/**Testing pyramid().*/
	@Test
	public void whenHeightIsTwoThenPyramid() {
		final int height = 2;
		Paint paint = new Paint();
		assertThat(paint.pyramid(height), is("  ^\n ^ ^\n"));
	}
}
