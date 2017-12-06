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
        private final SimpleLock lock = new SimpleLock();

        public int i = 0;

        public void increment() {
            try {
                lock.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            lock.unlock();
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
            for (int i = 0; i < 100; i++) {
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

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertThat(increment.i, is(200));
    }
}