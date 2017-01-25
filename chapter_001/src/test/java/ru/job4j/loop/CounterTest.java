package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing the class for calculate a sum of the range.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class CounterTest {

	/**
	*Testing add() method.
	*/
	@Test
	public void whenRangeFromOneToSixThenTwentyone() {
		final int start = 1;
		final int finish = 6;
		final int sum = 21;
		Counter counter = new Counter();
		assertThat(counter.add(start, finish), is(sum));
	}
}
