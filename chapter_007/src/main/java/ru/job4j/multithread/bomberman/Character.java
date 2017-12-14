package ru.job4j.multithread.bomberman;

/**
 * The Bomberman's character implementation.
 * Has automatic behavior.
 */
public class Character extends Thread {

    /**
     * The game board.
     */
    private final Board board;

    /**
     * The current position of the character.
     */
    private Cell current;

    /**
     * Sets the game board, character's X axis start position and character's X axis start position.
     *
     * @param board      - the game board.
     * @param startAxisX - X axis start position.
     * @param startAxisY - Y axis start position.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public Character(Board board, int startAxisX, int startAxisY) throws BoardIsNotInitException {
        this.board = board;
        this.current = board.getCell(startAxisX, startAxisY);
    }

    /**
     * Takes a new move every 1000 milliseconds.
     */
    @Override
    public void run() {
        try {
            long waitingTime = 500;
            boolean isStarted = false;

            while (!isStarted) {   //Attempt to take the starting cell.
                this.current.tryLock(2000);
                isStarted = true;
            }

            while (!Thread.currentThread().isInterrupted()) {
                this.move(waitingTime);
                Thread.sleep(1000);
            }
        } catch (InterruptedException | BoardIsNotInitException e) {
            e.printStackTrace();
        }
    }


    /**
     * Takes a new step on the free cell.
     * If the cell is busy, it waits for the specified time and tries to get the next one.
     *
     * @param waitingTime - time to wait for a busy cell to be vacated.
     * @throws InterruptedException - if process was interrupt.
     * @throws BoardIsNotInitException - attempt to use the not initialized board.
     */
    public void move(long waitingTime) throws InterruptedException, BoardIsNotInitException {
        boolean wasMove = false;
        do {
            if ((this.current.getYAxis() != this.board.getSideSize() - 1) && (this.doMove(this.board.getCell(this.current.getXAxis(), this.current.getYAxis() + 1), waitingTime))) {
                wasMove = true;
            } else if ((this.current.getXAxis() != this.board.getSideSize() - 1) && (this.doMove(this.board.getCell(this.current.getXAxis() + 1, this.current.getYAxis()), waitingTime))) {
                wasMove = true;
            } else if ((this.current.getYAxis() != 0) && (this.doMove(this.board.getCell(this.current.getXAxis(), this.current.getYAxis() - 1), waitingTime))) {
                wasMove = true;
            } else if ((this.current.getXAxis() != 0) && (this.doMove(this.board.getCell(this.current.getXAxis() - 1, this.current.getYAxis()), waitingTime))) {
                wasMove = true;
            }
        } while (!wasMove);
    }

    /**
     * Tries to get a free cell.
     *
     * @param move        - new move for checking.
     * @param waitingTime - time to wait for a busy cell to be vacated.
     * @return true - if move was taken, false - otherwise.
     * @throws InterruptedException - if process was interrupt.
     */
    private boolean doMove(Cell move, long waitingTime) throws InterruptedException {
        boolean wasMove = false;
        if (move.tryLock(waitingTime)) {
            this.current.unlock();
            this.current = move;
            wasMove = true;
        }
        return wasMove;
    }
}
