package ru.job4j.multithread;

/**
 * The thread that counts spaces from the text.
 */
public class SpaceCounter implements Runnable {

    /**
     * The text.
     */
    private String text;

    /**
     * Sets the text.
     * @param text - the text.
     */
    public SpaceCounter(String text) {
        this.text = text;
    }

    /**
     * Counts the spaces from text.
     */
    @Override
    public void run() {
        int charCounter = 0;
        int spaceCounter = 0;
        char space = ' ';
        while (charCounter < text.length()) {
            if (text.charAt(charCounter) == space) {
                spaceCounter++;
            }
            charCounter++;
        }
        System.out.println(String.format("Count of spaces: %s", spaceCounter));
    }
}
