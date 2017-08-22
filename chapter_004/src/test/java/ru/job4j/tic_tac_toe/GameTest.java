package ru.job4j.tic_tac_toe;

import com.google.common.base.CharMatcher;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.boardGame.State;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 01.08.17.
 * Tests class Game.
 */
public class GameTest {


    /**
     * Tests isDraw().
     */
    @Test
    public void whenIsDrawShouldReturnTrue() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        boolean expectIsDraw = true;
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], cross);
        board.setState(coordinates[3], cross);
        board.setState(coordinates[4], naught);
        board.setState(coordinates[5], cross);
        board.setState(coordinates[6], naught);
        board.setState(coordinates[7], cross);
        board.setState(coordinates[8], naught);


        Assert.assertThat(game.isDraw(), is(expectIsDraw));
    }

    /**
     * Tests isDraw().
     */
    @Test
    public void whenHasWinnerShouldReturnNotDraw() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        State empty = TicTacState.EMPTY;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        boolean expectIsNotDraw = false;
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], cross);
        board.setState(coordinates[3], cross);
        board.setState(coordinates[4], naught);
        board.setState(coordinates[5], cross);
        board.setState(coordinates[6], cross);
        board.setState(coordinates[7], empty);
        board.setState(coordinates[8], cross);


        Assert.assertThat(game.isDraw(), is(expectIsNotDraw));
    }

    /**
     * Tests checkForGameEnd().
     */
    @Test
    public void whenWinnerIsCrossThenGameOver() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        State empty = TicTacState.EMPTY;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], cross);
        board.setState(coordinates[3], cross);
        board.setState(coordinates[4], naught);
        board.setState(coordinates[5], cross);
        board.setState(coordinates[6], cross);
        board.setState(coordinates[7], empty);
        board.setState(coordinates[8], cross);

        boolean expectedResultOfChecking = true;

        Assert.assertThat(game.checkForGameEnd(), is(expectedResultOfChecking));
    }

    /**
     * Tests checkForGameEnd().
     */
    @Test
    public void whenWinnerIsNaughtThenReturnNaught() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        State empty = TicTacState.EMPTY;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], naught);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], naught);
        board.setState(coordinates[3], cross);
        board.setState(coordinates[4], cross);
        board.setState(coordinates[5], empty);
        board.setState(coordinates[6], empty);
        board.setState(coordinates[7], empty);
        board.setState(coordinates[8], empty);

        boolean expectedResultOfChecking = true;

        Assert.assertThat(game.checkForGameEnd(), is(expectedResultOfChecking));
    }

    /**
     * Tests checkForGameEnd().
     */
    @Test
    public void whenIsDrawThenReturnGameOver() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], cross);
        board.setState(coordinates[3], cross);
        board.setState(coordinates[4], naught);
        board.setState(coordinates[5], cross);
        board.setState(coordinates[6], naught);
        board.setState(coordinates[7], cross);
        board.setState(coordinates[8], naught);

        boolean expectedResultOfChecking = true;

        Assert.assertThat(game.checkForGameEnd(), is(expectedResultOfChecking));
    }

    /**
     * Tests checkForGameEnd().
     */
    @Test
    public void whenHasNotWinnerThenReturnGameNotOver() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 3;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State naught = TicTacState.NAUGHT;
        State empty = TicTacState.EMPTY;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));
        AbstractPlayer player2 = new AbstractPlayer();
        player2.setPlayer(new User(board, new ConsoleInput(), naught, "Player2"));
        AbstractPlayer winner = new AbstractPlayer();
        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, player2, winner);

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], naught);
        board.setState(coordinates[2], empty);
        board.setState(coordinates[3], empty);
        board.setState(coordinates[4], empty);
        board.setState(coordinates[5], empty);
        board.setState(coordinates[6], empty);
        board.setState(coordinates[7], empty);
        board.setState(coordinates[8], empty);

        boolean expectedResultOfChecking = false;

        Assert.assertThat(game.checkForGameEnd(), is(expectedResultOfChecking));
    }

    /**
     * Tests showInfo().
     */
    @Test
    public void whenBoardContainsTwoCrossThenDisplayTwoCross() {
        TicTacBoard board = new TicTacBoard();
        int boarsSize = 4;
        AtomicInteger countOfCells = new AtomicInteger(3);
        board.init(boarsSize);
        State cross = TicTacState.CROSS;
        State empty = TicTacState.EMPTY;
        String[] coordinates = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};
        AbstractPlayer player1 = new AbstractPlayer();
        player1.setPlayer(new User(board, new ConsoleInput(), cross, "Player1"));

        Game game = new Game(new ConsoleDisplay(), board, countOfCells, player1, new AbstractPlayer(), new AbstractPlayer());

        board.setState(coordinates[0], cross);
        board.setState(coordinates[1], cross);
        board.setState(coordinates[2], empty);
        board.setState(coordinates[3], empty);
        board.setState(coordinates[4], empty);
        board.setState(coordinates[5], empty);
        board.setState(coordinates[6], empty);
        board.setState(coordinates[7], empty);
        board.setState(coordinates[8], empty);

        int countOfCrossCells = 2;

        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));

        game.showInfo();

        Assert.assertThat(CharMatcher.is('X').countIn(consoleOut.toString()), is(countOfCrossCells));
    }
}
