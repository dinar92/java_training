package ru.job4j.multithread.bomberman;

/**
 * controller for controlling an object in two-dimensional space.
 * It provides the beginning of movement and stopping of movement
 * and movement along the x axis and along the and axis.
 */
interface Controller {
    /**
     * Starts moving.
     * @throws Exception - any exception at the time the movement begins.
     */
    void run() throws Exception;

    /**
     * Stops moving.
     * @throws Exception - any exception at the end of the movement.
     */
    void stopMovement() throws Exception;

    /**
     * Moves to the up.
     * @throws Exception - any exception at the time the movement to the up.
     */
    void moveUp() throws Exception;

    /**
     * Moves to the right.
     * @throws Exception - any exception at the time the movement to the right.
     */
    void moveRight() throws Exception;

    /**
     * Moves to the down.
     * @throws Exception - any exception at the time the movement to the down.
     */
    void moveDown() throws Exception;

    /**
     * Moves to the left.
     * @throws Exception - any exception at the time the movement to the left.
     */
    void moveLeft() throws Exception;
}
