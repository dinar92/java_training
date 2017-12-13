package ru.job4j.multithread;

/**
 * The implementation of the lock object.
 */
public class SimpleLock {

    /**
     * A status of the lock.
     */
    private boolean isBlocked = false;

    /**
     * Locks the code for another threads.
     */
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (isBlocked) {
                    wait();
            }
            isBlocked = true;
        }
    }

    /**
     * Unlocks the code and wakes up waiting thread.
     */
    public void unlock() {
        synchronized (this) {
            isBlocked = false;
            notify();
        }
    }
}
