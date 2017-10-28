package ru.job4j.collections;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 10.09.17.
 * Tests the class AbstractStore.
 */
public class AbstractStoreTest {

    /**
     * Tests update().
     */
    @Test
    public void whenObjectUpdateThenUpdated() {
        AbstractStore<User> store = new UserStore();
        User oldUser = new User();
        User newUser = new User();
        store.add(oldUser);

        store.update(oldUser, newUser);

        Assert.assertThat(store.contains(oldUser), is(false));
    }

    /**
     * Tests delete().
     */
    @Test
    public void whenDeleteObjectThenRemovedFromStore() {
        AbstractStore<Role> store = new RoleStore();
        Role forDelete = new Role();
        store.add(forDelete);

        store.delete(forDelete);

        Assert.assertThat(store.contains(forDelete), is(false));
    }

    /**
     * Tests contains().
     */
    @Test
    public void whenObjectNotContainsInStoreThenReturnsFalse() {
        AbstractStore<User> store = new UserStore();
        User user1 = new User();
        User user2 = new User();
        User notExist = new User();

        store.add(user1);
        store.add(user2);

        Assert.assertThat(store.contains(notExist), is(false));
    }

    /**
     * Tests contains().
     */
    @Test
    public void whenObjectContainsInStoreThenReturnsTrue() {
        AbstractStore<Role> store = new RoleStore();
        Role role1 = new Role();
        Role role2 = new Role();

        store.add(role1);
        store.add(role2);

        Assert.assertThat(store.contains(role1), is(true));
        Assert.assertThat(store.contains(role2), is(true));
    }
}
