package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.Player;
import ru.job4j.boardGame.Stage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Process of the game.
 */
class Game implements Stage {

    /**
     * Provides the info for players.
     */
    private final Display display;

    /**
     * The game board.
     */
    private final GameBoard board;

    /**
     * The count of necessary cells for win.
     */
    private AtomicInteger countOfCells;

    /**
     * The player 1, starts the game.
     */
    private AbstractPlayer player1;

    /**
     * The player 2.
     */
    private AbstractPlayer player2;
    /**
     * The winner.
     */
    private AbstractPlayer winner;


    /**
     * The consecutive cells finder.
     */
    private final ConsecutiveCellsFinder finder = new ConsecutiveCellsFinder();

    /**
     * Default constructor.
     *
     * @param display      display method.
     * @param board        game board.
     * @param countOfCells the cunt of cells.
     * @param player1      first player.
     * @param player2      second player.
     * @param winner       winner.
     */
    Game(Display display, GameBoard board, AtomicInteger countOfCells, AbstractPlayer player1, AbstractPlayer player2, AbstractPlayer winner) {
        this.display = display;
        this.board = board;
        this.countOfCells = countOfCells;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    /**
     * Shows info about the game board.
     */
    @Override
    public void showInfo() {
        display.showln("");
        display.showln("Tic-tac-toe!");
        display.showln("");
        for (int row = 0, rowChar = 97; row < board.getSize(); row++, rowChar++) {
            for (int column = 0; column < board.getSize() - 1; column++) {
                display.show("---");
            }
            display.showln("---");
            display.show(String.valueOf((char) rowChar));
            for (int column = 0, colChar = 49; column < board.getSize(); column++, colChar++) {
                display.show(" | " + board.getState(String.valueOf((char) rowChar) + String.valueOf((char) colChar)));
            }
            display.showln(" |");
        }
        for (int column = 0; column < board.getSize(); column++) {
            display.show("---");
        }
    }

    /**
     * Determines the game end.
     */
    @Override
    public void action() {
        while (!checkForGameEnd()) {
            player1.doMove();
            this.showInfo();
            if (checkForGameEnd()) {
                break;
            }
            player2.doMove();
            this.showInfo();
        }
    }

    /**
     * Determines the condition of the game and determines the winner.
     *
     * @return true if the game was finished.
     */
    public boolean checkForGameEnd() {
        boolean isEnd = false;
        if (this.isWin(this.player1)) {
            this.winner.setPlayer(this.player1);
            isEnd = true;
        } else if (this.isWin(this.player2)) {
            this.winner.setPlayer(this.player2);
            isEnd = true;
        } else if (this.isDraw()) {
            this.winner.setPlayer(new Computer(this.board, TicTacState.EMPTY, this.countOfCells, "Friendship"));
            isEnd = true;
        }
        return isEnd;
    }

    /**
     * Checks the draw.
     * Returns true if it does not find any empty cells.
     *
     * @return draw or not.
     */
    public boolean isDraw() {
        return !this.isWin(this.player1) && !this.isWin(this.player2) && this.finder.checkAllLines(this.board, new AtomicInteger(1), TicTacState.EMPTY).size() == 0;
    }

    /**
     * Defines the winner.
     *
     * @param player The player.
     * @return is win.
     */
    public boolean isWin(Player player) {
        return this.finder.checkAllLines(this.board, this.countOfCells, player.getState()).size() == countOfCells.get();
    }
}