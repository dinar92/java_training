package ru.job4j.multithread.problems;

/**
 * The implementation of the not thread safe object.
 */
public class NotThreadSafe {

    /**
     * Not synchronized variable.
     */
    private int num = 0;

    /**
     * Increments the variable.
     */
    public void increment() {
        this.num++;
    }

    /**
     * Prints the variable to the console.
     */
    public void printState() {
        System.out.println(num);
    }
}
