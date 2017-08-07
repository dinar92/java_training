package ru.job4j.menu;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 05.07.17.
 * Tests the class NotFoundItemException.
 */
public class NotFoundItemExceptionTest {

    /**
     * Tests toString().
     */
    @Test
    public void whenInvokeExceptionShouldReturnMessage() {
        String expectedMessage = "Not found item with such identifier!";

        assertThat(new NotFoundItemException().toString(), is(expectedMessage));
    }
}
