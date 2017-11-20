package ru.job4j.multithread;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class Time.
 */
public class TimeTest {

    /**
     * The simple endless thread with interrupt check.
     */
    Thread endlessThread = new Thread(new Runnable() {
        @Override
        public void run() {
            int counter = 0;
            while (!Thread.interrupted()) {
                counter++;
            }
        }
    });

    /**
     * Starts the endless thread and the Time thread stops him.
     */
    @Test
    public void whenStartThreadWithTimerThenHeIsDeathAfterTimeout() {
        long timeout = 10L;
        Thread timer = new Thread(new Time(endlessThread, timeout));

        endlessThread.start();
        timer.start();

        try {
            Thread.sleep(timeout + timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(endlessThread.isAlive(), is(false));
    }

    /**
     * Tests self interruption branch of the Time.
     */
    @Test
    public void whenTimeWasInterruptThenGetMessageToConsole() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        long timeout = 100000000000L;
        String expectMessage = "Interrupted\n";
        Thread timer = new Thread(new Time(endlessThread, timeout));

        endlessThread.start();
        timer.start();
        timer.interrupt();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(outputStream.toString(), is(expectMessage));
    }

    /**
     * Tests when the thread into the Time is not alive.
     */
    @Test
    public void whenThreadIntoTimerIsNotAliveThenNothing() {
        long timeout = 1000;
        Thread timer = new Thread(new Time(endlessThread, timeout));

        endlessThread.start();
        timer.start();
        endlessThread.interrupt();

        try {
            Thread.sleep(timeout + timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(endlessThread.isAlive(), is(false));
    }
}