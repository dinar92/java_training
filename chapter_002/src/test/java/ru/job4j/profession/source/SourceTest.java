package ru.job4j.profession.source;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Source.*/
public class SourceTest {

	/**Testing learned().*/
	@Test
	public void whenImplementedThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Source src = new Source();
		src.learned();
		assertThat(out.toString(), is("Теперь-то меня точно повысят!\n"));
	}
}
