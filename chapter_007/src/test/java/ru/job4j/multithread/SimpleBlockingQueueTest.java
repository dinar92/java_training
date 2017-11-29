package ru.job4j.multithread;

import org.junit.Test;

/**
 * Tests the class SimpleBlockingQueue.
 */
public class SimpleBlockingQueueTest {

    /**
     * Checks the blocking queue working.
     */
    @Test
    public void whenProducedThenConsumed() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread t1 = new Thread(new Producer(queue));
        Thread t2 = new Thread(new Consumer(queue));

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}