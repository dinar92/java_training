package ru.job4j.collectionsLite.collectionsFramework;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Comparator;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 29.08.17.
 * Tests class ByNameCompare.
 */
public class ByNameCompareTest {

    /**
     * Instance for checking exceptions.
     */
    @Rule
    public ExpectedException ex = ExpectedException.none();

    /**
     * Tests compareTo().
     */
    @Test
    public void whenSecondUserIsGreaterShouldReturnNegativeOne() {
        UserComparable user1 = new UserComparable("B", 25);
        UserComparable user2 = new UserComparable("A", 27);
        Comparator<UserComparable> comparator = new ByNameCompare();

        int expectRes = 1;

        Assert.assertThat(comparator.compare(user1, user2), is(expectRes));
    }

    /**
     * Tests compareTo() with NPE.
     */
    @Test
    public void whenArgIsNullThenNPE() {
        UserComparable user1 = null;
        UserComparable user2 = null;
        Comparator<UserComparable> comparator = new ByNameCompare();

        ex.expect(NullPointerException.class);

        comparator.compare(user1, user2);
    }
}
