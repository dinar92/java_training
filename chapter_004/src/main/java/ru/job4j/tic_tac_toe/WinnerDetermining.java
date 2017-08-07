package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.Player;
import ru.job4j.boardGame.Stage;

/**
 * Determines the winner.
 */
class WinnerDetermining implements Stage {

    /**
     * Provides the info for players.
     */
    private final Display display;

    /**
     * The winner.
     */
    private Player winner;

    /**
     * Default constructor.
     *
     * @param display - display method.
     * @param winner  - the winner.
     */
    WinnerDetermining(Display display, Player winner) {
        this.display = display;
        this.winner = winner;
    }

    /**
     * Shows info about the current status of the winner.
     */
    @Override
    public void showInfo() {
        display.showln("WON!!!");
    }

    /**
     * The response on player's enter.
     */
    @Override
    public void action() {
        display.show(winner.toString());
    }
}