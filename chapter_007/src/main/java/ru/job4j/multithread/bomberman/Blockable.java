package ru.job4j.multithread.bomberman;

/**
 * Blockability property of the board.
 */
public interface Blockable {

    /**
     * Sets the not available cell on the board.
     *
     * @param axisX - X-axis coordinate.
     * @param axisY - Y-axis coordinate.
     * @throws Exception - some problems with blocking.
     */
    void setBlock(int axisX, int axisY) throws Exception;
}
