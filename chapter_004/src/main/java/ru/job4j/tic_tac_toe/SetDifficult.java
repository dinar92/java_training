package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Stage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides choice of the game difficult.
 */
class SetDifficult implements Stage {

    /**
     * Provides the info for players.
     */
    private final Display display;

    /**
     * Checking user input for a integer range.
     */
    private final NumberInputVerificator verificator;

    /**
     * The game board.
     */
    private final GameBoard board;

    /**
     * The count of necessary cells for win.
     */
    private AtomicInteger countOfCells;

    /**
     * Default constructor.
     *
     * @param verificator  verificator.
     * @param display      display method.
     * @param board        game board.
     * @param countOfCells the count of cells.
     */
    SetDifficult(Display display, NumberInputVerificator verificator, GameBoard board, AtomicInteger countOfCells) {
        this.display = display;
        this.verificator = verificator;
        this.board = board;
        this.countOfCells = countOfCells;
    }

    /**
     * Shows info about the current status of the game.
     */
    @Override
    public void showInfo() {
        if (board.getSize() > 4) {
            display.showln("");
            display.showln("The game can be even harder!");
            display.showln("1 - standard, 2 - hard");
        }
    }

    /**
     * The response on player's enter.
     */
    @Override
    public void action() {
        int difficult = 1;
        if (board.getSize() > 4) {
            difficult = verificator.getVerifiedAnswer("Please, choose the difficulty of the game [1/2]:", 1, 2);
        }
        this.countOfCells.set(this.cellsCount(difficult));
    }

    /**
     * Returns the count of necessary cells for win.
     *
     * @param difficult the difficult of the game.
     * @return count of necessary cells for win.
     */
    private int cellsCount(int difficult) {
        return (difficult == 2) ? 5 : 3;
    }
}