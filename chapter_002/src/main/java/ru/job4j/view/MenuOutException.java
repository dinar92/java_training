package ru.job4j.view;

/**Exception for ConsoleInput, when index out of menu items.*/
public class MenuOutException extends RuntimeException {

	/**Shows the specified message.
	*@param msg - message*/
	public MenuOutException(String msg) {
        super(msg);
	}
}
