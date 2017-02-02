package ru.job4j.profession.document;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Document.*/
public class DocumentTest {

	/**Testing written().*/
	@Test
	public void whenWrittenThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Document doc = new Document();
		doc.written();
		assertThat(out.toString(), is("Документация написана! Можно уйти домой пораньше!\n"));
	}
}
