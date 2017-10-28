package ru.job4j.collections.mapCollection;

import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;

/**
 * Tests class User.
 */
public class UserTest {

    /**
     * Tests hashCode().
     */
    @Test
    public void whenEqualsUsersThenEqualsHashCodes() {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("Kate", 16, calendar);
        User user2 = new User("Kate", 16, calendar);

        Assert.assertThat(user1.hashCode(), is(user2.hashCode()));
    }

    /**
     * Tests hashCode().
     */
    @Test
    public void whenNotEqualsUsersThenNotEqualsHashCodes() {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("Kate", 16, calendar);
        User user2 = new User("Mike", 16, calendar);

        Assert.assertThat(user1.hashCode(), IsNot.not(user2.hashCode()));
    }

    /**
     * Tests equals().
     */
    @Test
    public void whenEqualsUsersThenTrue() {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("Kate", 16, calendar);
        User user2 = new User("Kate", 16, calendar);

        Assert.assertThat(user1.equals(user2), is(true));
    }

    /**
     * Tests equals().
     */
    @Test
    public void whenNotEqualsUsersThenFalse() {
        Calendar calendar = new GregorianCalendar();
        User user1 = new User("Kate", 16, calendar);
        User user2 = new User("John", 16, calendar);

        Assert.assertThat(user1.equals(user2), is(false));
    }
}