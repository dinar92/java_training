package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 23.07.17.
 * Tests class TicTacState.
 */
public class TicTacStateTest {

    /**
     * Tests toString() for EMPTY.
     */
    @Test
    public void whenToStringThenGetHumanizedEmptyState() {
        TicTacState state = TicTacState.EMPTY;
        String expectedView = " ";

        Assert.assertThat(state.toString(), is(expectedView));
    }

    /**
     * Tests toString() for CROSS.
     */
    @Test
    public void whenToStringThenGetHumanizedCrossState() {
        TicTacState state = TicTacState.CROSS;
        String expectedView = "X";

        Assert.assertThat(state.toString(), is(expectedView));
    }

    /**
     * Tests toString() for EMPTY.
     */
    @Test
    public void whenToStringThenGetHumanizedNaughtState() {
        TicTacState state = TicTacState.NAUGHT;
        String expectedView = "O";

        Assert.assertThat(state.toString(), is(expectedView));
    }
}
