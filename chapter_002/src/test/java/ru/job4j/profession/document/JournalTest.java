package ru.job4j.profession.document;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Journal.*/
public class JournalTest {

	/**Testing full().*/
	@Test
	public void whenJournalFullThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Journal jour = new Journal();
		jour.full();
		assertThat(out.toString(), is("Журнал заполнен!\n"));
	}
}
