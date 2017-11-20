package ru.job4j.multithread.problems;

import java.util.LinkedList;
import java.util.List;

/**
 * Launches a thousand thread in order.
 * Provoking race condition.
 * At the end prints the total value of the shared variable.
 */
public class Runner {

    /**
     * Starts action.
     */
    public void run() {
        NotThreadSafe nonSafeThread = new NotThreadSafe();
        List<Thread> threads = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new RaceConditionThread(nonSafeThread)));
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

        nonSafeThread.printState();
    }
}
