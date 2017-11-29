package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

/**
 * The simple implementation of the blocking queue like a FIFO principle.
 * @param <E> - the universal type.
 */
@ThreadSafe
public class SimpleBlockingQueue<E> {
    /**
     * The capacity of the queue.
     */
    private final int capacity;
    /**
     * The base container.
     */
    @GuardedBy("this")
    private ArrayList<E> list;
    /**
     * The end of process flag.
     */
    private volatile boolean endOfTheProcess = false;

    /**
     * Sets initial capacity of the queue.
     * @param capacity - initial capacity.
     */
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.list = new ArrayList<>(capacity);
    }

    /**
     * Enters an element in the queue.
     * If the queue is full, then waits for empty space.
     * @param element - an element.
     */
    public synchronized void offer(E element) {
        while (list.size() == capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(element);
        this.notify();
    }

    /**
     * Gets another item from the queue.
     * If the queue is empty, then wait for the element to be added.
     * After reaching the last element returns null.
     * @return - an element or null if the end.
     */
    public synchronized E poll() {
        while (list.size() == 0 && !this.endOfTheProcess) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        E result = null;
        if (!this.endOfTheProcess || list.size() != 0) {
            result = list.get(0);
            forwardShift();
            this.notify();
        }
        return result;
    }

    /**
     * Shifts array's elements forward for erase first element.
     */
    private void forwardShift() {
        ArrayList<E> newList = new ArrayList<>(capacity);
        for (int i = 0; i < list.size() - 1; i++) {
            newList.add(list.get(i + 1));
        }
        this.list = newList;
    }

    /**
     * The flag, that sets the end of the data transfer process.
     */
    public void disableOfProcess() {
        this.endOfTheProcess = true;
        synchronized (this) {
            this.notify();
        }
    }
}
