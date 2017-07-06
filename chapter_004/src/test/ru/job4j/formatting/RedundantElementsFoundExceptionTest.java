package ru.job4j.formatting;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by pacman on 05.07.17.
 * Tests the class RedundantElementsFoundExceptionTest.
 */
public class RedundantElementsFoundExceptionTest {

    /**
     * Tests toString().
     */
    @Test
    public void whenSetListShouldReturnFormattedString() {
        ArrayList<String> list = new ArrayList<>();
        String elem1 = "elem1";
        String elem2 = "elem2";
        String elem3 = "elem3";
        list.add(elem1);
        list.add(elem2);
        list.add(elem3);

        String expectedString = String.format("Found redundant elements with keys: %s, %s, %s.", elem1, elem2, elem3);


        assertThat(new RedundantElementsFoundException(list).toString(), is(expectedString));
    }
}
