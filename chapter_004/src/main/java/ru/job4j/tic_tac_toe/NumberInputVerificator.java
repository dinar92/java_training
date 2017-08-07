package ru.job4j.tic_tac_toe;

/**
 * Created by pacman on 27.07.17.
 * Checking user input for a number range.
 */
public interface NumberInputVerificator {

    /**
     * Verification of response to the range of numbers.
     * Asking from player while answer is not verified.
     *
     * @param question question for player.
     * @param minLimit min limit.
     * @param maxLimit max limit.
     * @return player's answer.
     */
    int getVerifiedAnswer(String question, int minLimit, int maxLimit);
}


