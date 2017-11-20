package ru.job4j.multithread;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Tests The class CountChar.
 */
public class CountCharTest {

    /**
     * Tests the CountChar thread's output to the console.
     */
    @Test
    public void whenStartCounterThenPrintsInConsoleCountOfChars() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String quote = "When a father gives to his son, both laugh; when a son gives to his father, both cry.";
        Thread counter = new Thread(new CountChar(quote));

        counter.start();
        try {
            counter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(outputStream.toString(), is(String.format("Count of chars: %s\n", quote.length())));
    }

}