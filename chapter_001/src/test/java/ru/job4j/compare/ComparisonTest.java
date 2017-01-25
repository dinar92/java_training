package ru.job4j.compare;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Test of the Comparison class.
*@author gimazetdinov
*@version 1.0
*@since 25.01.2017
*/
public class ComparisonTest {

	/**Test contain method contain() that return true.*/
	@Test
	public void whenStringContainsSubstringThenTrue() {
		final String str = "Hello world!";
		final String subStr = "lo w";
		final boolean expect = true;
		assertThat(Comparison.contain(str, subStr), is(expect));
	}
}
