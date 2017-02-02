package ru.job4j.profession.people;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Patient.*/
public class PatientTest {

	/**Testing examine().*/
	@Test
	public void whenExamineThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Patient pat = new Patient();
		pat.examine();
		assertThat(out.toString(), is("Доктор, у меня тут кое-что отвалилось...\n"));
	}

	/**Testing cure().*/
	@Test
	public void whenCureThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Patient pat = new Patient();
		pat.cure();
		assertThat(out.toString(), is("Доктор, спасибо!!! Больше не чешется!!!\n"));
	}

	/**Testing takePrescribe().*/
	@Test
	public void whenTakePrescribeThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Patient pat = new Patient();
		pat.takePrescribe();
		assertThat(out.toString(), is("Доктор, а сколько это стоит?\n"));
	}

	/**Testing heard().*/
	@Test
	public void whenHeardThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Patient pat = new Patient();
		pat.heard();
		assertThat(out.toString(), is("Доктор, я ведь смогу и дальше программировать на Джава с этим?\n"));
	}
}
