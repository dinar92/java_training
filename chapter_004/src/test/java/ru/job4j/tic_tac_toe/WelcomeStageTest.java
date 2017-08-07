package ru.job4j.tic_tac_toe;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 07.08.17.
 * Tests class WelcomeStage.
 */
public class WelcomeStageTest {

    /**
     * Tests showInfo().
     */
    @Test
    public void whenShowInfoThenShowGreeting() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String expectGreeting = "Welcome to the tic-tac-toe vs. computer!\n";

        new WelcomeStage(new ConsoleInput(), new ConsoleDisplay()).showInfo();

        Assert.assertThat(out.toString(), is(expectGreeting));
    }

    /**
     * Tests action().
     */
    @Test
    public void whenIncorrectEnterThenWarningMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        System.setIn(new ByteArrayInputStream("Incorrect\n\n".getBytes()));

        String expectWarning = "Please, press enter again.";

        new WelcomeStage(new ConsoleInput(), new ConsoleDisplay()).action();

        Assert.assertThat(out.toString(), CoreMatchers.containsString(expectWarning));
    }
}
