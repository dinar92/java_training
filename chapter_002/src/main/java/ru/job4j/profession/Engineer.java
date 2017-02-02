package ru.job4j.profession;

import ru.job4j.profession.project.Project;

/**Professional engineer.*/
public class Engineer extends Profession {

	/**Number of engineer's projects.*/
	private int numberOfProjects;

	/**Engineer develop project.
	*@param project project*/
	public void developDesign(Project project) {
		project.implemented();
	}
}
