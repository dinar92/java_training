package ru.job4j.tic_tac_toe;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.Display;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 07.08.17.
 * Tests class SetDifficult.
 */
public class SetDifficultTest {

    /**
     * Get an instance of SetDifficult, with specified size of the game board and
     * the count of necessary cells for win.
     * @param sizeOfBoard size of the game board.
     * @param countOfCells count of necessary cells for win.
     * @return instance of SetDifficult.
     */
    private SetDifficult getDifficultSetter(int sizeOfBoard, AtomicInteger countOfCells) {
        TicTacBoard board = new TicTacBoard();
        board.init(sizeOfBoard);
        ConsoleInput input = new ConsoleInput();
        Display display = new ConsoleDisplay();
        NumberInputVerificator verificator = new IntegerInputVerificator(input, display);
        return new SetDifficult(display, verificator, board, countOfCells);
    }

    /**
     * Tests action() (tests show question function).
     */
    @Test
    public void whenBoardsSizeMoreThanFourThenAskDifficult() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        int sizeOfBoard = 5;
        AtomicInteger countOfCells = new AtomicInteger(3);
        SetDifficult set = this.getDifficultSetter(sizeOfBoard, countOfCells);

        String expectAsk = "Please, choose the difficulty of the game [1/2]";

        set.action();

        Assert.assertThat(out.toString(), CoreMatchers.containsString(expectAsk));
    }

    /**
     * Tests action() (tests not show question function).
     */
    @Test
    public void whenBoardSizeLessOrEqualsThanFourThenNotShowAskDialog() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        int sizeOfBoard = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        SetDifficult set = this.getDifficultSetter(sizeOfBoard, countOfCells);

        String expectAsk = "";

        set.action();

        Assert.assertThat(out.toString(), CoreMatchers.containsString(expectAsk));
    }
    /**
     * Tests action() (tests changeable of countOfCells).
     */
    @Test
    public void whenSetHardDifficultThenCountOfCellsChanged() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        int sizeOfBoard = 5;
        AtomicInteger countOfCells = new AtomicInteger(3);
        SetDifficult set = this.getDifficultSetter(sizeOfBoard, countOfCells);
        int expectChangedValueOfCountOfCells = 5;

        set.action();

        Assert.assertThat(countOfCells.get(), is(expectChangedValueOfCountOfCells));
    }
}
