package ru.job4j.multithread;

/**
 * The producer of data thread.
 */
public class Producer implements Runnable {
    /**
     * The blocking queue.
     */
    private final SimpleBlockingQueue queue;

    /**
     * Sets the blocking queue.
     * @param queue - the queue.
     */
    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    /**
     * Puts 1000 elements in the queue.
     */
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            queue.offer(new Integer(i));
        }
        queue.disableOfProcess();
    }
}
