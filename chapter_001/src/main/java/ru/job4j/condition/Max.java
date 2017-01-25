package ru.job4j.condition;

/**Class for determining max value.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class Max {

	/**
	*Return max of two params.
	*@param first - first arg
	*@param second - second arg
	*@return int value
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}

	/**
	*Return max of three params.
	*@param first - first arg
	*@param second - second arg
	*@param third - third arg
	*@return int value
	*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}
