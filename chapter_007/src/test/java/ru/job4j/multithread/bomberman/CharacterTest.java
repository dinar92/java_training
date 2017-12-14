package ru.job4j.multithread.bomberman;

import org.junit.Test;

/**
 * Tests the class Character.
 */
public class CharacterTest {

    /**
     * Tests action.
     */
    @Test
    public void whenStartCharacterThenMove() {
        Board board = new Board(10);
        board.init();
        Thread hero1 = null;
        Thread hero2 = null;
        Thread hero3 = null;
        Thread hero4 = null;
        try {
            hero1 = new Character(board, 0, 0);
            hero2 = new Character(board, 0, 9);
            hero3 = new Character(board, 9, 0);
            hero4 = new Character(board, 9, 9);
        } catch (BoardIsNotInitException ex) {
            ex.printStackTrace();
        }
        hero1.start();
        hero2.start();
        hero3.start();
        hero4.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hero1.interrupt();
        hero2.interrupt();
        hero3.interrupt();
        hero4.interrupt();

        try {
            hero1.join();
            hero2.join();
            hero3.join();
            hero4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}