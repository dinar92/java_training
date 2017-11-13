package ru.job4j.multithread;

/**
 * The thread that counts words from text.
 */
public class WordCounter implements Runnable {
    /**
     * The text.
     */
    private final String text;

    /**
     * Sets the text.
     * @param text - the text.
     */
    public WordCounter(String text) {
        this.text = text;
    }

    /**
     * Counts the words from text.
     */
    @Override
    public void run() {
        int charCounter = 0;
        int wordCounter = 0;
        char space = ' ';
        while (charCounter < text.length()) {
            while (charCounter < text.length() && text.charAt(charCounter) != space) {
                charCounter++;
            }
            wordCounter++;
            charCounter++;
        }
        System.out.println(String.format("Count of words: %s", wordCounter));
    }
}
