package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.boardGame.Input;
import ru.job4j.tic_tac_toe.exception.OutOfRangeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 24.07.17.
 * Tests class ConsoleInput.
 */
public class ConsoleInputTest {

    /**
     * Instance of exception.
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests getAnswer(String question).
     */
    @Test
    public void whenInputTextInConsoleShouldReturnText() {
        String expectText = "Test1";
        String consoleInput = "Test1\n";
        String question = "Some question";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(inputStream);

        Input input = new ConsoleInput();
        Assert.assertThat(input.getAnswer(question), is(expectText));
    }

    /**
     * Tests getAnswer(String question).
     */
    @Test
    public void whenSetParameterShouldPrintInConsole() {
        String consoleInput = "Test1\n";
        String expectOut = "Some question\n";
        String question = "Some question";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Input input = new ConsoleInput();
        input.getAnswer(question);
        Assert.assertThat(outputStream.toString(), is(expectOut));
    }

    /**
     * Tests getAnswer(String question, int minLimit, int maxLimit).
     *
     * @throws OutOfRangeException throws if number is out of the specified range.
     */
    @Test
    public void whenAnswerIntegerInRangeShouldReturnInteger() throws OutOfRangeException {
        String question = "Some question";
        String answer = "2";
        int expectAnswer = 2;
        int minLimit = 1;
        int maxLimit = 3;
        ByteArrayInputStream input = new ByteArrayInputStream(answer.getBytes());
        System.setIn(input);

        Input consoleInput = new ConsoleInput();

        Assert.assertThat(consoleInput.getAnswer(question, minLimit, maxLimit), is(expectAnswer));
    }

    /**
     * Tests getAnswer(String question, int minLimit, int maxLimit).
     *
     * @throws OutOfRangeException throws if number is out of the specified range.
     */
    @Test
    public void whenAnswerIntegerMoreThanRangeShouldThrowException() throws OutOfRangeException {
        String question = "Some question";
        String answer = "4";
        int minLimit = 1;
        int maxLimit = 3;
        ByteArrayInputStream input = new ByteArrayInputStream(answer.getBytes());
        System.setIn(input);

        Input consoleInput = new ConsoleInput();

        exception.expect(OutOfRangeException.class);
        consoleInput.getAnswer(question, minLimit, maxLimit);
    }

    /**
     * Tests getAnswer(String question, int minLimit, int maxLimit).
     *
     * @throws OutOfRangeException throws if number is out of the specified range.
     */
    @Test
    public void whenAnswerIntegerLessThanRangeShouldThrowException() throws OutOfRangeException {
        String question = "Some question";
        String answer = "0";
        int minLimit = 1;
        int maxLimit = 3;
        ByteArrayInputStream input = new ByteArrayInputStream(answer.getBytes());
        System.setIn(input);

        Input consoleInput = new ConsoleInput();

        exception.expect(OutOfRangeException.class);
        consoleInput.getAnswer(question, minLimit, maxLimit);
    }

    /**
     * Tests getAnswer(String question, int minLimit, int maxLimit).
     *
     * @throws OutOfRangeException throws if number is out of the specified range.
     */
    @Test
    public void whenAnswerIsNotIntegerShouldThrowException() throws OutOfRangeException {
        String question = "Some question";
        String answer = "Not Integer";
        int minLimit = 1;
        int maxLimit = 3;
        ByteArrayInputStream input = new ByteArrayInputStream(answer.getBytes());
        System.setIn(input);

        Input consoleInput = new ConsoleInput();

        exception.expect(NumberFormatException.class);
        consoleInput.getAnswer(question, minLimit, maxLimit);
    }
}
