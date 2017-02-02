package ru.job4j.profession;

import ru.job4j.profession.people.Student;
import ru.job4j.profession.document.Journal;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Teacher.*/
public class TeacherTest {

	/**Testing toTeach().*/
	@Test
	public void whenToTeachThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Teacher teacher = new Teacher();
		teacher.toTeach(new Student());
		assertThat(out.toString(), is("Я понял, почему нужно сравнивать объекты через equals!\n"));
	}

	/**Testing setEvaluation().*/
	@Test
	public void whenSetEvalThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Teacher teacher = new Teacher();
		teacher.setEvaluation(new Student());
		assertThat(out.toString(), is("Нужно было вчера учить уроки, а не пиво пить...\n"));
	}

	/**Testing checkHomework().*/
	@Test
	public void whenCheckHomeworkThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Teacher teacher = new Teacher();
		teacher.checkHomework(new Student());
		assertThat(out.toString(), is("Снова не правильно списал...\n"));
	}

	/**Testing fillJournal().*/
	@Test
	public void whenFillJournalThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Teacher teacher = new Teacher();
		teacher.fillJournal(new Journal());
		assertThat(out.toString(), is("Журнал заполнен!\n"));
	}
}
