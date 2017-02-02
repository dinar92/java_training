package ru.job4j.profession.project;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Project.*/
public class ProjectTest {

	/**Testing implemented().*/
	@Test
	public void whenImplementedThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Project proj = new Project();
		proj.implemented();
		assertThat(out.toString(), is("Проект реализован успешно! Теперь можно просить прибавку!\n"));
	}
}
