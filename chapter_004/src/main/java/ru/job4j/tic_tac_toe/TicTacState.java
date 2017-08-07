package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.State;

/**
 * Created by pacman on 09.07.17.
 * The state of the cell for the game tic-tac-toe.
 */
public enum TicTacState implements State {
    /**
     * Empty cell.
     */
    EMPTY {
        /**
         * Humanized view of empty cell.
         * @return view.
         */
        public String toString() {
            return " ";
        }
    },
    /**
     * Naught cell.
     */
    NAUGHT {
        /**
         * Humanized view of naught cell.
         * @return view.
         */
        public String toString() {
            return "O";
        }
    },
    /**
     * Cross cell.
     */
    CROSS {
        /**
         * Humanized view of cross cell.
         * @return view.
         */
        public String toString() {
            return "X";
        }
    }
}
