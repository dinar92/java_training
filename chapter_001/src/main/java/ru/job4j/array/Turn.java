package ru.job4j.array;

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

	/**Method which rotate the square array.
	*@param inputArray unrotated array
	*@return inputArray rotated array
	*/
	public int[][] rotate(int[][] inputArray) {
		int tmp = 0;
		int len = inputArray.length;
		for (int i = 0; i < len / 2; i++) {
			for (int j = i; j < len - 1 - i; j++) {
				tmp = inputArray[i][j];
				inputArray[i][j] = inputArray[len - j - 1][i];
				inputArray[len - j - 1][i] = inputArray[len - i - 1][len - j - 1];
				inputArray[len - i - 1][len - j - 1] = inputArray[j][len - i - 1];
				inputArray[j][len - i - 1] = tmp;
			}
		}
		return inputArray;
	}
}
