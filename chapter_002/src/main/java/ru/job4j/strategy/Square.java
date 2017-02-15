package ru.job4j.strategy;

/**Implementation of a square.*/
public class Square implements Shape {

	/**Shapes a square in the console.
	*@return square in string format*/
	public String pic() {
		return new StringBuilder().append("^^^^^^^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^     ^\n")
								.append("^^^^^^^\n")
								.toString();
	}
}
