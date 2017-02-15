package ru.job4j.strategy;

/**Implementation of a triangle.*/
public class Triangle implements Shape {

	/**Shapes a triangle in the console.
	*@return triangle in string format*/
	public String pic() {
		return new StringBuilder().append("   ^   \n")
								.append("  ^ ^  \n")
								.append(" ^   ^ \n")
								.append("^^^^^^^\n")
								.toString();
	}
}
