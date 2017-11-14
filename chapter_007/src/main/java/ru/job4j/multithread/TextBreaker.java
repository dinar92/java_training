package ru.job4j.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * The text breaker.
 */
public class TextBreaker {

    /**
     * The text.
     */
    private final String text;

    /**
     * Sets the text.
     *
     * @param text - the text.
     */
    public TextBreaker(String text) {
        this.text = text;
    }


    private long maxTimeOfRunning = 0L;
    private boolean willBeInterrupt = false;

    /**
     * Starts counting by spaces and words from the text.
     * Result will be print to the console.
     */
    public void run() {
        List<Thread> store = new ArrayList<>();
        store.add(new Thread(new WordCounter(this.text)));
        store.add(new Thread(new SpaceCounter(this.text)));

        System.out.println("Processing start...");

        for (Thread thread : store) {
            thread.start();
        }

        if (willBeInterrupt) {
            try {
                Thread.sleep(maxTimeOfRunning);
                for (Thread thread : store) {
                    if (thread.isAlive()) {
                        thread.interrupt();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            for (Thread thread : store) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processing finish!");
    }

    public void interruptIfTooLong(long maxTimeInMillis) {
        this.maxTimeOfRunning = maxTimeInMillis;
        willBeInterrupt = true;
    }
}
