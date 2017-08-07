package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 07.08.17.
 * Tests class WinnerDetermining.
 */
public class WinnerDeterminingTest {

    /**
     * Tests showInfo().
     */
    @Test
    public void whenShowInfoThenGetResultOfGame() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expectString = "WON!!!\n";

        new WinnerDetermining(new ConsoleDisplay(), new AbstractPlayer()).showInfo();

        Assert.assertThat(outputStream.toString(), is(expectString));
    }


    /**
     * Tests action().
     */
    @Test
    public void whenSetWinnerThenShowHisName() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String playersName = "Mike";
        AbstractPlayer player = new AbstractPlayer();
        player.setPlayer(new User(new TicTacBoard(), new ConsoleInput(), TicTacState.CROSS, playersName));

        new WinnerDetermining(new ConsoleDisplay(), player).action();

        Assert.assertThat(outputStream.toString(), is(playersName));
    }
}
