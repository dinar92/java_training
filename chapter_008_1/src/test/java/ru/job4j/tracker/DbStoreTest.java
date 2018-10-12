package ru.job4j.tracker;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DbStoreTest {

    /**
     * An instance of user storage.
     */
    private Store<User> store = DbStore.getInstance();

    /**
     * A test role.
     */
    private static Role role;

    static {
        role = new Role();
        role.setId(-1);
        role.setName("Test Role");
        try {
            RoleValidateService.getInstance().add(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cleans databases from test data.
     */
    @AfterClass
    public static void cleanDatabase() throws Exception {
        int[] IDs = {-1, -2, -4, -5, -6, -7};
        int roleID = -1;
        Validate<User> userStore = ValidateService.getInstance();
        Validate<Role> roleStore = RoleValidateService.getInstance();

        for (Integer ID :
                IDs) {
            userStore.delete(ID);
        }

        roleStore.delete(roleID);
    }

    /**
     * Tests add(User). Checks added user existence in the store.
     */
    @Test
    public void whenAddNewUserThenInStore() {
        User userForAdd = new User();
        String userName = "Test User 1";
        Integer userID = new Integer(-1);

        userForAdd.setName(userName);
        userForAdd.setId(userID);
        userForAdd.setRole(role);
        userForAdd.setPassword("pass");
        userForAdd.setLogin("login");
        userForAdd.setCreateDate(LocalDate.now());
        userForAdd.setEmail("login@mail.com");

        this.store.add(userForAdd);

        List<User> userList = this.store.findAll();
        assertThat(userList.stream().anyMatch(user -> user.equals(userForAdd)), Matchers.is(true));
    }

    /**
     * Tests update(User).
     */
    @Test
    public void whenUpdateUserThenUpdatedInStore() {
        User userForUpdate = new User();
        String beforeUpdateName = "Test User 2";
        String updatedName = "Updated Test User 2";
        Integer userID = new Integer(-2);

        userForUpdate.setId(userID);
        userForUpdate.setName(beforeUpdateName);
        userForUpdate.setRole(role);
        userForUpdate.setPassword("pass");
        userForUpdate.setLogin("login");
        userForUpdate.setCreateDate(LocalDate.now());
        userForUpdate.setEmail("login@mail.com");
        this.store.add(userForUpdate);

        userForUpdate.setName(updatedName);
        this.store.update(userForUpdate);

        List<User> userList = this.store.findAll();
        assertThat(userList.stream().anyMatch(user -> user.getName().equals(updatedName)), is(true));
    }

    /**
     * Tests delete(Integer).
     */
    @Test
    public void whenRemoveUserThenIsNotInStore() {
        User userForRemove = new User();
        String userName = "Test User 3";
        Integer userID = new Integer(-3);

        userForRemove.setId(userID);
        userForRemove.setName(userName);
        userForRemove.setRole(role);
        userForRemove.setPassword("pass");
        userForRemove.setLogin("login");
        userForRemove.setCreateDate(LocalDate.now());
        userForRemove.setEmail("login@mail.com");
        this.store.add(userForRemove);

        this.store.delete(userID);

        List<User> userList = this.store.findAll();
        assertFalse(userList.stream().anyMatch(user -> user.equals(userForRemove)));
    }

    /**
     * Tests findAll():List<User>.
     */
    @Test
    public void whenFindAllThenReturnsAddedUsers() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setId(-5);
        user1.setName("Test User 5");
        user1.setRole(role);
        user1.setPassword("pass");
        user1.setLogin("login");
        user1.setCreateDate(LocalDate.now());
        user1.setEmail("login@mail.com");

        user2.setId(-6);
        user2.setName("Test User 6");
        user2.setRole(role);
        user2.setPassword("pass");
        user2.setLogin("login");
        user2.setCreateDate(LocalDate.now());
        user2.setEmail("login@mail.com");

        user3.setId(-7);
        user3.setName("Test User 7");
        user3.setRole(role);
        user3.setPassword("pass");
        user3.setLogin("login");
        user3.setCreateDate(LocalDate.now());
        user3.setEmail("login@mail.com");


        this.store.add(user1);
        this.store.add(user2);
        this.store.add(user3);

        List<User> userList = this.store.findAll();
        assertThat(userList.stream().anyMatch(user -> user.equals(user1)), Matchers.is(true));
        assertThat(userList.stream().anyMatch(user -> user.equals(user2)), Matchers.is(true));
        assertThat(userList.stream().anyMatch(user -> user.equals(user3)), Matchers.is(true));
    }

    /**
     * Tests findById(Integer).
     */
    @Test
    public void whenFindByIDThenReturnsUser() {
        User userForSearch = new User();
        String userName = "Test User 4";
        Integer userID = -4;

        userForSearch.setName(userName);
        userForSearch.setId(userID);
        userForSearch.setRole(role);
        userForSearch.setPassword("pass");
        userForSearch.setLogin("login");
        userForSearch.setCreateDate(LocalDate.now());
        userForSearch.setEmail("login@mail.com");
        this.store.add(userForSearch);

        User foundUser = this.store.findById(userID);
        assertEquals(userForSearch, foundUser);
    }
}