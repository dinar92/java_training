package ru.job4j.collectionsPro;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 11.09.17.
 * Tests the class SimpleArrayList.
 */
public class SimpleArrayListTest {

    /**
     * Tests add(, with expand.
     */
    @Test
    public void whenArrayIsFullThenArrayExpanded() {
        int initialSize = 1;
        SimpleArrayList<String> array = new SimpleArrayList<>(initialSize);
        String text1 = "Test1";
        String text2 = "Test2";

        array.add(text1);
        array.add(text2);

        Assert.assertThat(array.get(1), is(text2));
    }
}
