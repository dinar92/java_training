package ru.job4j.calculator;

/**
 * Created by pacman on 19.04.17.
 * Standard implementation of a calculator.
 */
public class BaseCalculator extends Calculator {

    /**
     * Unified interface, that gets the result of specified expression.
     * @param arg1 first arg
     * @param arg2 second arg
     * @param operation calculation operation
     * @return result of expression
     * @throws ArithmeticException division by zero
     */
    public double doOperation(double arg1, double arg2, String operation) throws ArithmeticException {
        if ("+".equals(operation)) {
            this.add(arg1, arg2);
        } else if ("-".equals(operation)) {
            this.substract(arg1, arg2);
        } else if ("*".equals(operation)) {
            this.multiple(arg1, arg2);
        } else if ("/".equals(operation)) {
            this.div(arg1, arg2);
        }

        return this.getResult();
    }
}
