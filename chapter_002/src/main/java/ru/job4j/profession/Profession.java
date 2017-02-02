package ru.job4j.profession;

import ru.job4j.profession.document.Document;
import ru.job4j.profession.source.Source;

/**The common class for other professions.*/
public class Profession {

	/**An edication of professional.*/
	private String education;

	/**An experence of professional.*/
	private int experienceInYears;

	/**A specialization of professional.*/
	private String specialization;

	/**A salary of professional.*/
	private int salary;

	/**A professional writes documentation.
	*@param doc document*/
	public void writeDocumentation(Document doc) {
		doc.written();
	}

	/**A professional wants to be smarter.
	*@param src source*/
	public void improve(Source src) {
		src.learned();
	}
}
