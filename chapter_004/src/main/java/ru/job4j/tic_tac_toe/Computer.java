package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.ComputerStrategy;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Player;
import ru.job4j.boardGame.State;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pacman on 13.07.17.
 * The simple robot for playing tic-tac-toe.
 */
public class Computer implements Player {

    /**
     * The game board.
     */
    private final GameBoard board;

    /**
     * The role.
     */
    private final TicTacState state;

    /**
     * The count of necessary cells for win.
     */
    private AtomicInteger countOfCells;

    /**
     * Default strategy of the game - a simple robot.
     */
    private ComputerStrategy strategy;

    /**
     * The player's name.
     */
    private String name;

    /**
     * Sets the board, role of computer and difficult of the game.
     * Sets default strategy generator.
     *
     * @param board        game board.
     * @param state        state of the game.
     * @param countOfCells - count of necessary cells for win.
     * @param name         name.
     */
    public Computer(GameBoard board, TicTacState state, AtomicInteger countOfCells, String name) {
        this.board = board;
        this.state = state;
        this.countOfCells = countOfCells;
        this.strategy = new SimpleStrategy(this.board, this.state, this.countOfCells);
        this.name = name;
    }


    /**
     * Returns the state of the player.
     *
     * @return the current state.
     */
    public State getState() {
        return this.state;
    }

    /**
     * Returns player's name.
     *
     * @return name.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Provides to set more clever strategy for the computer.
     *
     * @param strategy strategy of the game.
     */
    public void setStrategy(ComputerStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * The player's moving.
     *
     * @return the coordinates of the move.
     */
    @Override
    public void doMove() {
        this.board.setState(this.strategy.getMove(), this.state);
    }

}
