package ru.job4j.multithread;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the class WordCounter.
 */
public class WordCounterTest {

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
        Runnable counter = new WordCounter(text);
        Thread thread = new Thread(counter);

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectOut1 = "Count of words: 4\n";

        assertThat(outputStream.toString(), CoreMatchers.containsString(expectOut1));
    }
}