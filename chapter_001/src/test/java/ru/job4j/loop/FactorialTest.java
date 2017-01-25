package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class for calculate a factorial.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class FactorialTest {

	/**Testing fact() method.*/
	@Test
	public void whenSetThreeThenReturnTwentyFour() {
		final int number = 4;
		final int expectedFact = 24;
		Factorial obj = new Factorial();
		assertThat(obj.fact(number), is(expectedFact));
	}
}
