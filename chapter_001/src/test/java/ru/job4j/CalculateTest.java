package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Calculate.
* @author gimazetdinov
* @version 1.0
* @since 18.01.2017
*/
public class CalculateTest {

	/**
	*Test echo().
	*/
	@Test
	public void whenEnterStrThenReturnStr() {
		Calculate calc = new Calculate();
		assertThat(calc.echo("Hello world!"), is("Hello world!"));
	}

	/**
	* Test main().
	*/
	@Test
	public void whenRunMainThenSysoutString() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		assertThat(out.toString(), is("Hello world!\n"));
	}
}

