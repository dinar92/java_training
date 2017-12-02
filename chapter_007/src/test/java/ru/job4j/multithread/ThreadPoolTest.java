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
        ThreadPool pool = new ThreadPool(4, 20);
        char c = 'a';
        for (int i = 0; i < 20; i++, c++) {
            pool.addWork(new Work(Character.toString(c)));
        }
        pool.setEnd();
    }

}