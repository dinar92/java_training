package ru.job4j.view;

import org.junit.Test;
import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Created by pacman on 23.06.17.
 * Tests MenuOutException.
 */
public class MenuOutExceptionTest {

    /**
     * Tests exception working.
     */
    @Test
    public void whenThrowsExceptionThenGetMessage() {

        String question = "question";
        int[] rangeOfMenu = {1, 2, 3};
        String outIndex = "4";
        ByteArrayInputStream bais = new ByteArrayInputStream(outIndex.getBytes());
        System.setIn(bais);

        String expectedMessage = "Out of menu items!";
        String actual = "Test failure";

        try {
            new ConsoleInput().ask(question, rangeOfMenu);
        } catch (MenuOutException ex) {
            actual = ex.getMessage();
        }

        assertThat(actual, is(expectedMessage));
    }
}
