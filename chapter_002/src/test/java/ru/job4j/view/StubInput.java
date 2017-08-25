package ru.job4j.view;

import java.util.ArrayList;

/**Class for testing console user interface.
*@author gimazetdinov
*@version 1.0
*/
public class StubInput implements Input {

	/**List of answers.*/
	private String[] answers;

	/**Counter of questions.*/
	private int position = 0;

	/**Constructor sets the list of answers.
	*@param answers - list of answers*/
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**Method for testing console user interface.
	*@param question - question
	*@return answer on the question*/
	public String ask(String question) {
		return answers[position++];
	}

	/**Method for testing console user interface with specified range of menu items.
	*@param question - question
	*@param range - range of menu items
	*@return answer on the question*/
	public String ask(String question, int[] range) {
		return this.ask(question);
	}

	/**Method for testing console user interface with specified array of menu items.
	*@param question - question
	*@param array - allowable input array of strings
	*@return an allowable user's input*/
	public String ask(String question, String[] array) {
		return this.ask(question);
	}

	/**
	 * Method for interaction.
	 *
	 * @param question - question
	 * @param array    - allowable input list.
	 * @param <E> - type of value.
	 * @return an allowable user's input
	 */
	public <E> String ask(String question, ArrayList<E> array) {
		return this.ask(question);
	}
}
