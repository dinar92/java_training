package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 17.04.17.
 * Tests the class InteractCalc.
 */
public class InteractCalcTest {

    private final String ls = System.getProperty("line.separator");

    /**
     * Tests calculate().
     */
    @Test
    public void whenCalculateThenGetResult() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(String.join(ls,
                "5.0",
                "/",
                "2.0",
                "exit" +
                        ls).getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String result = String.join(ls,
                "Enter first arg:",
                "Enter operation:",
                "Enter second arg:",
                "Result: 2.5",
                "Enter operation again | enter \"С\" to clean | enter \"exit\" : " + ls);
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
        InteractCalc calc = new InteractCalc(new BaseCalculator(), new ConsoleInput());
        calc.calculate();
        assertThat(outputStream.toString(), is(result));
    }

    /**
     * Tests main().
     */
    @Test
    public void whenStartThroughMainShouldCalculate() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(String.join(ls,
                "6.0",
                "/",
                "2.0",
                "+",
                "2.0",
                "exit" +
                        ls).getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String result = String.join(ls,
                "Enter first arg:",
                "Enter operation:",
                "Enter second arg:",
                "Result: 3.0",
                "Enter operation again | enter \"С\" to clean | enter \"exit\" : ",
                "Enter second arg:",
                "Result: 5.0",
                "Enter operation again | enter \"С\" to clean | enter \"exit\" : " + ls);
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
        InteractCalc.main(new String[0]);
        assertThat(outputStream.toString(), is(result));
    }
}