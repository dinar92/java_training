package ru.job4j.multithread.bomberman;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Tests the class BlockBoard.
 */
public class BlockBoardTest {

    /**
     * Size of side of the board.
     */
    private static final int SIZE_OF_SIDE = 7;

    /**
     * Some thread, which use the same board and
     * sets the block on [0,0] position.
     */
    private class AnotherThread extends Thread {
        BlockBoard aBoard;
        AnotherThread(BlockBoard board) {
            aBoard = board;
        }

        @Override
        public void run() {
            try {
                aBoard.setBlock(0, 0);
            } catch (BoardIsNotInitException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tests block setting function.
     * @throws BoardIsNotInitException - BoardIsNotInitException.
     * @throws InterruptedException - InterruptedException.
     */
    @Test
    public void whenBlockIsSetsThenCellLocked() throws BoardIsNotInitException, InterruptedException {
        BlockBoard board = new BlockBoard(SIZE_OF_SIDE);
        board.init();
        Thread anotherThread = new AnotherThread(board);
        anotherThread.start();
        anotherThread.join();
        Cell cell = board.getCell(0, 0);
        boolean resultOfBlocking = cell.tryLock(1000);
        Assert.assertThat(resultOfBlocking, is(false));
    }
}
