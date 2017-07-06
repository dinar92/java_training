package ru.job4j.view;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 27.06.17.
 * Tests the class ValidateMenuInput.
 */
public class ValidateMenuInputTest {

    /**
     * The line separator.
     */
    private String ls = System.lineSeparator();

    /**
     * Tests ask(String question, int[] range).
     */
    @Test
    public void whenSetQuestionAndRangeOfIntShouldReturnAnswer() {
        String question = "Why?";
        int[] rangeOfAnswers = {1, 2, 3, 4};
        String answer = "1";
        ByteArrayInputStream bais = new ByteArrayInputStream((answer + ls).getBytes());

        System.setIn(bais);

        assertThat(new ValidateMenuInput().ask(question, rangeOfAnswers), is(answer));
    }

    /**
     * Tests ask(String question, int[] range) with
     * the invalid answer.
     */
    @Test
    public void whenAnswerOutOfRangeOfIntsShouldOutputNotification() {
        String question = "Why?";
        int[] rangeOfAnswers = {1, 2, 3, 4};
        String answer1 = "5";
        String answer2 = "1";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        ByteArrayInputStream bais = new ByteArrayInputStream((answer1 + ls + answer2 + ls).getBytes());
        System.setIn(bais);
        String expectedNotice = "Please, enter the valid menu item!";

        new ValidateMenuInput().ask(question, rangeOfAnswers);

        assertThat(baos.toString(), containsString(expectedNotice));
    }

    /**
     * Tests ask(String question, String[] range).
     */
    @Test
    public void whenSetQuestionAndRangeOfStringShouldReturnAnswer() {
        String question = "Why?";
        String[] rangeOfAnswers = {"1", "2", "3", "4"};
        String answer = "1";
        ByteArrayInputStream bais = new ByteArrayInputStream((answer + ls).getBytes());

        System.setIn(bais);

        assertThat(new ValidateMenuInput().ask(question, rangeOfAnswers), is(answer));
    }
}
