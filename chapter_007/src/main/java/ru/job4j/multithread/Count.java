package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * The thread safe counter.
 * Increments the self state.
 */
@ThreadSafe
public class Count {

    /**
     * The state.
     */
    @GuardedBy("this") private int value = 0;

    /**
     * Increments the state.
     * @return the state.
     */
    public synchronized int increment() {
        return ++value;
    }
}
