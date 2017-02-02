package ru.job4j.profession;

import ru.job4j.profession.people.Student;
import ru.job4j.profession.document.Journal;

/**Professional teacher.*/
public class Teacher extends Profession {

	/**Teacher teaches at a certain methodic.*/
	private String methodOfTeaching;

	/**Teacher teaches.
	*@param student student*/
	public void toTeach(Student student) {
		student.study();
	}

	/**Teacher sets evaluation.
	*@param student student*/
	public void setEvaluation(Student student) {
		student.wasRated();
	}

	/**Teacher checks homeworks.
	*@param student student*/
	public void checkHomework(Student student) {
		student.openNotebook();
	}

	/**Teacher fills a journal.
	*@param journal journal*/
	public void fillJournal(Journal journal) {
		journal.full();
	}
}
