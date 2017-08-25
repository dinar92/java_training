package ru.job4j.collectionsLite.collectionsFramework;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by pacman on 23.08.17.
 * Tests class UserConvert.
 */
public class UserConvertTest {

    /**
     * Tests process().
     */
    @Test
    public void whenSetListThenReturnHashMap() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Mike", "Moscow"));
        list.add(new User(2, "Nick", "New York"));
        list.add(new User(3, "Peter", "Bryansk"));

        HashMap<Integer, User> actualMap = new UserConvert().process(list);

        for (User user : list) {
            Assert.assertThat(actualMap, IsMapContaining.hasEntry(user.id, user));
        }
    }
}
