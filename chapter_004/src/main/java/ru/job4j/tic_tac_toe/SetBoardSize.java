package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Stage;

/**
 * Sets size of a game board.
 */
class SetBoardSize implements Stage {

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
     * Default constructor.
     *
     * @param verificator verificator.
     * @param display     display method.
     * @param board       game board.
     */
    SetBoardSize(Display display, NumberInputVerificator verificator, GameBoard board) {
        this.display = display;
        this.verificator = verificator;
        this.board = board;
    }

    /**
     * Shows info about the current status of the game.
     */
    @Override
    public void showInfo() {
        display.showln("");
        display.showln("The board can be sized from 3 to 15 inclusive.");
    }

    /**
     * The response on player's enter.
     */
    @Override
    public void action() {
        int minLimit = 3;
        int maxLimit = 15;
        int choice = verificator.getVerifiedAnswer("Please, enter size:", minLimit, maxLimit);
        board.init(choice);
    }
}