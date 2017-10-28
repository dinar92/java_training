package ru.job4j.collectionsPro;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pacman on 10.09.17.
 * Tests the class Base.
 */
public class BaseTest {

    /**
     * Tests serId(), getId().
     */
    @Test
    public void whenSetIdShouldContainsThatId() {
        Base user = new User();
        String id = "1";

        user.setId(id);

        Assert.assertThat(user.getId(), CoreMatchers.is(id));
    }
}
