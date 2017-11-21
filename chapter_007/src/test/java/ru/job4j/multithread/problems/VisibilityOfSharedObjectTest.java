package ru.job4j.multithread.problems;

import org.junit.Test;

/**
 * Invokes visibility of shared object.
 */
public class VisibilityOfSharedObjectTest {

    /**
     * With a consecutive increase in the number, we receive not consecutive values of object in other thread.
     * Invokes two thread.
     * First thread increments the object's state 50 times,
     * every 2 milliseconds.
     * Second - invokes the object's variable printing method 10000 times.
     */
    @Test
    public void whenOneThreadIncrementsSecondShowStateThenDisplayTrouble() {
        NotThreadSafe notThreadSafe = new NotThreadSafe();
        Thread incrementer = new Thread(new IncrementThread(notThreadSafe));
        Thread printer = new Thread(new DisplayThread(notThreadSafe));

        incrementer.start();
        printer.start();

        try {
            incrementer.join();
            printer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
