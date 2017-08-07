package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.State;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 24.07.17.
 * Tests class User.
 */
public class UserTest {

    /**
     * The line separator.
     */
    private final String ls = System.lineSeparator();

    /**
     * Instance of the user.
     */
    private final User user = new User(new TicTacBoard(), new ConsoleInput(), TicTacState.CROSS, "Mike");
    /**
     * Tests getState().
     */
    @Test
    public void whenGetSatteShuldReturnStateOfUser() {
        State expectState = TicTacState.CROSS;

        Assert.assertThat(this.user.getState(), is(expectState));
    }

    /**
     * Tests toString().
     */
    @Test
    public void whnToStringThenReturnNameOfUser() {
        String expectName = "Mike";

        Assert.assertThat(this.user.toString(), is(expectName));
    }

    /**
     * Tests doMove() in normal mode.
     */
    @Test
    public void whenDoMoveShouldSetSelfStateinDefinedCell() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        board.init(boardSize);
        State state = TicTacState.CROSS;
        String coordinate = "b2";
        System.setIn(new ByteArrayInputStream(coordinate.getBytes()));
        User player = new User(board, new ConsoleInput(), state, "Nick");

        player.doMove();

        Assert.assertThat(board.getState(coordinate), is(state));
    }

    /**
     * Tests doMove(), when cell is not empty.
     */
    @Test
    public void whenCellIsNotEmptyShouldGenerateMessage() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        board.init(boardSize);
        State state = TicTacState.NAUGHT;
        String coordinate = "c3";
        String emptyCoordinate = "a1";
        board.setState(coordinate, TicTacState.NAUGHT);
        String expectMessage = "Enter the cell's coordinate (a1 | b2 |...):" + ls
                + "This cell isn't empty. Try again." + ls
                + "Enter the cell's coordinate (a1 | b2 |...):" + ls;

        System.setIn(new ByteArrayInputStream((coordinate + ls + emptyCoordinate + ls).getBytes()));
        User player = new User(board, new ConsoleInput(), state, "Nick");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        player.doMove();

        Assert.assertThat(outputStream.toString(), is(expectMessage));
    }

    /**
     * Tests doMove(), when exception if cell is not exist.
     */
    @Test
    public void whenCellIsNotExistShouldThrowException() {
        GameBoard board = new TicTacBoard();
        int boardSize = 3;
        board.init(boardSize);
        String coordinate = "z9";
        String correctCoordinate = "a1";
        String expectMessage = "Enter the cell's coordinate (a1 | b2 |...):" + ls
                + "Cell isn't exist. Try again!" + ls
                + "Enter the cell's coordinate (a1 | b2 |...):" + ls;


        System.setIn(new ByteArrayInputStream((coordinate + ls + correctCoordinate + ls).getBytes()));
        User player = new User(board, new ConsoleInput(), TicTacState.CROSS, "Nick");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        player.doMove();

        Assert.assertThat(outputStream.toString(), is(expectMessage));
    }
}
