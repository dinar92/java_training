package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.ComputerStrategy;
import ru.job4j.boardGame.GameBoard;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pacman on 18.07.17.
 * Implementation of the simple strategy of a robot playing.
 */
public class SimpleStrategy implements ComputerStrategy {

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
     * Contains the coordinates of cells that make up the strategy.
     */
    private ArrayList<String> cellsOfStrategy = new ArrayList<String>();

    /**
     * Cells finder.
     */
    ConsecutiveCellsFinder finder = new ConsecutiveCellsFinder();

    /**
     * Sets the board, role of computer and difficult of the game.
     *
     * @param board        the game board.
     * @param state        the rolee of computer.
     * @param countOfCells count of necessary cells for win.
     */
    public SimpleStrategy(GameBoard board, TicTacState state, AtomicInteger countOfCells) {
        this.board = board;
        this.state = state;
        this.countOfCells = countOfCells;
        this.cellsOfStrategy = this.newStrategy();
    }

    /**
     * Returns the cell's coordinate for moving.
     *
     * @return coordinate.
     */
    @Override
    public String getMove() {
        String move = "";
        if (!this.checkStrategy(this.cellsOfStrategy)) {
            this.cellsOfStrategy = this.newStrategy();
        }
        move = this.cellsOfStrategy.get(0);
        this.cellsOfStrategy.remove(0);
        return move;
    }

    /**
     * Checks the current strategy for relevance.
     *
     * @param cellsOfStrategy cells.
     * @return true - if current strategy is correct, false - if obstacle on the strategy way.
     */
    private boolean checkStrategy(ArrayList<String> cellsOfStrategy) {
        for (String cell : cellsOfStrategy) {
            if (!this.board.getState(cell).equals(this.state) && !this.board.getState(cell).equals(TicTacState.EMPTY)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates new strategy and places generated cells to the current strategy.
     * If the available correct moves is ended, then returns first empty cell.
     *
     * @return new move strategy.
     */
    public ArrayList<String> newStrategy() {

        ArrayList<String> strategy = finder.checkAllLines(this.board, this.countOfCells, this.state, TicTacState.EMPTY);
        if (strategy.size() == 0) {
            strategy = finder.checkAllLines(this.board, new AtomicInteger(1), TicTacState.EMPTY);
        }
        return strategy;
    }
}
