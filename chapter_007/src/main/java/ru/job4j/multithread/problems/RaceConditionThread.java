package ru.job4j.multithread.problems;

/**
 * A race condition provoking thread.
 */
public class RaceConditionThread implements Runnable {

    /**
     * The instance of the not thread safe object.
     */
    private final NotThreadSafe thread;

    /**
     * Sets the not thread safe object.
     *
     * @param thread - thread.
     */
    public RaceConditionThread(NotThreadSafe thread) {
        this.thread = thread;
    }

    /**
     * Logic of the thread.
     */
    @Override
    public void run() {
        thread.increment();
    }
}
