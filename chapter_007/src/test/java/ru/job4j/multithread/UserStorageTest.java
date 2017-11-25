package ru.job4j.multithread;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests the class UserStorage.
 */
public class UserStorageTest {

    /**
     * The storage of users.
     */
    UserStorage<User> storage = new UserStorage<>();

    /**
     * Tests addUser(User).
     */
    @Test
    public void whenUserWasAddToStorageThenStorageContainsHim() {
        int usersId = 1;
        User user = new User(usersId, 100);
        storage.addUser(user);

        assertThat(storage.getUserById(usersId), is(user));
    }

    /**
     * Tests deleteUser(User).
     */
    @Test
    public void whenDeleteUserThenStorageNotContainsHim() {
        int usersId = 2;
        User user = new User(usersId, 200);
        storage.addUser(user);

        storage.deleteUser(user);

        assertThat(storage.getUserById(usersId), IsNull.nullValue());
    }

    /**
     * Tests transfer(int fromId, int toId, int amount).
     */
    @Test
    public void whenTransferAmountThenTransferIsSuccess() {
        int amount1 = 200;
        int amount2 = 50;
        int transferAmount = 100;
        int id1 = 3;
        int id2 = 4;
        storage.addUser(new User(id1, amount1));
        storage.addUser(new User(id2, amount2));

        storage.transfer(id1, id2, transferAmount);

        assertThat(storage.getUserById(id2).getAmount(), is(amount2 + transferAmount));
        assertThat(storage.getUserById(id1).getAmount(), is(amount1 - transferAmount));
    }

    /**
     * Tests transfer(int fromId, int toId, int amount), if amount is not enough.
     */
    @Test
    public void whenAmountIsNotEnoughThenTransferNotSuccess() {
        int amount1 = 20;
        int amount2 = 50;
        int transferAmount = 100;
        int id1 = 3;
        int id2 = 4;
        storage.addUser(new User(id1, amount1));
        storage.addUser(new User(id2, amount2));

        storage.transfer(id1, id2, transferAmount);

        assertThat(storage.getUserById(id2).getAmount(), is(amount2));
        assertThat(storage.getUserById(id1).getAmount(), is(amount1));
    }
}