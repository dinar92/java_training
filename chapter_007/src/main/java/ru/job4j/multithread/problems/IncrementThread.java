package ru.job4j.multithread.problems;

/**
 * Created by user on 20.11.17..
 * Invokes increment method from not thread safe object.
 */
public class IncrementThread implements Runnable {

    /**
     * The not thread safe object.
     */
    private NotThreadSafe notThreadSafe;

    /**
     * Sets the not thread safe object
     * @param notThreadSafe - the object.
     */
    public IncrementThread(NotThreadSafe notThreadSafe) {
        this.notThreadSafe = notThreadSafe;
    }

    /**
     * Increments the object's state 50 times,
     * every 2 milliseconds.
     */
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            this.notThreadSafe.increment();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
