package ru.job4j.multithread;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class Count.
 */
public class CountTest {

    /**
     * Launches a thousand thread in order. Each thread
     * increments and prints the state of Count.
     */
    @Test
    public void whenRunManyThreadsThenSynchronizedWithoutProblems() {
        Count count = new Count();
        List<Thread> threads = new LinkedList<>();
        int expectFinalVar = 1001;

        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(count.increment());
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MatcherAssert.assertThat(count.increment(), is(expectFinalVar));
    }

}