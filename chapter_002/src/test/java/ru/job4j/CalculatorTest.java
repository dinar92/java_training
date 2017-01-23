package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Calculator.
*@author gimazetdinov
*@since 23.01.2017
*@version 1.0
*/
public class CalculatorTest {

	/**
	*Test add() method.
	*/
	@Test
	public void whenAddTwoToThreeThenFive() {
		final double TWO = 2.0;
		final double THREE = 3.0;
		final double FIVE = 5.0;
		Calculator calc = new Calculator();
		calc.add(TWO, THREE);
		assertThat(calc.getResult(), is(FIVE));
	}

	/**
	*Test substract() method.
	*/
	@Test
	public void whenFourSubstractThreeThenOne() {
		final double FOUR = 4.0;
		final double THREE = 3.0;
		final double ONE = 1.0;
		Calculator calc = new Calculator();
		calc.substract(FOUR, THREE);
		assertThat(calc.getResult(), is(ONE));
	}

	/**
	*Test div() method.
	*/
	@Test
	public void whenFourDividedByTwoThenTwo() {
		final double FOUR = 4.0;
		final double TWO = 2.0;
		Calculator calc = new Calculator();
		calc.div(FOUR, TWO);
		assertThat(calc.getResult(), is(TWO));
	}

	/**
	*Test multiple() method.
	*/
	@Test
	public void whenMultipleFourToTwoThenEight() {
		final double FOUR = 4.0;
		final double TWO = 2.0;
		final double EIGHT = 8.0;
		Calculator calc = new Calculator();
		calc.multiple(FOUR, TWO);
		assertThat(calc.getResult(), is(EIGHT));
	}

	/**
	*Test getResult() method without any arithmetical expression.
	*/
	@Test
	public void whenGetResultThenReturnZero() {
		final double ZERO = 0.0;
		Calculator calc = new Calculator();
		assertThat(calc.getResult(), is(ZERO));
	}
}
