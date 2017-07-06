package ru.job4j.chess;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 27.06.17.
 * Tests class Knight.
 */
public class KnightTest {

    /**
     * Tests clone().
     */
    @Test
    public void whenCloneShouldReturnsClone() {
        Cell source = new Cell(4, 5);
        Cell destination = new Cell(6, 8);
        Knight knight = new Knight(source, "white", new Board());
        assertThat(knight.clone(destination), is(knight));
    }

    /**
     * Tests the stub method - way().
     */
    @Test
    public void whenWayShouldReturnEmptyArray() {
        Board board = new Board();
        Knight knight = new Knight(board.desk[2][0], "white", board);

        Cell[] emptyExpect = new Cell[0];

        assertThat(knight.way(board.desk[0][2]), is(emptyExpect));

    }
}
