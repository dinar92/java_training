package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.GameBoard;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 06.08.17.
 * Tests class SetBoardSize.
 */
public class SetBoardSizeTest {

    /**
     * Tests action().
     */
    @Test
    public void whenSetBoardSizeThenCreateBoardWithSpecifiedSize() {
        GameBoard board = new TicTacBoard();
        int boardSize = 5;
        System.setIn(new ByteArrayInputStream((String.valueOf(boardSize) + "\n").getBytes()));

        ConsoleInput input = new ConsoleInput();
        ConsoleDisplay display = new ConsoleDisplay();

        new SetBoardSize(display, new IntegerInputVerificator(input, display), board).action();

        Assert.assertThat(board.getSize(), is(boardSize));
    }
}