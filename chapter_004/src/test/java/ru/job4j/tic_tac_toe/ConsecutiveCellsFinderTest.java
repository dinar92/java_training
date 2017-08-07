package ru.job4j.tic_tac_toe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.State;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 25.07.17.
 * Tests class ConsecutiveCellsFinder.
 */
public class ConsecutiveCellsFinderTest {

    /**
     * The instance of finder.
     */
    private final ConsecutiveCellsFinder finder = new ConsecutiveCellsFinder();

    /**
     * Tests checkHorizontalLines(...).
     */
    @Test
    public void whenFindHorizontalSequenceShouldReturnsSequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 5;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(4);
        State[] states = new State[2];
        states[0] = TicTacState.EMPTY;
        states[1] = TicTacState.CROSS;
        String crossCell = "a3";
        board.setState(crossCell, states[1]);
        ArrayList<String> expectCells = new ArrayList<>();
        expectCells.add("a1");
        expectCells.add("a2");
        expectCells.add("a3");
        expectCells.add("a4");
        ArrayList<String> actualCells = this.finder.checkHorizontalLines(board, countOfCells, states);
        Assert.assertThat(actualCells, Matchers.containsInAnyOrder(expectCells.toArray(new String[expectCells.size()])));
        Assert.assertThat(actualCells.size(), is(expectCells.size()));
    }

    /**
     * Tests checkHorizontalLines(...) without a coincidence.
     */
    @Test
    public void whenNotFindHorizontalSequenceShouldReturnsEmptySequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 5;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(4);
        State[] states = new State[1];
        states[0] = TicTacState.CROSS;
        int sizeOfExpectArray = 0;
        ArrayList<String> actualCells = this.finder.checkHorizontalLines(board, countOfCells, states);
        Assert.assertThat(actualCells.size(), is(sizeOfExpectArray));
    }

    /**
     * Tests checkVerticalLines(...).
     */
    @Test
    public void whenFindVerticalSequenceShouldReturnsSequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 6;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[2];
        states[0] = TicTacState.EMPTY;
        states[1] = TicTacState.CROSS;
        String crossCell = "c1";
        board.setState(crossCell, states[1]);
        ArrayList<String> expectCells = new ArrayList<>();
        expectCells.add("a1");
        expectCells.add("b1");
        expectCells.add("c1");
        ArrayList<String> actualCells = this.finder.checkVerticalLines(board, countOfCells, states);
        Assert.assertThat(actualCells, Matchers.containsInAnyOrder(expectCells.toArray(new String[expectCells.size()])));
        Assert.assertThat(actualCells.size(), is(expectCells.size()));
    }

    /**
     * Tests checkVerticalLines(...) without a coincidence.
     */
    @Test
    public void whenNotFindVerticalSequenceShouldReturnsEmptySequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 6;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[1];
        states[0] = TicTacState.NAUGHT;
        int sizeOfExpectArray = 0;
        ArrayList<String> actualCells = this.finder.checkVerticalLines(board, countOfCells, states);
        Assert.assertThat(actualCells.size(), is(sizeOfExpectArray));
    }

    /**
     * Tests checkFirstDiagonalLine(...).
     */
    @Test
    public void whenFindFirstDiagonalSequenceShouldReturnsSequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 6;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[2];
        states[0] = TicTacState.CROSS;
        states[1] = TicTacState.EMPTY;
        String crossCell = "c3";
        board.setState(crossCell, states[0]);
        ArrayList<String> expectCells = new ArrayList<>();
        expectCells.add("a1");
        expectCells.add("b2");
        expectCells.add("c3");
        ArrayList<String> actualCells = this.finder.checkFirstDiagonalLine(board, countOfCells, states);
        Assert.assertThat(actualCells, Matchers.containsInAnyOrder(expectCells.toArray(new String[expectCells.size()])));
        Assert.assertThat(actualCells.size(), is(expectCells.size()));
    }

    /**
     * Tests checkFirstDiagonalLine(...) without a coincidence.
     */
    @Test
    public void whenNotFindFirstDiagonalSequenceShouldReturnsEmptySequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 6;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[1];
        states[0] = TicTacState.CROSS;
        int sizeOfExpectArray = 0;
        ArrayList<String> actualCells = this.finder.checkFirstDiagonalLine(board, countOfCells, states);
        Assert.assertThat(actualCells.size(), is(sizeOfExpectArray));
    }

    /**
     * Tests checkSecondDiagonalLine(...).
     */
    @Test
    public void whenFindSecondDiagonalSequenceShouldReturnsSequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 4;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[2];
        states[0] = TicTacState.CROSS;
        states[1] = TicTacState.EMPTY;
        String crossCell = "c2";
        board.setState(crossCell, states[0]);
        ArrayList<String> expectCells = new ArrayList<>();
        expectCells.add("d1");
        expectCells.add("c2");
        expectCells.add("b3");
        ArrayList<String> actualCells = this.finder.checkSecondDiagonalLine(board, countOfCells, states);
        Assert.assertThat(actualCells, Matchers.containsInAnyOrder(expectCells.toArray(new String[expectCells.size()])));
        Assert.assertThat(actualCells.size(), is(expectCells.size()));
    }

    /**
     * Tests checkSecondDiagonalLine(...) without a coincidence.
     */
    @Test
    public void whenNotFindSecondDiagonalSequenceShouldReturnsEmptySequence() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 4;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        State[] states = new State[1];
        states[0] = TicTacState.CROSS;
        int sizeOfExpectArray = 0;
        ArrayList<String> actualCells = this.finder.checkSecondDiagonalLine(board, countOfCells, states);
        Assert.assertThat(actualCells.size(), is(sizeOfExpectArray));
    }

    /**
     * Tests checkAllLines().
     */
    @Test
    public void whenNeedToCheckAllLinesShouldReturnPossibleVariant() {
        GameBoard board = new TicTacBoard();
        int sizeOfBoard = 3;
        board.init(sizeOfBoard);
        AtomicInteger countOfCells = new AtomicInteger(3);
        String occupiedByEnemy = "a1";
        board.setState(occupiedByEnemy, TicTacState.NAUGHT);
        String[] expectCells = {"b1", "b2", "b3"};
        State[] states = new State[2];
        states[0] = TicTacState.CROSS;
        states[1] = TicTacState.EMPTY;
        Assert.assertThat(finder.checkAllLines(board, countOfCells, states), Matchers.containsInAnyOrder(expectCells));
        Assert.assertThat(finder.checkAllLines(board, countOfCells, states).size(), is(expectCells.length));
    }
}
