package ru.job4j.multithread;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class UserTest {

    /**
     * The instance of base user.
     */
    User user = new User(1, 100);

    /**
     * Tests equals(User).
     */
    @Test
    public void whenEqualToAnotherOrderThenTrue() {
        User anotherUser = new User(1, 100);

        MatcherAssert.assertThat(user.equals(anotherUser), is(true));
        MatcherAssert.assertThat(user.hashCode(), is(anotherUser.hashCode()));
    }
}