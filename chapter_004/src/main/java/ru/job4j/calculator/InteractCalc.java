package ru.job4j.calculator;


import java.io.IOException;

/**
 * Created by pacman on 16.04.17.
 */
public class InteractCalc {

    /**
     * The instance of the calculator.
     */
    private final BaseCalculator cal;

    /**
     * The instance of interaction with user.
     */
    private final UsersInput usersInput;

    /**
     * Default constructor.
     * @param cal instance of the calculator.
     * @param usersInput instance of interaction with user.
     */
    public InteractCalc(BaseCalculator cal, UsersInput usersInput) {
        this.cal = cal;
        this.usersInput = usersInput;
    }

    /**
     * Constant for exit of the calculator.
     */
    private final String exit = "exit";

    /**
     * Constant for clean the memory of the calculator.
     */
    private final String clean = "C";

    /**
     * Launches calculator.
     * @param args args
     */
    public static void main(String[] args) {
        new InteractCalc(new EngineerCalculator(), new ConsoleInput()).calculate();
    }

    /**
     * Implementation of calculator logic.
     */
    public void calculate() {
        try {
            String command = "", operation = "";
            Boolean repeat = true;
            double arg1, arg2, result = 0.0;
            do {
                if (repeat) {
                    arg1 = Double.parseDouble(usersInput.getUsersInput("Enter first arg:"));
                    operation = usersInput.getUsersInput("Enter operation:");
                    repeat = false;
                } else {
                    arg1 = result;
                    operation = command;
                }
                arg2 = Double.parseDouble(usersInput.getUsersInput("Enter second arg:"));

                result = cal.doOperation(arg1, arg2, operation);
                System.out.println("Result: " + result);
                command = usersInput.getUsersInput("Enter operation again | enter \"С\" to clean | enter \"exit\" : ");
                if (clean.equals(command)) {
                    repeat = true;
                }
            } while (!exit.equals(command));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}