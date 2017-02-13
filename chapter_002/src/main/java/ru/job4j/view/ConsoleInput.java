package ru.job4j.view;

import java.util.Scanner;

/**Class for user interaction software.
*@author gimazetdinov
*@version 1.0
*/
public class ConsoleInput implements Input {

	/**Console input reader.*/
	private Scanner scanner = new Scanner(System.in);

	/**User's input read answer on question.
	*@param question - question
	*@return user's input*/
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

}
