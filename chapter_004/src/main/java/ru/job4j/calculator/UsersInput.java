package ru.job4j.calculator;

import java.io.IOException;

/**
 * Created by pacman on 19.04.17.
 */
public interface UsersInput {

    /**
     * Return user's input on a given line.
     * @param outForUser given line.
     * @return user's input.
     * @throws IOException IOException.
     */
    String getUsersInput(String outForUser) throws IOException;
}
