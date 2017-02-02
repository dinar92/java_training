package ru.job4j.profession.people;

/**Typical patient.*/
public class Patient {

	/**On inspection at the doctor.*/
	public void examine() {
		System.out.println("Доктор, у меня тут кое-что отвалилось...");
	}

	/**Doctor cured.*/
	public void cure() {
		System.out.println("Доктор, спасибо!!! Больше не чешется!!!");
	}

	/**The patient takes a prescription.*/
	public void takePrescribe() {
		System.out.println("Доктор, а сколько это стоит?");
	}

	/**The patient heard the diagnosis.*/
	public void heard() {
		System.out.println("Доктор, я ведь смогу и дальше программировать на Джава с этим?");
	}
}
