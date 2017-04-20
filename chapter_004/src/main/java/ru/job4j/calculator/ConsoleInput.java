package ru.job4j.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pacman on 19.04.17.
 * Gets user's input from a console.
 */
public class ConsoleInput implements UsersInput {

    /**
     * Reader from console.
     */
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Return user's input on a given line.
     *
     * @param consoleOut given line.
     * @return user's input.
     * @throws IOException IOException.
     */
    public String getUsersInput(String consoleOut) throws IOException {
        System.out.println(consoleOut);
        return br.readLine();
    }
}
