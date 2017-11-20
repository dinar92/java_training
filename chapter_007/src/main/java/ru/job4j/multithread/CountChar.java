package ru.job4j.multithread;

/**
 * The chars counter from a string.
 */
public class CountChar implements Runnable {

    /**
     * The string for counting.
     */
    private final String text;

    /**
     * Sets the string for counting.
     * @param text - the text.
     */
    public CountChar(String text) {
        this.text = text;
    }

    /**
     * Starts the thread for counting chars in the text.
     * Prints result in the console.
     */
    @Override
    public void run() {
        int counter = 0;
        while (counter < text.length()) {
            if (Thread.interrupted()) {
                System.out.println("Was interrupt");
                return;
            }
            counter++;
        }
        System.out.println(String.format("Count of chars: %s", counter));
    }
}
