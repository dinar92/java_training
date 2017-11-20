package ru.job4j.multithread.problems;

public class Visibility implements Runnable {

    private NotThreadSafe notThreadSafe;

    public Visibility(NotThreadSafe notThreadSafe) {
        this.notThreadSafe = notThreadSafe;
    }

    @Override
    public void run() {
        this.notThreadSafe.printState();
    }
}
