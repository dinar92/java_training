package ru.job4j.multithread;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the class TextBreaker.
 */
public class TextHandlerTest {

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

        new TextHandler(text).run();
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
}