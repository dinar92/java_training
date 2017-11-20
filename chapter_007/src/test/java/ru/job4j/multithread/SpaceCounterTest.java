package ru.job4j.multithread;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the class SpaceCounter.
 */
public class SpaceCounterTest {
    /**
     * The text.
     */
    private final String text = "word1, word2, word3, word4";

    /**
     * Tests run().
     */
    @Test
    public void whenStartCounterThenOutToConsoleInfo() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expectOut = "Count of spaces: 3\n";
        Thread thread = new Thread(new SpaceCounter(text));

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut));
    }
}