package ru.job4j.view;

/**Interaction with the external environment.*/
public interface Input {

	/**Method for interaction.
	*@param question - question
	*@return answer - answer*/
	String ask(String question);
}
