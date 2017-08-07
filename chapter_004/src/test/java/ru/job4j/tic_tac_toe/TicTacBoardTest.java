package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.State;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 23.07.17.
 * Tests class TicTacBoard.
 */
public class TicTacBoardTest {

    /**
     * The instance of exception.
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Tests init().
     */
    @Test
    public void whenInitTheBoardShouldCreateSpecifiedBoard() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;

        board.init(boardSize);

        Assert.assertThat(board.getSize(), is(boardSize));
    }

    /**
     * Tests getState(String coordinate).
     */
    @Test
    public void whenInitBoardShouldContainsEmptyCells()  {
        GameBoard board = new TicTacBoard();
        int boardSize = 4;
        String coordinate = "a1";
        State expectedState = TicTacState.EMPTY;

        board.init(boardSize);

        Assert.assertThat(board.getState(coordinate), is(expectedState));
    }

    /**
     * Tests exception getState(String coordinate),
     * when specified coordinate is not exist.
     */
    @Test
    public void whenGetNotExistCellShouldThrowException()  {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        String coordinate = "z9";

        board.init(boardSize);

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Such cell is not exist!");
        board.getState(coordinate);
    }

    /**
     * Tests setState(String coordinate, State state).
     */
    @Test
    public void whenSetStateThenBoardMustBeChange() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        String cellForChange = "a1";
        State stateToChange = TicTacState.CROSS;

        board.init(boardSize);
        board.setState(cellForChange, stateToChange);

        Assert.assertThat(board.getState(cellForChange), is(stateToChange));
    }

    /**
     * Tests setState(String coordinate, State state),
     * when specified coordinate is not exist.
     */
    @Test
    public void whenSetStateToNonExistCellThenThrowException() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        String cellForChange = "z8";
        State stateToChange = TicTacState.CROSS;

        board.init(boardSize);

        this.exception.expect(IllegalArgumentException.class);
        this.exception.expectMessage("Such cell is not exist!");
        board.setState(cellForChange, stateToChange);
    }


}
