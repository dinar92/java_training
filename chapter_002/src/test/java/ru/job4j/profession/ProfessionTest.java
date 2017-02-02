package ru.job4j.profession;

import ru.job4j.profession.source.Source;
import ru.job4j.profession.document.Document;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Profession.*/
public class ProfessionTest {

	/**Testing writeDocumentation().*/
	@Test
	public void whenWriteDocumentationThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Profession prof = new Profession();
		prof.writeDocumentation(new Document());
		assertThat(out.toString(), is("Документация написана! Можно уйти домой пораньше!\n"));
	}

	/**Testing improve().*/
	@Test
	public void whenImproveThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Profession prof = new Profession();
		prof.improve(new Source());
		assertThat(out.toString(), is("Теперь-то меня точно повысят!\n"));
	}

}
