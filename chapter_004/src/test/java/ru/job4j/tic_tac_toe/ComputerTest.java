package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Player;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 25.07.17.
 * Tests class Computer.
 */
public class ComputerTest {

    /**
     * The game board.
     */
    private final GameBoard board = new TicTacBoard();

    /**
     *The state of the computer.
     */
    private final TicTacState state = TicTacState.CROSS;

    /**
     * Count of necessary cells for win.
     */
    private AtomicInteger countOfCells = new AtomicInteger(3);

    /**
     * The nickname of the computer.
     */
    private final String name = "R2D2";

    /**
     * Instance of the computer.
     */
    private final Player computer = new Computer(this.board, this.state, this.countOfCells, this.name);

    /**
     * Tests getState().
     */
    @Test
    public void whenGetStateShouldReturnedState() {
        TicTacState expectState = TicTacState.CROSS;

        Assert.assertThat(this.computer.getState(), is(expectState));
    }

    /**
     * Tests getName().
     */
    @Test
    public void whenToStringShouldGetName() {
        String expectName = "R2D2";

        Assert.assertThat(this.computer.toString(), is(expectName));
    }

    /**
     * Tests doMove().
     */
    @Test
    public void whenDoMoveShouldSetsStateOnBoard() {
        String firstMoveCoordinate = "a1";
        int sizeOfBoard = 3;

        //Creating clear board.
        this.board.init(sizeOfBoard);

        Computer computer = new Computer(this.board, this.state, countOfCells, name);

        computer.doMove();

        Assert.assertThat(this.board.getState(firstMoveCoordinate), is(this.state));
    }
}
