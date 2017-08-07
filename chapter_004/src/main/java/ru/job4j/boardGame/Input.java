package ru.job4j.boardGame;

import ru.job4j.tic_tac_toe.exception.OutOfRangeException;

/**
 * Created by pacman on 11.07.17.
 * Gets the user's input from a player.
 */
public interface Input {

    /**
     * Returns the user's answer on the specified question.
     * @param question the question.
     * @return the answer.
     */
    String getAnswer(String question);

    /**
     * Verification of response to the range.
     * @param question question for player.
     * @param minLimit min limit.
     * @param maxLimit max limit.
     * @return player's answer.
     * @throws OutOfRangeException out of range exception.
     * @throws NumberFormatException NumberFormatException.
     */
    int getAnswer(String question, int minLimit, int maxLimit) throws OutOfRangeException, NumberFormatException;
}
