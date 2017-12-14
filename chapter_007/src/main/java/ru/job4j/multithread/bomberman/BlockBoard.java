package ru.job4j.multithread.bomberman;

/**
 * A board with the possibility of setting blocks.
 */
public class BlockBoard extends Board implements Blockable {

    /**
     * Cell release timeout.
     */
    private final static long LOCK_WAITING_OF_BLOCK = 5000;

    /**
     * Sets the size of the board.
     *
     * @param sizeOfSide - the size.
     */
    public BlockBoard(int sizeOfSide) {
        super(sizeOfSide);
    }


    /**
     * Sets the not available cell on the board.
     * Should be used after calling the init().
     *
     * @param axisX - X-axis coordinate.
     * @param axisY - Y-axis coordinate.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     * @throws InterruptedException - locking was interrupted.
     */
    public void setBlock(int axisX, int axisY) throws BoardIsNotInitException, InterruptedException {
            this.getCell(axisX, axisY).tryLock(LOCK_WAITING_OF_BLOCK);
    }
}
