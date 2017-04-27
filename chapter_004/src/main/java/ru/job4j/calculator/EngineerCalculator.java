package ru.job4j.calculator;

/**
 * Created by pacman on 20.04.17.
 * Implementation of an engineer calculator.
 */
public class EngineerCalculator extends BaseCalculator {

    /**
     * Returns the trigonometric sine of an angle.
     *
     * @param arg1 multiplier.
     * @param arg2 an angle, in radians.
     * @return the sine of the argument.
     */
    public double sinus(double arg1, double arg2) {
        return arg1 * Math.sin(arg2);
    }

    /**
     * Returns the trigonometric cosine of an angle.
     *
     * @param arg1 multiplier.
     * @param arg2 an angle, in radians.
     * @return the cosine of the argument.
     */
    public double cosin(double arg1, double arg2) {
        return arg1 * Math.cos(arg2);
    }

    /**
     * Returns the trigonometric tangent of an angle.
     *
     * @param arg1 multiplier.
     * @param arg2 an angle, in radians.
     * @return the tangent of the argument.
     */
    public double tangens(double arg1, double arg2) {
        return arg1 * Math.tan(arg2);
    }

    /**
     * Unified interface, that gets the result of specified expression.
     *
     * @param arg1      first arg.
     * @param arg2      second arg.
     * @param operation calculation operation.
     * @return result of expression.
     * @throws ArithmeticException division by zero.
     */
    @Override
    public double doOperation(double arg1, double arg2, String operation) throws ArithmeticException {
        double result = super.doOperation(arg1, arg2, operation);
        if ("sin".equals(operation)) {
            result = this.sinus(arg1, arg2);
        } else if ("cos".equals(operation)) {
            result = this.cosin(arg1, arg2);
        } else if ("tan".equals(operation)) {
            result = this.tangens(arg1, arg2);
        }
        return result;
    }
}
