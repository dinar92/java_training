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
     * Tests sortByNameLength.
     */
    @Test
    public void whenPutUnsortedThenReturnSortedByNameLengthList() {
        List<UserComparable> actualList = new ArrayList<>();
        actualList.add(new UserComparable("BB", 18));
        actualList.add(new UserComparable("AAA", 22));
        actualList.add(new UserComparable("C", 16));

        List<UserComparable> list = new SortUser().sortByNameLength(actualList);

        Iterator<UserComparable> iterator = list.iterator();

        Assert.assertThat(iterator.next().name, is("C"));
        Assert.assertThat(iterator.next().name, is("BB"));
        Assert.assertThat(iterator.next().name, is("AAA"));
    }

    /**
     * Tests sortByAllFields().
     */
    @Test
    public void whenPutUnsortedThenReturnsSortedByNameAndAge() {
        List<UserComparable> actualList = new ArrayList<>();

        UserComparable john4 = new UserComparable("John", 18);
        UserComparable alex2 = new UserComparable("Alex", 22);
        UserComparable john3 = new UserComparable("John", 15);
        UserComparable alex1 = new UserComparable("Alex", 16);

        actualList.add(john4);
        actualList.add(alex2);
        actualList.add(john3);
        actualList.add(alex1);

        List<UserComparable> list = new SortUser().sortByAllFields(actualList);

        Iterator<UserComparable> iterator = list.iterator();

        Assert.assertThat(iterator.next(), is(alex1));
        Assert.assertThat(iterator.next(), is(alex2));
        Assert.assertThat(iterator.next(), is(john3));
        Assert.assertThat(iterator.next(), is(john4));
    }

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
