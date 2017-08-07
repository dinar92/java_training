package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.GameBoard;
import ru.job4j.boardGame.State;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pacman on 21.07.17.
 * Finds the consecutive cells on the game board with classic numeration coordinates
 * that meet the requirements.
 */
public class ConsecutiveCellsFinder {

    /**
     * Checks the horizontal lines that meet the requirements or return empty ArrayList<String>.
     * Returns the first match from the beginning.
     *
     * @param board        the game board.
     * @param countOfCells the count of necessary cells which must be found.
     * @param states       the conditions that will be compared with the help of the operator OR.
     * @return list found.
     */
    public ArrayList<String> checkHorizontalLines(GameBoard board, AtomicInteger countOfCells, State... states) {
        ArrayList<String> cells = new ArrayList<>();
        String coordinate;
        exit:
            for (int row = 0, rowChar = 97; row < board.getSize(); row++, rowChar++) {
                for (int column = 0, colChar = 49; column < board.getSize(); column++, colChar++) {
                    for (int counter = 0; counter < countOfCells.get(); counter++) {
                        try {
                            coordinate = String.valueOf((char) rowChar) + String.valueOf((char) (colChar + counter));
                            for (State state : states) {
                                if (board.getState(coordinate).equals(state)) {
                                    cells.add(coordinate);
                                    break;
                                }
                            }
                            if (cells.size() == counter) {
                                cells.clear();
                                break;
                            }
                            if (cells.size() == countOfCells.get()) {
                                break exit;
                            }
                        } catch (IllegalArgumentException ex) {
                            cells.clear();
                            break;
                        }

                    }
                }
            }
        return cells;
    }

    /**
     * Checks the vertical lines that meet the requirements or return empty ArrayList<String>.
     * Returns the first match from the beginning.
     *
     * @param board        the game board.
     * @param countOfCells the count of necessary cells which must be found.
     * @param states       the conditions that will be compared with the help of the operator OR.
     * @return list found.
     */
    public ArrayList<String> checkVerticalLines(GameBoard board, AtomicInteger countOfCells, State... states) {
        ArrayList<String> cells = new ArrayList<>();
        String coordinate;
        exit:
            for (int column = 0, colChar = 49; column < board.getSize(); column++, colChar++) {
                for (int row = 0, rowChar = 97; row < board.getSize(); row++, rowChar++) {
                    for (int counter = 0; counter < countOfCells.get(); counter++) {
                        try {
                            coordinate = String.valueOf((char) (rowChar + counter)) + String.valueOf((char) colChar);
                            for (State state : states) {
                                if (board.getState(coordinate).equals(state)) {
                                    cells.add(coordinate);
                                    break;
                                }
                            }
                            if (cells.size() == counter) {
                                cells.clear();
                                break;
                            }
                            if (cells.size() == countOfCells.get()) {
                                break exit;
                            }
                        } catch (IllegalArgumentException ex) {
                            cells.clear();
                            break;
                        }

                    }
                }
            }
        return cells;
    }

    /**
     * Checks the first diagonal which starts from the top left
     * line that meet the requirements or return empty ArrayList<String>.
     * Returns the first match from the beginning.
     *
     * @param board        the game board.
     * @param countOfCells the count of necessary cells which must be found.
     * @param states       the conditions that will be compared with the help of the operator OR.
     * @return list found.
     */
    public ArrayList<String> checkFirstDiagonalLine(GameBoard board, AtomicInteger countOfCells, State... states) {
        ArrayList<String> cells = new ArrayList<>();
        String coordinate;
        exit:
            for (int diag = 0, colChar = 49, rowChar = 97; diag < board.getSize(); diag++, colChar++, rowChar++) {
                for (int counter = 0; counter < countOfCells.get(); counter++) {
                    try {
                        coordinate = String.valueOf((char) (rowChar + counter)) + String.valueOf((char) (colChar + counter));
                        for (State state : states) {
                            if (board.getState(coordinate).equals(state)) {
                                cells.add(coordinate);
                                break;
                            }
                        }
                        if (cells.size() == counter) {
                            cells.clear();
                            break;
                        }
                        if (cells.size() == countOfCells.get()) {
                            break exit;
                        }
                    } catch (IllegalArgumentException ex) {
                        cells.clear();
                        break exit;
                    }
                }
            }
        return cells;
    }

    /**
     * Checks the first diagonal which starts from the bottom left
     * line that meet the requirements or return empty ArrayList<String>.
     * Returns the first match from the beginning.
     *
     * @param board        the game board.
     * @param countOfCells the count of necessary cells which must be found.
     * @param states       the conditions that will be compared with the help of the operator OR.
     * @return list found.
     */
    public ArrayList<String> checkSecondDiagonalLine(GameBoard board, AtomicInteger countOfCells, State... states) {
        ArrayList<String> cells = new ArrayList<>();
        String coordinate;
        exit:
            for (int diag = 0, colChar = 49, rowChar = 97 + (board.getSize() - 1); diag < board.getSize(); diag++, colChar++, rowChar--) {
                for (int counter = 0; counter < countOfCells.get(); counter++) {
                    try {
                        coordinate = String.valueOf((char) (rowChar - counter)) + String.valueOf((char) (colChar + counter));
                        for (State state : states) {
                            if (board.getState(coordinate).equals(state)) {
                                cells.add(coordinate);
                                break;
                            }
                        }
                        if (cells.size() == counter) {
                            cells.clear();
                            break;
                        }
                        if (cells.size() == countOfCells.get()) {
                            break exit;
                        }
                    } catch (IllegalArgumentException ex) {
                        cells.clear();
                        break exit;
                    }
                }
            }
        return cells;
    }

    /**
     * Checks all lines of the board and returns first of matches.
     * Or empty ArrayList<String> if matches is not found.
     *
     * @param board        the game board.
     * @param countOfCells the count of necessary cells which must be found.
     * @param states       the conditions that will be compared with the help of the operator OR.
     * @return list found.
     */
    public ArrayList<String> checkAllLines(GameBoard board, AtomicInteger countOfCells, State... states) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> toReturn = new ArrayList<>();
        list.add(this.checkHorizontalLines(board, countOfCells, states));
        list.add(this.checkVerticalLines(board, countOfCells, states));
        list.add(this.checkFirstDiagonalLine(board, countOfCells, states));
        list.add(this.checkSecondDiagonalLine(board, countOfCells, states));

        for (ArrayList<String> elem : list) {
            if (elem.size() != 0) {
                toReturn = elem;
                break;
            }
        }
        return toReturn;
    }
}

