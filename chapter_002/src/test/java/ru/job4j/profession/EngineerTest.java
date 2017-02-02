package ru.job4j.profession;

import ru.job4j.profession.project.Project;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Engineer.*/
public class EngineerTest {

	/**Testing developDesign().*/
	@Test
	public void whenDevelopDesignThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Engineer ing = new Engineer();
		ing.developDesign(new Project());
		assertThat(out.toString(), is("Проект реализован успешно! Теперь можно просить прибавку!\n"));
	}
}
