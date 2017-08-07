package ru.job4j.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 26.07.17.
 * Tests class SimpleStrategy.
 */
public class SimpleStrategyTest {

    /**
     * Tests getMove().
     */
    @Test
    public void whenGetMoveThenReturnCoordinateToMoving() {
        TicTacBoard board = new TicTacBoard();
        int sizeOfBoard = 3;
        board.init(sizeOfBoard);
        TicTacState state = TicTacState.CROSS;
        AtomicInteger countOfCells = new AtomicInteger(3);
        SimpleStrategy strategy = new SimpleStrategy(board, state, countOfCells);
        String[] expectCoordinates = {"a1", "a2", "a3"};
        ArrayList<String> actualCoordinates = new ArrayList<>();
        for (int i = 0; i < countOfCells.get(); i++) {
            actualCoordinates.add(strategy.getMove());
            board.setState(actualCoordinates.get(i), state);
        }
        Assert.assertThat(actualCoordinates.toArray(new String[actualCoordinates.size()]), is(expectCoordinates));
    }
}
