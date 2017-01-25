package ru.job4j.loop;

/**Class for paint pyramids.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class Paint {

	/**Method which paint a pyramid.
	*@param h - height of a pyramid
	*@return paint of pyramid in the String format
	*/
	public String pyramid(int h) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < h; i++) {
			for (int j = i; j < h - 1; j++) {
				str.append(" ");
			}
			for (int j = 0; j < i + 1; j++) {
				str.append(" ^");
			}
			str.append("\n");
		}
		return str.toString();
	}
}
