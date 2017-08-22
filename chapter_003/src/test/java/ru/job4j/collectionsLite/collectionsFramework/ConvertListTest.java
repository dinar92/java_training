package ru.job4j.collectionsLite.collectionsFramework;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 22.08.17.
 * Tests class ConvertList.
 */
public class ConvertListTest {

    /**
     * Tests toList().
     */
    @Test
    public void whenSetTwoDimensionalArrayShouldGetOneDimensionalList() {
        int[][] array = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        List<Integer> list = new ArrayList<>();
        for (int elem = 1; elem < 10; elem++) {
            list.add(elem);
        }

        Assert.assertThat(new ConvertList().toList(array), is(list));
    }

    /**
     * Tests toArray().
     */
    @Test
    public void whenSetListShouldGetTwoDimensionalArray() {
        List<Integer> list = new ArrayList<>();
        for (int elem = 1; elem < 10; elem++) {
            list.add(elem);
        }
         int rowsCount = 4;

        int[][] array = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {0, 0, 0}};
        Assert.assertThat(new ConvertList().toArray(list, rowsCount), is(array));
    }

}
