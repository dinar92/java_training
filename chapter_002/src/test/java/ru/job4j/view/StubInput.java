package ru.job4j.view;

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
}
