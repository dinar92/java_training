package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Display;
import ru.job4j.boardGame.Input;
import ru.job4j.tic_tac_toe.exception.OutOfRangeException;

/**
 * Created by pacman on 29.07.17.
 * Checking user input for a integer range.
 */
public class IntegerInputVerificator implements NumberInputVerificator {

    /**
     * Provides the relation with players.
     */
    private final Input input;

    /**
     * Provides the info for players.
     */
    private final Display display;

    /**
     * Default constructor.
     * Sets input and output method.
     * @param input input.
     * @param display output.
     */
    public IntegerInputVerificator(Input input, Display display) {
        this.display = display;
        this.input = input;
    }

    /**
     * Verification of response to the range of integers.
     * Asking from player while answer is not verified.
     *
     * @param question question for player.
     * @param minLimit min limit.
     * @param maxLimit max limit.
     * @return player's answer.
     */
    public int getVerifiedAnswer(String question, int minLimit, int maxLimit) {
        int answer = 0;
        boolean isCorrect;
        do {
            try {
                answer = input.getAnswer(question, minLimit, maxLimit);
                isCorrect = true;
            } catch (OutOfRangeException | NumberFormatException ex) {
                this.display.showln("Please, give the correct answer!");
                isCorrect = false;
            }
        } while (!isCorrect);
        return answer;
    }
}


