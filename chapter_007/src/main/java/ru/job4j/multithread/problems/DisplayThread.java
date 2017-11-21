package ru.job4j.multithread.problems;

/**
 * Invokes the method, which prints the not thread safe object's state to the console.
 */
public class DisplayThread implements Runnable {

    /**
     * The not thread safe object.
     */
    private NotThreadSafe notThreadSafe;

    /**
     * Sets the not thread safe object.
     * @param notThreadSafe - the object.
     */
    public DisplayThread(NotThreadSafe notThreadSafe) {
        this.notThreadSafe = notThreadSafe;
    }

    /**
     * Invokes the object's variable printing method 10000 times.
     */
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            this.notThreadSafe.printState();
        }
    }
}
