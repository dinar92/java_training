package ru.job4j.menu;

/**
 * Created by pacman on 11.05.17.
 * Generates unique sequence of integers.
 */
public class KeyGeneratorForMenuItems implements KeyGenerator {

    /**
     * A counter.
     */
    private int counter = 0;

    /**
     * Generates the next integer in order each time it is invoked.
     * @return the generated integer.
     */
    @Override
    public int getNextKey() {
        return ++this.counter;
    }

    /**
     * Resets to initial value.
     */
    public void reset() {
        this.counter = 0;
    }
}
