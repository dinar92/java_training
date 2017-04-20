package ru.job4j.calculator;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 20.04.17.
 * Tests for EngineerCalculator.
 */
public class EngineerCalculatorTest {

    /**
     * Instance of EngineerCalculator.
     */
    EngineerCalculator calc = new EngineerCalculator();

    /**
     * Testing sinus().
     */
    @Test
    public void whenSinThenReturnSin() {
        double multiple = 1.0;
        double angle = 2.0;
        double expect = 0.9092974268256817;
        assertThat(calc.sinus(multiple, angle), is(expect));
    }

    /**
     * Testing cosin().
     */
    @Test
    public void whenCosThenReturnCos() {
        double multiple = 1.0;
        double angle = 2.0;
        double expect = -0.4161468365471424;
        assertThat(calc.cosin(multiple, angle), is(expect));
    }


    /**
     * Testing tangen().
     */
    @Test
    public void whenTangThenReturnTang() {
        double multiple = 1.0;
        double angle = 2.0;
        double expect = -2.185039863261519;
        assertThat(calc.tangens(multiple, angle), is(expect));
    }


    /**
     * Testing doOperation().
     */
    @Test
    public void whenDoOperationThenGetResult() {
        double multiple = 1.0;
        double angle = 2.0;
        String operation = "cos";
        double expect = -0.4161468365471424;
        assertThat(calc.doOperation(multiple, angle, operation), is(expect));
    }
}