package ru.job4j.profession.people;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Student.*/
public class StudentTest {

	/**Testing study().*/
	@Test
	public void whenStudyThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Student st = new Student();
		st.study();
		assertThat(out.toString(), is("Я понял, почему нужно сравнивать объекты через equals!\n"));
	}

	/**Testing wasRated().*/
	@Test
	public void whenWasRatedThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Student st = new Student();
		st.wasRated();
		assertThat(out.toString(), is("Нужно было вчера учить уроки, а не пиво пить...\n"));
	}

	/**Testing openNotebook().*/
	@Test
	public void whenOpenNotebookThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Student st = new Student();
		st.openNotebook();
		assertThat(out.toString(), is("Снова не правильно списал...\n"));
	}
}
