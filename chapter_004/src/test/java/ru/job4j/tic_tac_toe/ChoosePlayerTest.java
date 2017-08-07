package ru.job4j.tic_tac_toe;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pacman on 02.08.17.
 * Tests class ChoosePlayer.
 */
public class ChoosePlayerTest {

    /**
     * Tests showInfo().
     */
    @Test
    public void whenShowInfoThenShowInfoForUser() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        int chooseUserFirst = 1;
        NumberInputVerificator verificator = mock(NumberInputVerificator.class);
        when(verificator.getVerifiedAnswer("Who's first?", 1, 2)).thenReturn(chooseUserFirst);

        String expectMessage = "Who will be the first?";
        ChoosePlayer choosePlayer = new ChoosePlayer(new ConsoleInput(), new ConsoleDisplay(), verificator, new TicTacBoard(), new AtomicInteger(3), new AbstractPlayer(), new AbstractPlayer());

        choosePlayer.showInfo();

        Assert.assertThat(out.toString(), CoreMatchers.containsString(expectMessage));
    }

    /**
     * Tests action(), when user is first.
     */
    @Test
    public void whenSetOneThenFirstIsUser() {

        // Mocking choice second player.
        String question = "Select the first player [1/2]:";
        int minLimit = 1;
        int maxLimit = 2;
        int chooseUserFirst = 1;
        NumberInputVerificator verificator = mock(NumberInputVerificator.class);
        when(verificator.getVerifiedAnswer(question, minLimit, maxLimit)).thenReturn(chooseUserFirst);

        //Create board.
        TicTacBoard board = new TicTacBoard();
        int sizeOfBoard = 3;
        board.init(sizeOfBoard);

        AbstractPlayer firstPlayer = new AbstractPlayer();
        AbstractPlayer secondPlayer = new AbstractPlayer();

        ChoosePlayer choosePlayer = new ChoosePlayer(new ConsoleInput(), new ConsoleDisplay(), verificator, board, new AtomicInteger(3), firstPlayer, secondPlayer);

        //First player's name.
        String usersName = "You";

        //Second player's name.
        String computersName = "Terminator";

        choosePlayer.action();

        Assert.assertThat(firstPlayer.toString(), is(usersName));
        Assert.assertThat(secondPlayer.toString(), is(computersName));
    }

    /**
     * Tests action(), when computer is first.
     */
    @Test
    public void whenSetTwoThenFirstIsComputer() {

        // Mocking choice second player.
        String question = "Select the first player [1/2]:";
        int minLimit = 1;
        int maxLimit = 2;
        int chooseUserFirst = 2;
        NumberInputVerificator verificator = mock(NumberInputVerificator.class);
        when(verificator.getVerifiedAnswer(question, minLimit, maxLimit)).thenReturn(chooseUserFirst);

        //Create board.
        TicTacBoard board = new TicTacBoard();
        int sizeOfBoard = 3;
        board.init(sizeOfBoard);

        AbstractPlayer firstPlayer = new AbstractPlayer();
        AbstractPlayer secondPlayer = new AbstractPlayer();

        ChoosePlayer choosePlayer = new ChoosePlayer(new ConsoleInput(), new ConsoleDisplay(), verificator, board, new AtomicInteger(3), firstPlayer, secondPlayer);

        //First player's name.
        String usersName = "You";

        //Second player's name.
        String computersName = "Terminator";

        choosePlayer.action();

        Assert.assertThat(firstPlayer.toString(), is(computersName));
        Assert.assertThat(secondPlayer.toString(), is(usersName));
    }
}
