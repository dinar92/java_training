package ru.job4j.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * The simple implementation of the thread pool.
 */
public class ThreadPool {

    /**
     * The blocking queue of works.
     */
    private final SimpleBlockingQueue<Work> worksQueue;
    /**
     * The list of worker threads.
     */
    private final List<Thread> listOfThreads = new ArrayList<>();
    /**
     * The end of the processing flag.
     */
    private volatile boolean isEnd = false;

    /**
     * Sets max count of possible works,
     * which will be processed in parallel;
     * starts threads.
     * @param maxCountOfWorks - the max count of works.
     */
    public ThreadPool(int maxCountOfWorks) {
        this.worksQueue = new SimpleBlockingQueue<>(maxCountOfWorks);
    }

    /**
     * Initializes the number of threads by the number of processors.
     */
    public void init() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            this.listOfThreads.add(new Thread(new WorkThread()));
        }

        for (Thread thread : this.listOfThreads) {
            thread.start();
        }
    }

    /**
     * Adds new work.
     * @param work - work.
     */
    public void addWork(Work work) {
        this.worksQueue.offer(work);
    }

    /**
     * Sets the end of pool's process and
     * interrupts all threads.
     */
    public void setEnd() {
        this.isEnd = true;
        for (Thread thread : listOfThreads) {
            thread.interrupt();
        }
    }

    /**
     * The thread which will do work.
     */
    private class WorkThread implements Runnable {

        /**
         * Polls the work from the blocking works queue.
         */
        @Override
        public void run() {
            while (!isEnd) {
                worksQueue.poll().doSomething();
            }
        }
    }
}
