package ru.job4j.view;

import ru.job4j.data.Tracker;

/**Interface for the user action implementation.*/
interface UserAction {

	/**Key of the action.
	*@return key - key*/
	int key();

	/**The action.
	*@param input - input method
	*@param tracker - database emulation*/
	void execute(Input input, Tracker tracker);

	/**Info about the action.
	*@return info - info*/
	String info();
}
