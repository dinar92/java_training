package ru.job4j.collectionsLite.collectionsFramework;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Created by pacman on 28.08.17.
 * Tests class UserComparable.
 */
public class UserComparableTest {

    /**
     * The instance of the checker exceptions.
     */
    @Rule
    public ExpectedException ex = ExpectedException.none();

    /**
     * Tests compareTo().
     */
    @Test
    public void whenThisAgeMoreThanParamsAgeThenReturnPositiveNumber() {
        UserComparable youngUser = new UserComparable("Mike", 17);
        UserComparable olderUser = new UserComparable("John", 55);

        Assert.assertThat(olderUser.compareTo(youngUser), Matchers.greaterThan(0));
    }

    /**
     * Tests compareTo() with NPE.
     */
    @Test
    public void whenArgIsNullThenNPE() {
        UserComparable user = new UserComparable("George", 45);
        UserComparable secondUser = null;

        ex.expect(NullPointerException.class);

        user.compareTo(secondUser);
    }

}
