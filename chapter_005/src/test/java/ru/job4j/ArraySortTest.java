package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class for arrays sorting.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class ArraySortTest {

	/**Testing bubbleSort().*/
	@Test
	public void whenUnsortedArrayThenSortedArray() {
		final int[] unsortedArr = {6, 77, 2, 56};
		final int[] sortedArr = {2, 6, 56, 77};
		ArraySort obj = new ArraySort();
		assertThat(obj.bubbleSort(unsortedArr), is(sortedArr));
	}
}
