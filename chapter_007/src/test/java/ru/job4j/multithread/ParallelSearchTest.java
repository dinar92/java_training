package ru.job4j.multithread;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the class ParallelSearch.
 */
public class ParallelSearchTest {

    /**
     * Tests search().
     */
    @Test
    public void whenStartSearchThenGetResult() {
        List<String> extensions = new ArrayList<>();
        extensions.add("txt");
        String string = "name";
        String path = "/home/pacman/Development/xxx";
        String expect1 = "/home/pacman/Development/xxx/1of3.txt";
        String expect2 = "/home/pacman/Development/xxx/folder1/2of3.txt";
        String expect3 = "/home/pacman/Development/xxx/folder1/folder2/folder4/3of3.txt";

        List<String> result = new ParallelSearch(string, path, extensions).search();

        assertThat(result, Matchers.hasItem(expect1));
        assertThat(result, Matchers.hasItem(expect2));
        assertThat(result, Matchers.hasItem(expect3));
        assertThat(result.size(), is(3));
    }

}