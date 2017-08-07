package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.Display;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 24.07.17.
 * Tests class ConsoleDisplay.
 */
public class ConsoleDisplayTest {

    /**
     * Tests showln(String message).
     */
    @Test
    public void whenSetMessageShouldPrintItOnConsoleWithNewLine() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Display display = new ConsoleDisplay();
        String inputMessage = "Test1";
        String expectMessageOnConsole = "Test1\n";

        display.showln(inputMessage);

        Assert.assertThat(bos.toString(), is(expectMessageOnConsole));
    }

    /**
     * Tests showln(String message).
     */
    @Test
    public void whenSetMessageShouldPrintItOnConsole() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Display display = new ConsoleDisplay();
        String inputMessage = "Test2";
        String expectMessageOnConsole = "Test2";

        display.show(inputMessage);

        Assert.assertThat(bos.toString(), is(expectMessageOnConsole));
    }
}
