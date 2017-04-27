package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by pacman on 19.04.17.
 * Tests BaseCalculator.
 */
public class BaseCalculatorTest {

    /**
     * Gets the result of specified expression.
     * @throws Exception
     */
    @Test
    public void whenDoOperationThenGetRsult() throws ArithmeticException{
        BaseCalculator calc = new BaseCalculator();
        double arg1 = 5.0;
        double arg2 = 2.0;
        double result = arg1 / arg2;
        String division = "/";
        assertThat(calc.doOperation(arg1, arg2, division), is(result));
    }
}