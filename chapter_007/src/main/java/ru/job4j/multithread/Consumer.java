package ru.job4j.multithread;

/**
 * The implementation of a consumer.
 */
public class Consumer implements Runnable {

    /**
     * The blocking queue.
     */
    private final SimpleBlockingQueue queue;

    /**
     * Sets the blocking queue.
     * @param queue - the queue.
     */
    public Consumer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    /**
     * Takes elements from the queue while the queue not returns null.
     */
    @Override
    public void run() {
        Object result;
        while ((result = queue.poll()) != null) {
            System.out.println(result);
        }
    }
}
