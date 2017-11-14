package ru.job4j.multithread;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the class TextBreaker.
 */
public class TextBreakerTest {

    /**
     * The text.
     */
    private final String text = "word1, word2, word3, word4";

    /**
     * Tests run().
     */
    @Test
    public void whenStartBreakerThenOutToConsoleInfo() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        new TextBreaker(text).run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectOut1 = "Count of words: 4\n";
        String expectOut2 = "Count of spaces: 3\n";

        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut1));
        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut2));
    }

    /**
     * Tests interruption function.
     */
    @Test
    public void whenAppRunTooLongThenThreadsInterrupted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringBuilder bigString = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            bigString.append(text);
        }

        TextBreaker handler = new TextBreaker(bigString.toString());
        handler.interruptIfTooLong(1);
        handler.run();

        String expectOut1 = "The space counter thread was interrupted!\n";
        String expectOut2 = "The words counter thread was interrupted!\n";
        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut1));
        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut2));
    }
}