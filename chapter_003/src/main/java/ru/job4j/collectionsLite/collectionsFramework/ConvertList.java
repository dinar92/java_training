package ru.job4j.collectionsLite.collectionsFramework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 21.08.17.
 * Lists converter.
 */
public class ConvertList {

    /**
     * Converts array to list.
     *
     * @param array the two-dimensional array.
     * @return the List of integers.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> integersArray = new ArrayList<>((int) Math.pow(array.length, 2));
        for (int[] row : array) {
            for (int elem : row) {
                integersArray.add(elem);
            }
        }
        return integersArray;
    }

    /**
     * Converts List to the two-dimensional array with specified
     * count of rows that the array must have.
     *
     * @param list the input List.
     * @param rows the count of rows.
     * @return the two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        List<Integer> workList = list;
        while ((workList.size() % rows) != 0) {
            workList.add(0);
        }
        int elemsInRow = workList.size() / rows;
        int currentElemInRow = 0, rowCounter = 0;
        int[][] array = new int[rows][elemsInRow];
        for (Integer elem : workList) {
            if (currentElemInRow == elemsInRow) {
                currentElemInRow = 0;
                rowCounter++;
            }
            array[rowCounter][currentElemInRow] = elem;
            currentElemInRow++;
        }
        return array;
    }

    /**
     * Converts the list of arrays to one list.
     * @param list the list of arrays.
     * @return List.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> converted = new ArrayList<>();
        for (int[] intsArray : list) {
            for (int elem : intsArray) {
                converted.add(elem);
            }
        }
        return converted;
    }
}
