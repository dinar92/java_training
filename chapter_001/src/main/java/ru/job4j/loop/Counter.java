package ru.job4j.loop;

/**Class for calculate a sum of the range.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class Counter {

	/**
	*Calculate a sum of the range.
	*@param start - first number of the range
	*@param finish - last number of the range
	*@return sum of numbers
	*/
	public int add(int start, int finish) {
		int sum = 0;
		for (int i = start; i <= finish; i++) {
			sum += i;
		}
		return sum;
	}
}
