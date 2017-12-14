package ru.job4j.multithread.bomberman;

/**
 * The monster's implementation.
 * Has automatic behavior.
 * Takes a new move every 1000 milliseconds.
 * If the cell is busy, it checks for 5 seconds and does another move.
 */
public class Monster extends Character {

    /**
     * The current position of the character.
     */
    private Cell current;

    private boolean isStarted;

    /**
     * Sets the game board, character's X axis start position and character's X axis start position.
     *
     * @param board      - the game board.
     * @param startAxisX - X axis start position.
     * @param startAxisY - Y axis start position.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public Monster(Board board, int startAxisX, int startAxisY) throws BoardIsNotInitException {
        super(board, startAxisX, startAxisY);
        try {
            this.current = board.getCell(startAxisX, startAxisY);
        } catch (BoardIsNotInitException boardIsNotInitException) {
            boardIsNotInitException.printStackTrace();
        }
        this.isStarted = false;
    }

    /**
     * Starts activity.
     */
    @Override
    public void run() {
        try {
            long waitingTime = 5000;
            while (!isStarted) {                    //Attempt to take the starting cell.
                if (this.current.tryLock(waitingTime)) {
                    isStarted = true;
                }
            }

            while (!Thread.currentThread().isInterrupted() && isStarted) {
                this.move(waitingTime);
                Thread.sleep(1000);
            }
        } catch (InterruptedException | BoardIsNotInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops monster's activity.
     */
    public void stopMonster() {
        this.isStarted = false;
        this.current.unlock();
    }
}
