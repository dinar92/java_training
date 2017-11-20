package ru.job4j.multithread.problems;

public class VisibilityRunner {

    public void run() {
        NotThreadSafe notThreadSafe = new NotThreadSafe();

        for (int i = 0; i < 1000; i++) {
            new Thread(new RaceConditionThread(notThreadSafe)).start();
            new Thread(new Visibility(notThreadSafe)).start();
        }
    }
}
