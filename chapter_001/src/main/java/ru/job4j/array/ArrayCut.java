package ru.job4j.array;

import java.util.Arrays;

/**
*Class for cut operations of arrays.
*@author gimazetdinov
*@version 1.0
*@since 25.01.2017
*/
public class ArrayCut {

	/**Method that leaves only the unique strings in the array.
	*@param arr - input array
	*@return unique array
	*/
	public String[] uniq(String[] arr) {
		int len = arr.length;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) {
				for (int j = i + 1; j < arr.length; j++) {
					if (!arr[j].equals("")) {
						arr[i] = arr[j];
						arr[j] = "";
					}
				}
			}
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].equals(arr[j])) {
					arr[j] = "";
					len--;
				}
			}
		}
		return Arrays.copyOf(arr, len);
	}
}
