package ru.job4j.multithread;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Tests the class SimpleLock.
 */
public class SimpleLockTest {

    /**
     * The class that uses the SimpleLock.
     */
    public class Increment {

        private final SimpleLock lock = new SimpleLock();  //Ограничен доступ к локу!

        public int i = 0;

        public void increment() {
            try {
                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * The thread that changes the state of locked class (Increment).
     */
    public class Incrementer extends Thread {
        private final Increment increment;

        Incrementer(Increment increment) {
            this.increment = increment;
        }

        public void run() {

            for (int i = 0; i < 1000; i++) {
                increment.increment();
            }

        }
    }

    /**
     * Tests lock(), unlock().
     */
    @Test
    public void whenCodeLockedThenLocked() {
        Increment increment = new Increment();
        Thread t1 = new Incrementer(increment);
        Thread t2 = new Incrementer(increment);
        Thread t3 = new Incrementer(increment);
        Thread t4 = new Incrementer(increment);
        Thread t5 = new Incrementer(increment);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(increment.i, is(5000));
    }
}