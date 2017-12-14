package ru.job4j.multithread.bomberman;

import org.junit.Test;

public class MonsterTest {

    @Test
    public void whenStartCharacterThenMove() {
        Board board = new Board(10);
        board.init();
        Monster monster1 = null;
        Monster monster2 = null;
        Monster monster3 = null;
        Monster monster4 = null;
        try {
            monster1 = new Monster(board, 0, 0);
            monster2 = new Monster(board, 0, 9);
            monster3 = new Monster(board, 9, 0);
            monster4 = new Monster(board, 9, 9);
        } catch (BoardIsNotInitException ex) {
            ex.printStackTrace();
        }
        monster1.start();
        monster2.start();
        monster3.start();
        monster4.start();

        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        monster1.stopMonster();
        monster2.stopMonster();
        monster3.stopMonster();
        monster4.stopMonster();

        try {
            monster1.join();
            monster2.join();
            monster3.join();
            monster4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}