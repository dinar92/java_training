package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.Cell;
import ru.job4j.boardGame.State;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 23.07.17.
 * Tests class TicTacCell.
 */
public class TicTacCellTest {

    /**
     * The cell's coordinates.
     */
    private final String coordinates = "a1";

    /**
     * The cell's state.
     */
    private final State state = TicTacState.EMPTY;

    /**
     * Instance of the tic-tac-toe cell.
     */
    private final Cell cell = new TicTacCell(this.state, this.coordinates);

    /**
     * Tests getState().
     */
    @Test
    public void whenGetStateShouldReturnCurrentState() {
        State expectState = TicTacState.EMPTY;

        Assert.assertThat(cell.getState(), is(expectState));
    }

    /**
     * Tests setState().
     */
    @Test
    public void whenSetStateShouldReturnSpecifiedState() {
        State expectState = TicTacState.CROSS;

        this.cell.setState(TicTacState.CROSS);

        Assert.assertThat(cell.getState(), is(expectState));
    }

    /**
     * Tests getCoord().
     */
    @Test
    public void whenGetCoordShouldReturnCurrentCoordinates() {
        String expectCoordinate = "a1";

        Assert.assertThat(cell.getCoord(), is(expectCoordinate));
    }

    /**
     * Tests toString().
     */
    @Test
    public void whenInvokeToShowingShouldReturnCellsStateInString() {
        String expectState = TicTacState.EMPTY.toString();

        Assert.assertThat(cell.toString(), is(expectState));
    }
}
