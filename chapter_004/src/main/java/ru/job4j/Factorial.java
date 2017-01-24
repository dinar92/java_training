package ru.job4j;

/**Class Factorial for calculate a factorial.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class Factorial {

	/**
	*Method for calculate a factorial of specified number.
	*@param numb - specified number
	*@return fact - factorial
	*/
	public int fact(int numb) {
		int fact = 1;
		int counter = 1;
		while (counter <= numb) {
			fact *= counter;
			counter++;
		}
		return fact;
	}
}
