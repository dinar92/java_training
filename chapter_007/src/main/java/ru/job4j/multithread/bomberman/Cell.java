package ru.job4j.multithread.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The cell implementation of the Bomberman Game board.
 */
public class Cell {

    /**
     * The cell's lock.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * Cell's x-axis coordinate.
     */
    private final int xAxis;
    /**
     * Cell's y-axis coordinate.
     */
    private final int yAxis;

    /**
     * Sets cell's coordinates on the game board.
     * @param xAxis - x-axis.
     * @param yAxis - y-axis.
     */
    public Cell(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    /**
     * Acquires the lock if it is free within the given waiting time.
     * @param timeInMilliseconds - waiting time in milliseconds.
     * @return - true - if the lock was acquired and false - if the waiting time elapsed before the lock was acquired.
     * @throws InterruptedException  - if the current thread is interrupted while acquiring the lock.
     */
    public boolean tryLock(long timeInMilliseconds) throws InterruptedException {
        return this.lock.tryLock(timeInMilliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Releases the cell.
     */
    public void unlock() {
        this.lock.unlock();
    }

    /**
     * Returns the x-axis coordinate. Start from 0.
     * @return - x-axis.
     */
    public int getXAxis() {
        return this.xAxis;
    }

    /**
     * Returns the y-axis coordinate. Start from 0.
     * @return - y-axis.
     */
    public int getYAxis() {
        return this.yAxis;
    }
}
