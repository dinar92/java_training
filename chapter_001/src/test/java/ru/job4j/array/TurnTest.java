package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class for revert arrays.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class TurnTest {
	/**Testing back().*/
	@Test
	public void whenArrayThenRevertedArray() {
		final int[] arr = {6, 5, 4, 3, 2, 1};
		final int[] revertedArr = {1, 2, 3, 4, 5, 6};
		Turn obj = new Turn();
		assertThat(obj.back(arr), is(revertedArr));
	}

	/**Testing rotate().*/
	public void whenArrayThenRotatedArray() {
		final int[][] arr = {{1, 2, 3},
							 {4, 5, 6},
							 {7, 8, 9}};
		final int[][] rotatedArr = {{7, 4, 1},
									{8, 5, 2},
									{9, 6, 3}};
		Turn obj = new Turn();
		assertThat(obj.rotate(arr), is(rotatedArr));
	}
}
