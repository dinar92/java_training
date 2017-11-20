package ru.job4j.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * The text handler.
 */
public class TextHandler {

    /**
     * The text.
     */
    private final String text;

    /**
     * Sets the text.
     *
     * @param text - the text.
     */
    public TextHandler(String text) {
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

        System.out.println("Processing start...");

        for (Thread thread : store) {
            thread.start();
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
}
