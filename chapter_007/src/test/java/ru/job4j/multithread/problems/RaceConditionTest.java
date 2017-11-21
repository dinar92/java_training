package ru.job4j.multithread.problems;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RaceConditionTest {

    /**
     * Launches a thousand thread in order.
     * Provoking race condition.
     * At the end prints the total value of the shared variable.
     */
    @Test
    public void whenRunTwoThreadsThenRaceConditionArise() {
        NotThreadSafe notThreadSafe = new NotThreadSafe();
        List<Thread> threads = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new RaceConditionThread(notThreadSafe)));
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

        notThreadSafe.printState();
    }
}
