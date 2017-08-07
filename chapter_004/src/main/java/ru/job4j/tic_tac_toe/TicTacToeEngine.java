package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.GameEngine;
import ru.job4j.boardGame.Input;
import ru.job4j.boardGame.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pacman on 11.07.17.
 * The game engine for tic-tac-toe.
 */
public class TicTacToeEngine implements GameEngine {

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
    private final GameBoard board = new TicTacBoard();

    /**
     * The count of necessary cells for win.
     */
    private AtomicInteger countOfCells = new AtomicInteger(3);

    /**
     * The player 1, starts the game.
     */
    private AbstractPlayer player1 = new AbstractPlayer();

    /**
     * The player 2.
     */
    private AbstractPlayer player2 = new AbstractPlayer();

    /**
     * The winner.
     */
    private AbstractPlayer winner = new AbstractPlayer();

    /**
     * Game stages.
     */
    public final ArrayList<Stage> stages = new ArrayList<>();

    /**
     * The default constructor.
     *
     * @param input   relation with players.
     * @param display info for players.
     */
    public TicTacToeEngine(Input input, Display display) {
        this.input = input;
        this.display = display;
        this.verificator = new IntegerInputVerificator(input, display);
    }

    /**
     * Starts the bypass of game stages.
     */
    @Override
    public void start() {
        for (Stage stage : stages) {
            stage.showInfo();
            stage.action();
        }
    }

    /**
     * Initializes default game stages.
     *
     * @return instance of self.
     */
    @Override
    public GameEngine init() {
        this.stages.add(new WelcomeStage(this.input, this.display));
        this.stages.add(new SetBoardSize(this.display, this.verificator, this.board));
        this.stages.add(new SetDifficult(this.display, this.verificator, this.board, this.countOfCells));
        this.stages.add(new ChoosePlayer(this.input, this.display, this.verificator, this.board, this.countOfCells, this.player1, this.player2));
        this.stages.add(new Game(this.display, this.board, this.countOfCells, this.player1, this.player2, this.winner));
        this.stages.add(new WinnerDetermining(this.display, this.winner));
        return this;
    }
}
