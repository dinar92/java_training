package ru.job4j.multithread.bomberman;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * A hero implementation.
 * Has manual behavior.
 */
public class Hero extends Thread implements Controller {

    /**
     * The game board.
     */
    private final Board board;

    /**
     * The current cell of the hero.
     */
    private Cell current;

    /**
     * The time to wait for the lock.
     */
    private final static long WAITING_TIME = 0;

    /**
     * The state of the hero.
     */
    private boolean isRun;

    /**
     * A queue of moves.
     * Provides a sequence of moves hero.
     */
    private final BlockingQueue<Move> moves;

    /**
     * Behavior of the movement to the right.
     */
    private final Move right;

    /**
     * Behavior of the movement to the left.
     */
    private final Move left;

    /**
     * Behavior of the movement to the up.
     */
    private final Move up;

    /**
     * Behavior of the movement to the down.
     */
    private final Move down;

    /**
     * Ceases to exist hero.
     */
    private final Move stop;

    /**
     * Sets the board and the start position on this board.
     *
     * @param board      - the game board.
     * @param startAxisX - x-axis coordinate.
     * @param startAxisY - y-axis coordinate.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public Hero(Board board, int startAxisX, int startAxisY) throws BoardIsNotInitException {
        this.board = board;
        current = board.getCell(startAxisX, startAxisY);
        this.right = new HeroRightMove();
        this.left = new HeroLeftMove();
        this.up = new HeroUpMove();
        this.down = new HeroDownMove();
        this.stop = new StopMoving();
        this.moves = new ArrayBlockingQueue<>(10);
        this.isRun = false;
    }


    /**
     * Sets hero on the start position on the board.
     */
    @Override
    public void run() {
        try {
            while (!isRun) {                        //Attempts to take the starting cell.
                if (current.tryLock(WAITING_TIME)) {
                    isRun = true;
                }
            }
            while (isRun) {
                moves.take().move();
            }
        } catch (InterruptedException | BoardIsNotInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns hero's coordinates as array.
     *
     * @return - array, where a first element is x-axis coordinate and second - y-axis coordinate.
     */
    public int[] getPosition() {
        return new int[]{this.current.getXAxis(), this.current.getYAxis()};
    }

    /**
     * Removes hero from the game.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void stopMovement() throws InterruptedException {
        this.moves.put(this.stop);
    }

    /**
     * Moves the hero up the board.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void moveUp() throws InterruptedException {

        this.moves.put(this.up);

    }

    /**
     * Moves the hero down the board.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void moveDown() throws InterruptedException {

        this.moves.put(this.down);

    }

    /**
     * Moves the hero left the board.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void moveLeft() throws InterruptedException {
        this.moves.put(this.left);
    }

    /**
     * Moves the hero right the board.
     *
     * @throws InterruptedException if interrupted while waiting
     */
    public void moveRight() throws InterruptedException {
        this.moves.put(this.right);
    }

    /**
     * Behavior of movement on the board.
     */
    private abstract class Move {

        /**
         * Moves on the board.
         *
         * @throws InterruptedException - if process was interrupt.
         */
        abstract void move() throws InterruptedException, BoardIsNotInitException;

        /**
         * Tries to get a free cell.
         *
         * @param move        - new move for checking.
         * @param waitingTime - time to wait for a busy cell to be vacated.
         * @throws InterruptedException - if process was interrupt.
         */
        void doMove(Cell move, long waitingTime) throws InterruptedException {
            if (move.tryLock(waitingTime)) {
                Hero.this.current.unlock();
                Hero.this.current = move;
            }
        }
    }

    /**
     * Behavior of stopping hero's motion.
     */
    private class StopMoving extends Move {

        /**
         * Sets the flag of end game.
         */
        @Override
        void move() {
            Hero.this.isRun = false;
            Hero.this.current.unlock();
        }
    }

    /**
     * Behavior of motion to the down.
     */
    private class HeroDownMove extends Move {

        /**
         * Moves the hero down the board.
         *
         * @throws InterruptedException    - if process was interrupt.
         * @throws BoardIsNotInitException - attempt to use the not initialized board.
         */
        @Override
        public void move() throws InterruptedException, BoardIsNotInitException {
            if (Hero.this.current.getYAxis() != 0) {
                doMove(Hero.this.board.getCell(Hero.this.current.getXAxis(), Hero.this.current.getYAxis() - 1), WAITING_TIME);
            }
        }
    }

    /**
     * Behavior of motion to the up.
     */
    public class HeroUpMove extends Move {

        /**
         * Moves the hero up the board.
         *
         * @throws InterruptedException    - if process was interrupt.
         * @throws BoardIsNotInitException - attempt to use the not initialized board.
         */
        @Override
        public void move() throws InterruptedException, BoardIsNotInitException {
            if (Hero.this.current.getYAxis() != Hero.this.board.getSideSize() - 1) {
                doMove(Hero.this.board.getCell(Hero.this.current.getXAxis(), Hero.this.current.getYAxis() + 1), WAITING_TIME);
            }
        }
    }

    /**
     * Behavior of motion to the right.
     */
    public class HeroRightMove extends Move {

        /**
         * Moves the hero right the board.
         *
         * @throws InterruptedException    - if process was interrupt.
         * @throws BoardIsNotInitException - attempt to use the not initialized board.
         */
        @Override
        public void move() throws InterruptedException, BoardIsNotInitException {
            if (Hero.this.current.getXAxis() != Hero.this.board.getSideSize() - 1) {
                doMove(Hero.this.board.getCell(Hero.this.current.getXAxis() + 1, Hero.this.current.getYAxis()), WAITING_TIME);
            }
        }
    }

    /**
     * Behavior of motion to the right.
     */
    public class HeroLeftMove extends Move {

        /**
         * Moves the hero left the board.
         *
         * @throws InterruptedException    - if process was interrupt.
         * @throws BoardIsNotInitException - attempt to use the not initialized board.
         */
        @Override
        void move() throws InterruptedException, BoardIsNotInitException {
            if (Hero.this.current.getXAxis() != 0) {
                doMove(Hero.this.board.getCell(Hero.this.current.getXAxis() - 1, Hero.this.current.getYAxis()), WAITING_TIME);
            }
        }
    }
}
