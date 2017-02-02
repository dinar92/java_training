package ru.job4j.profession;

import ru.job4j.profession.people.Patient;

/**Professional doctor.*/
public class Doctor extends Profession {

	/**Examines physically.
	*@param man - patient*/
	public void examinePhysically(Patient man) {
		man.examine();
	}

	/**Cures people.
	*@param man - patient*/
	public void cure(Patient man) {
		man.cure();
	}

	/**Prescribes medication.
	*@param man - patient*/
	public void prescribeMedication(Patient man) {
		man.takePrescribe();
	}

	/**Diagnoses.
	*@param man - patient*/
	public void diagnose(Patient man) {
		man.heard();
	}
}
