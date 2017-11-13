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
     * @param text - the text.
     */
    public TextBreaker(String text) {
        this.text = text;
    }

    /**
     * Starts counting by spaces and words from the text.
     * Result will be print to the console.
     */
    public void run() {
        List<Thread> store = new ArrayList<>();
        store.add(new Thread(new WordCounter(this.text)));
        store.add(new Thread(new SpaceCounter(this.text)));

        for (Thread thread : store) {
            thread.start();
        }
    }
}
