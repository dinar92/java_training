package ru.job4j;

/**Class for revert arrays.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class Turn {

	/**Method which revert the array.
	*@param inputArray unreverted array
	*@return inputArray reverted array
	*/
	public int[] back(int[] inputArray) {
		int tmp = 0;
		for (int i = 0; i < inputArray.length / 2; i++) {
			tmp = inputArray[i];
			inputArray[i] = inputArray[inputArray.length - 1 - i];
			inputArray[inputArray.length - 1 - i] = tmp;
		}
		return inputArray;
	}
}
