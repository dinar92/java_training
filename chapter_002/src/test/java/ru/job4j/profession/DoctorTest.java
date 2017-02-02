package ru.job4j.profession;

import ru.job4j.profession.people.Patient;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing class Doctor.*/
public class DoctorTest {

	/**Testing examinePhysically().*/
	@Test
	public void whenExaminePhysicallyThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Doctor doc = new Doctor();
		doc.examinePhysically(new Patient());
		assertThat(out.toString(), is("Доктор, у меня тут кое-что отвалилось...\n"));
	}

	/**Testing cure().*/
	@Test
	public void whenCureThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Doctor doc = new Doctor();
		doc.cure(new Patient());
		assertThat(out.toString(), is("Доктор, спасибо!!! Больше не чешется!!!\n"));
	}

	/**Testing prescribeMedication().*/
	@Test
	public void whenPrescribeMedicationThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Doctor doc = new Doctor();
		doc.prescribeMedication(new Patient());
		assertThat(out.toString(), is("Доктор, а сколько это стоит?\n"));
	}

	/**Testing diagnose().*/
	@Test
	public void whenDiagnoseThenOutput() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Doctor doc = new Doctor();
		doc.diagnose(new Patient());
		assertThat(out.toString(), is("Доктор, я ведь смогу и дальше программировать на Джава с этим?\n"));
	}
}
