package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class cut operations of arrays.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class ArrayCutTest {

	/**Testing uniq().*/
	@Test
	public void whenArrayThenUniqArray() {
		final String[] arr = {"Привет ", "Привет ", "Мир ", "Мир "};
		final String[] uniqArr = {"Привет ", "Мир "};
		ArrayCut obj = new ArrayCut();
		assertThat(obj.uniq(arr), is(uniqArr));
	}
}
