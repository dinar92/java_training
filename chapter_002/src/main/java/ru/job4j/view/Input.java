package ru.job4j.view;

/**Interaction with the external environment.*/
public interface Input {

	/**Method for interaction.
	*@param question - question
	*@return answer - answer*/
	String ask(String question);

	/**Method for interaction.
	*@param question - question
	*@param range - allowable input range of keys
	*@return the user's input key*/
	String ask(String question, int[] range);

	/**Method for interaction.
	*@param question - question
	*@param array - allowable input array of strings
	*@return an allowable user's input*/
	String ask(String question, String[] array);
}
