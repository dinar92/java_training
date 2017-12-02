package ru.job4j.multithread;

import org.junit.Test;

/**
 * Tests the class ThreadPool.
 */
public class ThreadPoolTest {

    /**
     * Demonstrates working.
     */
    @Test
    public void whenStartPoolThenStartWorksInThreads() {
        ThreadPool pool = new ThreadPool(4, 4);
        pool.addWork(new Work("a"));
        pool.addWork(new Work("b"));
        pool.addWork(new Work("c"));
        pool.addWork(new Work("d"));
        pool.setEnd();
    }

}