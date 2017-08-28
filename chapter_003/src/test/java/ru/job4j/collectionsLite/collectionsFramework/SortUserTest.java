package ru.job4j.collectionsLite.collectionsFramework;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 27.08.17.
 * Tests class SortUser.
 */
public class SortUserTest {

    /**
     * Tests sort().
     */
    @Test
    public void whenPutUnsortedListShouldReturnSortedSet() {
        List<UserComparable> actualList = new ArrayList<>();
        actualList.add(new UserComparable("Alex", 22));
        actualList.add(new UserComparable("Mike", 16));
        actualList.add(new UserComparable("John", 18));

       Set<UserComparable> set = new SortUser().sort(actualList);

       Iterator<UserComparable> iterator = set.iterator();

        Assert.assertThat(iterator.next().age, is(16));
        Assert.assertThat(iterator.next().age, is(18));
        Assert.assertThat(iterator.next().age, is(22));
    }
}
