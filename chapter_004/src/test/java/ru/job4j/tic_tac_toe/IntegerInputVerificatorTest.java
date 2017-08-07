package ru.job4j.tic_tac_toe;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;


/**
 * Created by pacman on 04.08.17.
 * Tests class IntegerInputVerificator.
 */
public class IntegerInputVerificatorTest {

    /**
     * Tests getVerifiedAnswer() with correct answer.
     */
    @Test
    public void whenCorrectInpputThenGetInteger() {
        System.setIn(new ByteArrayInputStream("23\n".getBytes()));

        String question = "How're you old?";
        int minLimit = 1;
        int maxLimit = 110;
        int expectAnswer = 23;

        IntegerInputVerificator verificator = new IntegerInputVerificator(new ConsoleInput(), new ConsoleDisplay());

        Assert.assertThat(verificator.getVerifiedAnswer(question, minLimit, maxLimit), is(expectAnswer));
    }

    /**
     * Tests getVerifiedAnswer() when answer's out of range of the specified limit.
     */
    @Test
    public void whenInputIsOutOfRangeThenEnterInputAgainMessage() {
        System.setIn(new ByteArrayInputStream("150\n25\n".getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String question = "How're you old?";
        int minLimit = 1;
        int maxLimit = 110;
        String expectMessage = "Please, give the correct answer!";

        IntegerInputVerificator verificator = new IntegerInputVerificator(new ConsoleInput(), new ConsoleDisplay());
        verificator.getVerifiedAnswer(question, minLimit, maxLimit);

        Assert.assertThat(out.toString(), is(CoreMatchers.containsString(expectMessage)));
    }

    /**
     * Tests getVerifiedAnswer() when answer isn't an integer.
     */
    @Test
    public void whenAnswerIsNotIntegerThenEnterInputAgainMessage() {
        System.setIn(new ByteArrayInputStream("Twenty five\n25\n".getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String question = "How're you old?";
        int minLimit = 1;
        int maxLimit = 110;
        String expectMessage = "Please, give the correct answer!";

        IntegerInputVerificator verificator = new IntegerInputVerificator(new ConsoleInput(), new ConsoleDisplay());
        verificator.getVerifiedAnswer(question, minLimit, maxLimit);

        Assert.assertThat(out.toString(), is(CoreMatchers.containsString(expectMessage)));
    }
}
