package ru.job4j.view;

import ru.job4j.data.Tracker;

/**The class starts console user interface.
*@version 2.0
*@author gimazetdinov
*/
public class StartUI {

	/**Method of interaction with the outside.*/
	private Input input;

	/**The database emulation.*/
	private Tracker tracker;

	/**The constructure sets method of interaction with the outside.
	*@param input - method of interaction with the outside
	*@param tracker - database emulator*/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**Program's start point.
	*@param args - command line arguments*/
	public static void main(String[] args) {
		new StartUI(new ValidateMenuInput(), new Tracker()).init();
	}

	/**UI's start method.*/
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillAction();
		String choice;
		do {
			menu.show();
			choice = input.ask("Please, enter 'exit' or select number: ", menu.getKeysRange());
			if (!"exit".equals(choice)) {
				menu.select(Integer.parseInt(choice));
			}
		} while (!"exit".equals(choice));
	}
}
