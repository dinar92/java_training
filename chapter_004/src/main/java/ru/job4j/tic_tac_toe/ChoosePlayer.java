package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Input;
import ru.job4j.boardGame.Stage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Choose a start game player.
 */
public class ChoosePlayer implements Stage {

    /**
     * Provides the relation with players.
     */
    private final Input input;

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
     * The player 1, starts the game.
     */
    private AbstractPlayer player1;

    /**
     * The player 2.
     */
    private AbstractPlayer player2;

    /**
     * Default constructor.
     *
     * @param input        input method.
     * @param verificator  verificator.
     * @param display      display method.
     * @param board        game board.
     * @param countOfCells the cunt of cells.
     * @param player1      first player.
     * @param player2      second player.
     */
    public ChoosePlayer(Input input, Display display, NumberInputVerificator verificator, GameBoard board, AtomicInteger countOfCells, AbstractPlayer player1, AbstractPlayer player2) {
        this.input = input;
        this.display = display;
        this.verificator = verificator;
        this.board = board;
        this.countOfCells = countOfCells;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Shows info about the current status of the game.
     */
    @Override
    public void showInfo() {
        this.display.showln("");
        this.display.showln("Who will be the first?");
        this.display.showln("1 - you, 2 - computer");
    }

    /**
     * The response on player's enter.
     * If was enter 1, then user do first move and has the cross state.
     * If was enter 2, then computer do first move and has the cross state.
     */
    @Override
    public void action() {
        int choice = this.verificator.getVerifiedAnswer("Select the first player [1/2]:", 1, 2);
        if (choice == 1) {
            this.player1.setPlayer(new User(this.board, this.input, TicTacState.CROSS, "You"));
            this.player2.setPlayer(new Computer(this.board, TicTacState.NAUGHT, this.countOfCells, "Terminator"));
        } else {
            this.player1.setPlayer(new Computer(this.board, TicTacState.CROSS, this.countOfCells, "Terminator"));
            this.player2.setPlayer(new User(this.board, this.input, TicTacState.NAUGHT, "You"));
        }
    }
}