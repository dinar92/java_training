package ru.job4j.compare;

/**Class for comparison.
*@author gimazetdinov
*@version 1.0
*@since 25.01.2017
*/
public class Comparison {

	/**Check string contains substring.
	*@param string - string
	*@param substring - substring
	*@return boolean contains or not
	*/
	public static boolean contain(String string, String substring) {
		char[] str = string.toCharArray();
		char[] subStr = substring.toCharArray();
		int counter = 0;

		for (int i = 0; i < str.length; i++) {
			if (str[i] == subStr[counter]) {
				counter++;
				if (counter == subStr.length) {
					return true;
				}
			} else if (str[i] == subStr[0]) {
				counter = 1;
			} else {
				counter = 0;
			}
		}
		return false;
	}
}
