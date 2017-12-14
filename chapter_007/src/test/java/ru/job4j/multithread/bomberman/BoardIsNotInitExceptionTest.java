package ru.job4j.multithread.bomberman;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests the class BoardIsNotInitException.
 */
public class BoardIsNotInitExceptionTest {

    /**
     * Size of side of the board.
     */
    private static final int SIZE_OF_SIDE = 7;

    /**
     * The instance of exception.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Throws exception.
     *
     * @throws BoardIsNotInitException - attempts to use not initialized board.
     */
    @Test
    public void whenBoardIsNotInitThenFail() throws BoardIsNotInitException {
        expectedException.expect(BoardIsNotInitException.class);
        expectedException.expectMessage("Board was not initialized");
        Board board = new BlockBoard(SIZE_OF_SIDE);
        board.getCell(0, 0);
    }
}
