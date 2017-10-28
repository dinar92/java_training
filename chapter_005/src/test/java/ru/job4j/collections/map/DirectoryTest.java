package ru.job4j.collections.map;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests a class Dictionary.
 */
public class DirectoryTest {

    /**
     * A size of dictionary.
     */
    private int sizeOfDirectory = 19;

    /**
     * An instance of directory.
     */
    private Directory<String, Integer> directory = new Directory<>(sizeOfDirectory);

    /**
     * Tests insert() with the success final.
     */
    @Test
    public void whenPutInUserAndAgeInDirectoryThenItIsIn() {
        String user = "Mike";
        Integer age = new Integer(30);

        directory.insert(user, age);

        assertThat(directory.get(user), is(age));
    }

    /**
     * Tests insert(), with collision.
     */
    @Test
    public void whenCollisionThenReturnsNull() {
        String user1 = "Kate";
        Integer age1 = new Integer(30);

        String user2 = "Kate";
        Integer age2 = new Integer(18);

        directory.insert(user1, age1);

        assertThat(directory.insert(user2, age2), is(false));
    }

    /**
     * Tests delete().
     */
    @Test
    public void whenDeleteItemThenDirectoryNotHasThem() {
        String user = "John";
        Integer age = 33;

        directory.insert(user, age);
        directory.delete(user);

        assertThat(directory.get(user), IsNull.nullValue());
    }

    /**
     * Tests hasNext(), in normal mode.
     */
    @Test
    public void whenDirectoryHasNextThenTrue() {
        Directory<String, Integer> users = new Directory<>(15);
        String user1 = "Kate";
        Integer age1 = new Integer(30);
        String user2 = "Mike";
        Integer age2 = new Integer(18);

        users.insert(user1, age1);
        users.insert(user2, age2);

        Iterator iterator = users.iterator();
        iterator.next();

        assertThat(iterator.hasNext(), is(true));
    }

}