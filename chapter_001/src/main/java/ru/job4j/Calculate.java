package ru.job4j;

/**
*Class for calculation of arithmetic operations.
*@author gimazetdinov
*@since 18.01.2017
*@version 1.0
*/
public class Calculate {

	/**
	*Output given string to console.
	*@param str - string
	*@return given string
	*/
	public String echo(String str) {
		return String.format("%s", str);
	}

	/**
	*Method for tests.
	*@param args - entered args
	*/
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("Hello world!"));
	}
}
