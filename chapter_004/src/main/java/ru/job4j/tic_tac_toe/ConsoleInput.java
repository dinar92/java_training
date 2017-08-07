package ru.job4j.tic_tac_toe;

import ru.job4j.boardGame.Input;
import ru.job4j.tic_tac_toe.exception.OutOfRangeException;

import java.util.Scanner;

/**
 * Created by pacman on 11.07.17.
 * Gets the player's answer on a question from the console.
 */
public class ConsoleInput implements Input {

    /**
     * The scanner for reading from the console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Returns the user's answer on the specified question.
     *
     * @param question the question.
     * @return the answer.
     */
    @Override
    public String getAnswer(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Verification of response to the range.
     * @param question question for player.
     * @param minLimit min limit.
     * @param maxLimit max limit.
     * @return player's answer.
     * @throws OutOfRangeException out of range exception.
     * @throws NumberFormatException NumberFormatException.
     */
    @Override
    public int getAnswer(String question, int minLimit, int maxLimit) throws OutOfRangeException, NumberFormatException {
        int answer = Integer.parseInt(this.getAnswer(question));
        if (answer < minLimit || answer > maxLimit) {
            throw new OutOfRangeException(minLimit, maxLimit);
        }
        return answer;
    }
}
