package ru.job4j.view;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for user interaction software.
 *
 * @author gimazetdinov
 * @version 1.0
 */
public class ConsoleInput implements Input {

    /**
     * Console input reader.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * User's input read answer on question.
     *
     * @param question - question
     * @return user's input
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * User's answer on question with validate.
     *
     * @param question - question
     * @param range    - range of keys
     * @return user's inputed number of menu or string of the input if not number
     * @throws MenuOutException      - when user's input number is not in range of menu numbers
     * @throws NumberFormatException - when answer is not "exit"
     */
    public String ask(String question, int[] range) throws MenuOutException, NumberFormatException {
        boolean exist = false;
        String key = this.ask(question);
        if ("exit".equals(key)) {
            return key;
        }
        int intKey = Integer.parseInt(key);
        for (int num : range) {
            if (num == intKey) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu items!");
        }
    }

    /**
     * User's answer on question with validate.
     *
     * @param question - question
     * @param array    - allowable strings
     * @return user's inputed string, which contains in allowable array
     * @throws MenuOutException - when user's input number is not in array of allowable answers
     */
    public String ask(String question, String[] array) throws MenuOutException {
        boolean exist = false;
        String key = this.ask(question);
        for (String item : array) {
            if (item.equals(key) || "exit".equalsIgnoreCase(key)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("This is not valid input!");
        }
    }

    /**
     * User's answer on question with validate.
     *
     * @param question - question.
     * @param array    - allowable values.
     * @param <E>      type of values.
     * @return user's inputed string, which contains in allowable array.
     * @throws MenuOutException - when user's input number is not in the list of allowable answers.
     */
    @Override
    public <E> String ask(String question, ArrayList<E> array) {
        boolean exist = false;
        String key = this.ask(question);
        for (E item : array) {
            if (item.toString().equals(key) || "exit".equalsIgnoreCase(key)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("This is not valid input!");
        }
    }
}
