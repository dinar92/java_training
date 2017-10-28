package ru.job4j.collections;

import org.junit.Test;

/**
 * Tests speed measurement of Sets.
 */
public class SetSpeedTests {

    /**
     * Prints a result of measurements.
     */
    @Test
    public void whenSpedTestsThenOptimizedSetIsFaster() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        OptimizedSimpleSet<Integer> optimizedSimpleSet = new OptimizedSimpleSet<>();

        long result1 = 0;
        long result2 = 0;

        for (int j = 0; j < 100; j++) {
            long start1 = System.nanoTime();
            for (int i = 100; i < 0; i--) {
                simpleSet.add(new Integer(i));
                simpleSet.add(new Integer(i / 2));
                simpleSet.add(new Integer(100 - i));
            }
            result1 += System.nanoTime() - start1;
        }

        for (int j = 0; j < 100; j++) {
            long start2 = System.nanoTime();
            for (int i = 100; i < 0; i--) {
                optimizedSimpleSet.add(new Integer(i));
                optimizedSimpleSet.add(new Integer(i / 2));
                optimizedSimpleSet.add(new Integer(100 - i));
            }
            result2 += System.nanoTime() - start2;
        }

        result1 /= 100;
        result2 /= 100;

        System.out.println("simple = " + result1);
        System.out.println("optim = " + result2);
    }
}
