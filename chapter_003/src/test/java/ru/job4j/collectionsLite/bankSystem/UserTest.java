package ru.job4j.collectionsLite.bankSystem;

import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 02.09.17.
 * Tests the class User.
 */
public class UserTest {

    /**
     * Tests equals, with same links.
     */
    @Test
    public void whenTheSameLinkThenEquals() {
        User user1 = new User("Mike", 1);
        User user2 = user1;
        boolean expect = true;
        Assert.assertThat(user1.equals(user2), is(expect));
    }

    /**
     * Tests equals(), with null.
     */
    @Test
    public void whenArgumentIsNullThenNotEquals() {
        User user1 = new User("Mike", 1);
        User user2 = null;
        boolean expect = false;
        Assert.assertThat(user1.equals(user2), is(expect));
    }

    /**
     * Tests equals(), incompatibility.
     */
    @Test
    public void whenIncompatibilityArgumentThenNotEquals() {
        User user1 = new User("Mike", 1);
        String argument = new String("Text");
        boolean expect = false;
        Assert.assertThat(user1.equals(argument), is(expect));
    }

    /**
     * Tests equals(), when different users.
     */
    @Test
    public void whenUsersIsNotEqualThenReturnFalse() {
        User user1 = new User("Mike", 1);
        User user2 = new User("John", 2);
        boolean expect = false;
        Assert.assertThat(user1.equals(user2), is(expect));
    }

    /**
     * Tests equals(), when users equal.
     */
    @Test
    public void whenArgumentIsEqualThenReturnsTrue() {
        User user1 = new User("Mike", 1);
        User user2 = new User("Mike", 1);
        boolean expect = true;
        Assert.assertThat(user1.equals(user2), is(expect));

    }

    /**
     * Tests hashCode(), when different hash for different users.
     */
    @Test
    public void whenDifferentUsersThenDifferentHashCode() {
        User user1 = new User("Mike", 1);
        User user2 = new User("John", 2);
        Assert.assertThat(user1.hashCode(), IsNot.not(user2.hashCode()));
    }

    /**
     * Tests hashCode(), when the identical objects.
     */
    @Test
    public void whenIdenticalArgumentThenTheSameHashCode() {
        User user1 = new User("Mike", 1);
        User user2 = new User("Mike", 1);
        Assert.assertThat(user1.hashCode(), is(user2.hashCode()));

    }
}
