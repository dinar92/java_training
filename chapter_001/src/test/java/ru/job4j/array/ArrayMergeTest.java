package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing ArrayMerge.
*@author  gimazetdinov
*@since 29.01.2017
*/
public class ArrayMergeTest {
	/**Testing merge().*/
	@Test
	public void whenTwoSortedArrayThenMergingSortedArray() {
		final int[] arr1 = {1, 3, 4};
		final int[] arr2 = {2, 3, 5};
		final int[] finalArr = {1, 2, 3, 3, 4, 5};
		ArrayMerge obj = new ArrayMerge();
		assertThat(obj.merge(arr1, arr2), is(finalArr));
	}
}
