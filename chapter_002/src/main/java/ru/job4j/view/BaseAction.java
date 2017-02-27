package ru.job4j.view;

import ru.job4j.data.Tracker;

/**Base class for other actions.*/
public abstract class BaseAction implements UserAction {

	/**Key of the action.*/
	private final int key;

	/**Info about the menu item.*/
	private final String info;

	/**The constructor sets key and info of the action.
	*@param key of the menu item
	*@param info about the menu item*/
	public BaseAction(int key, String info) {
		this.key = key;
		this.info = info;
	}

	/**Return the key of the action.
	*@return key - key*/
	public int key() {
		return this.key;
	}

	/**Shows info about the action.
	*@return info string*/
	public String info() {
		return String.format("%s. %s", this.key(), this.info);
	}

	/**Executions the action.
	*@param input - method of interaction with the outside
	*@param tracker - the database emulation*/
	public abstract void execute(Input input, Tracker tracker);

}
