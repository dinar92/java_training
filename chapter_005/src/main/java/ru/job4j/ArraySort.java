package ru.job4j;

/**Class for arrays sorting.
*@author gimazetdinov
*@version 1.0
*@since 24.01.2017
*/
public class ArraySort {

	/**Method sorts arrays with the bubble sort alorithm.
	*@param array - unsorted array
	*@return array - sorted array
	*/
	public int[] bubbleSort(int[] array) {
		int tmp = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
		return array;
	}
}
