package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 19.04.17.
 * Tests class  ConsoleInput.
 */
public class ConsoleInputTest {

    /**
     * Tests getUsersInput().
     *
     * @throws Exception exception.
     */
    @Test
    public void whenMessageForUserThenGetUsersInput() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(("hello\n").getBytes());
        System.setIn(inputStream);
        UsersInput usersInput = new ConsoleInput();
        String enter = "test";
        assertThat(usersInput.getUsersInput(enter), is("hello"));
    }

}