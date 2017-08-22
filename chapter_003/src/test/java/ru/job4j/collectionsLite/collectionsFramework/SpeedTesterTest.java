package ru.job4j.collectionsLite.collectionsFramework;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by pacman on 09.08.17.
 * Tests class SpeedTest.
 */
public class SpeedTesterTest {

    /**
     * Te instance of the speed tester.
     */
    private final SpeedTester tester = new SpeedTester();

    /**
     * The amount of strings.
     */
    private final int amountOfStrings = 100000;

    /**
     * Tests the time of adding for LinkedList.
     */
    @Test
    public void whenLinkedListThenGetResultOfAdd() {
        long time = tester.add(new LinkedList<String>(), amountOfStrings);
        System.out.println(String.format("LinkedList : add = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }

    /**
     * Tests the time of removing for LinkedList.
     */
    @Test
    public void whenLinkedListThenGetResultOfRemove() {
        long time = tester.delete(new LinkedList<String>(), amountOfStrings);
        System.out.println(String.format("LinkedList : delete = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }

    /**
     * Tests the time of adding for ArrayList.
     */
    @Test
    public void whenArrayListThenGetResultOfAdd() {
        long time = tester.add(new ArrayList<>(), amountOfStrings);
        System.out.println(String.format("ArrayList : add = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }

    /**
     * Tests the time of removing for ArrayList.
     */
    @Test
    public void whenArrayListThenGetResultOfRemove() {
        long time = tester.delete(new ArrayList<String>(), amountOfStrings);
        System.out.println(String.format("ArrayList : delete = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }

    /**
     * Tests the time of adding for TreeSet.
     */
    @Test
    public void whenTreeSetThenGetResultOfAdd() {
        long time = tester.add(new TreeSet<String>(), amountOfStrings);
        System.out.println(String.format("TreeSet : add = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }

    /**
     * Tests the time of removing for TreeSet.
     */
    @Test
    public void whenTreeSetThenGetResultOfRemove() {
        long time = tester.delete(new TreeSet<String>(), amountOfStrings);
        System.out.println(String.format("TreeSet: delete = %s", time));
        Assert.assertThat(time, Matchers.greaterThan(0L));
    }
}
