package ru.job4j.tic_tac_toe;


import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Input;
import ru.job4j.boardGame.Player;
import ru.job4j.boardGame.State;

/**
 * Created by pacman on 13.07.17.
 * User.
 */
public class User implements Player {

    /**
     * An instance of game board.
     */
    private final GameBoard board;

    /**
     * Provides the user's enter.
     */
    private final Input input;

    /**
     * The player's role.
     */
    private final State state;

    /**
     * The player's name.
     */
    private String name;

    /**
     * Sets a board and an input method.
     *
     * @param input input.
     * @param board board.
     * @param name  name of the player.
     * @param state - a state of the player.
     */
    public User(GameBoard board, Input input, State state, String name) {
        this.board = board;
        this.input = input;
        this.state = state;
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
     * The player's moving.
     */
    @Override
    public void doMove() {
        String input;
        boolean isCorrect;
        do {
            try {
                input = this.input.getAnswer("Enter the cell's coordinate (a1 | b2 |...):");
                if (this.board.getState(input) == TicTacState.EMPTY) {
                    this.board.setState(input, this.state);
                    isCorrect = true;
                } else {
                    System.out.println("This cell isn't empty. Try again.");
                    isCorrect = false;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Cell isn't exist. Try again!");
                isCorrect = false;
            }
        } while (!isCorrect);
    }
}
