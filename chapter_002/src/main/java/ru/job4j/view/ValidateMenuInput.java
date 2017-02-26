package ru.job4j.view;

/**The class for validate user's menu input.*/
public class ValidateMenuInput extends ConsoleInput {

	/**Validates user's input.
	*@param question - question
	*@param range - range of keys
	*@return user's input*/
	public String ask(String question, int[] range) {
		boolean invalid = true;
		String key = null;
		do {
			try {
				key = super.ask(question, range);
				invalid = false;
			} catch (MenuOutException moe) {
				System.out.println("Please, enter the valid menu item!");
			} catch (NumberFormatException nfe) {
				System.out.println("Please, enter the valid menu item!");
			}
		} while (invalid);
		return key;
	}

	/**Validates user's input.
	*@param question - question
	*@param array - array of allowable strings
	*@return user's input (answer)*/
	public String ask(String question, String[] array) {
		boolean invalid = true;
		String key = null;
		do {
			try {
				key = super.ask(question, array);
				invalid = false;
			} catch (MenuOutException moe) {
				System.out.println("Please, enter the predefined item!");
			}
		} while (invalid);
		return key;
	}
}
