package ru.job4j.array;

/**Class for merging arrays.
*@author gimazetdinov
*@since 29.01.2017
*/

public class ArrayMerge {

	/**Merging two sorted in ascending arrays.
	*@param arr1 - first array
	*@param arr2 - second array
	*@return finalArr - final array
	*/
	public int[] merge(int[] arr1, int[] arr2) {
		int[] finalArr = new int[arr1.length + arr2.length];
		int i = 0, j = 0, x = 0;
		while (x < finalArr.length) {
			if (i != arr1.length && (j == arr2.length || arr1[i] < arr2[j])) {
				finalArr[x] = arr1[i];
				i++;
			} else {
				finalArr[x] = arr2[j];
				j++;
			}
			x++;
		}
		return finalArr;
	}
}
