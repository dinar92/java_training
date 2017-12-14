package ru.job4j.multithread.bomberman;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * A launcher of "The bomberman" game.
 */
public class Launcher {
    /**
     * Difficulty of the game.
     */
    private final Difficulty difficulty;
    /**
     * The game board.
     */
    private final BlockBoard board;
    /**
     * The side size of the game board.
     */
    private final int sizeOfBoard;
    /**
     * Board's fill percentage of easy game.
     */
    private final static double PERCENT_OF_EASY = 0.1;
    /**
     * Board's fill percentage of not so easy game.
     */
    private final static double PERCENT_OF_MEDIUM = 0.2;
    /**
     * Board's fill percentage of hard game.
     */
    private final static double PERCENT_OF_HARD = 0.3;

    /**
     * The storage of all active monsters.
     */
    private final Queue<Runnable> monstersStorage;

    /**
     * Sets the game parameters.
     *
     * @param difficulty  - difficulty of the game.
     * @param sizeOfBoard - size of board.
     */
    public Launcher(Difficulty difficulty, int sizeOfBoard) {
        this.difficulty = difficulty;
        this.board = new BlockBoard(sizeOfBoard);
        this.sizeOfBoard = sizeOfBoard;
        this.monstersStorage = new ArrayDeque<>();
    }

    /**
     * Start point of the game.
     */
    public void startGame() {
        int busyCells;
        if (difficulty == Difficulty.EASY) {
            busyCells = (int) Math.ceil(sizeOfBoard * PERCENT_OF_EASY);
        } else if (difficulty == Difficulty.MEDIUM) {
            busyCells = (int) Math.ceil(sizeOfBoard * PERCENT_OF_MEDIUM);
        } else {
            busyCells = (int) Math.ceil(sizeOfBoard * PERCENT_OF_HARD);
        }
        this.board.init();
        this.monsterFactory(busyCells);
        this.blockFactory(busyCells);
    }

    /**
     * Creates the hero.
     * Start position on the board is always: x = 0, y = 0.
     * May be called only after start the current game.
     *
     * @return new hero.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public Hero getHero() throws BoardIsNotInitException {
        return new Hero(this.board, 0, 0);
    }

    /**
     * The factory of monsters with specified count.
     *
     * @param countOfMonsters - the count of monsters, what must be generated.
     */
    private void monsterFactory(int countOfMonsters) {
        for (int i = 0; i < countOfMonsters; i++) {
            Cell randomCell = this.getRandomCell();
            Monster newMonster = null;
            try {
                newMonster = new Monster(this.board, randomCell.getXAxis(), randomCell.getYAxis());
            } catch (BoardIsNotInitException boardIsNotInitException) {
                boardIsNotInitException.printStackTrace();
            }
            this.monstersStorage.offer(newMonster);
        }
    }

    /**
     * /**
     * The factory of blocks with specified count.
     *
     * @param countOfBlocks - the count of blocks, what must be generated.
     */
    private void blockFactory(int countOfBlocks) {
        for (int i = 0; i < countOfBlocks; i++) {
            Cell randomCell = this.getRandomCell();
            try {
                this.board.setBlock(randomCell.getXAxis(), randomCell.getYAxis());
            } catch (BoardIsNotInitException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Returns the random cell of the game board.
     *
     * @return - the random cell.
     */
    private Cell getRandomCell() {
        Cell cell = null;
        Random generator = new Random();
        int randomX = generator.nextInt(this.sizeOfBoard);
        int randomY = generator.nextInt(this.sizeOfBoard);
        try {
            cell = this.board.getCell(randomX, randomY);
        } catch (BoardIsNotInitException boardIsNotInitException) {
            boardIsNotInitException.printStackTrace();
        }
        return cell;
    }

    /**
     * Stops the current game.
     */
    public void stopGame() {
        for (Runnable thread : this.monstersStorage) {
            Monster monster = (Monster) thread;
            monster.stopMonster();
        }
    }
}
