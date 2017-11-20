package ru.job4j.multithread;

/**
 * Threads by time interrupter.
 */
public class Time implements Runnable {

    /**
     * Thread for interruption.
     */
    private final Thread thread;

    /**
     * The time after which an interruption will occur.
     */
    private long milliseconds;

    /**
     * Sets thread and
     * sets the time in milliseconds.
     * By default - 0 ms.
     * @param thread - the thread for interruption.
     * @param milliseconds - the time.
     */
    public Time(Thread thread, long milliseconds) {
        this.thread = thread;
        this.milliseconds = milliseconds;
    }

    /**
     * Interrupts the specified thread after timeout.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(milliseconds);
            if (thread.isAlive()) {
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
