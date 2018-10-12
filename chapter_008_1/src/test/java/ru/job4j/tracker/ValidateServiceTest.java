package ru.job4j.tracker;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for the ValidateService.
 */
public class ValidateServiceTest {

    /**
     * A mock role.
     */
    private static Role mockRole;

    /**
     * A storage of roles.
     */
    private static Validate<Role> roleStore = RoleValidateService.getInstance();

    /**
     * A storage of users.
     */
    private static Validate<User> userStore = ValidateService.getInstance();

    /**
     * A logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateServiceTest.class);

    /**
     * An exceptions handler.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Initialize the mock role in the storage.
     */
    static {
        mockRole = new Role();
        mockRole.setId(-1);
        mockRole.setName("TestRole");
        try {
            roleStore.add(mockRole);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Cleans databases after tests.
     *
     * @throws Exception - some exception.
     */
    @AfterClass
    public static void cleanStoreFromTestData() throws Exception {
        int[] IDs = {-1, -2, -4, -7};
        for (int ID :
                IDs) {
            userStore.delete(ID);
        }
        roleStore.delete(-1);
    }

    /**
     * Tests correct adding a new user to storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenAddNewUserThenAddingSuccess() throws Exception {
        Integer id = -1;
        String name = "TestUserName";
        String password = "Test";
        String mail = "test@test.com";
        String login = "TestUserLogin";
        User mockUser = new User();

        mockUser.setId(id);
        mockUser.setName(name);
        mockUser.setPassword(password);
        mockUser.setRole(mockRole);
        mockUser.setEmail(mail);
        mockUser.setCreateDate(LocalDate.now());
        mockUser.setLogin(login);

        userStore.add(mockUser);

        User innerUser = userStore.findById(id);

        assertThat(mockUser, is(innerUser));
    }

    /**
     * Tests when user's reference is null, then throws Null Pointer Exception.
     *
     * @throws Exception - some exception.
     */
    @Test(expected = NullPointerException.class)
    public void whenInsertNullThenException() throws Exception {
        User nullUser = null;
        userStore.add(nullUser);
    }

    /**
     * When user with specified id already exists in the storage then throws exception.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserAddsTwiceThenException() throws Exception {
        Integer id = -2;
        String name = "TestUserName";
        String password = "Test";
        String mail = "test@test.com";
        String login = "TestUserLogin";
        User mockUserFirst = new User();
        User mockUserSecond = new User();
        LocalDate date = LocalDate.now();

        mockUserFirst.setId(id);
        mockUserFirst.setName(name);
        mockUserFirst.setPassword(password);
        mockUserFirst.setEmail(mail);
        mockUserFirst.setLogin(login);
        mockUserFirst.setCreateDate(date);
        mockUserFirst.setRole(mockRole);

        mockUserSecond.setId(id);
        mockUserSecond.setName(name);
        mockUserSecond.setPassword(password);
        mockUserSecond.setEmail(mail);
        mockUserSecond.setLogin(login);
        mockUserSecond.setCreateDate(date);
        mockUserSecond.setRole(mockRole);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User with this ID already exists");

        userStore.add(mockUserFirst);
        userStore.add(mockUserSecond);
    }

    /**
     * When the user has empty fields, then throws exception.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenAddNotFilledUserThenException() throws Exception {
        User mockUser = new User();

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("User's fields must be contain the values");

        userStore.add(mockUser);
    }

    /**
     * Tests correct updating function.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenCorrectUpdateThenUpdated() throws Exception {
        Integer id = -4;
        User oldUser = new User();
        User updatedUser = new User();
        LocalDate date = LocalDate.now();

        oldUser.setId(id);
        oldUser.setName("test name");
        oldUser.setPassword("test_password");
        oldUser.setEmail("test@test.com");
        oldUser.setLogin("test_login");
        oldUser.setCreateDate(date);
        oldUser.setRole(mockRole);

        updatedUser.setId(id);
        updatedUser.setName("new_test_name");
        updatedUser.setPassword("new_test_password");
        updatedUser.setEmail("new@test.com");
        updatedUser.setLogin("new_test_login");
        updatedUser.setCreateDate(date);
        updatedUser.setRole(mockRole);

        userStore.add(oldUser);
        userStore.update(updatedUser);
        User actualUser = userStore.findById(id);

        assertThat(actualUser, IsNot.not(oldUser));
        assertThat(actualUser, is(updatedUser));
    }

    /**
     * When updating user is not in the database, then throws exception.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUpdatingUserNotExistThenException() throws Exception {
        User mockUser = new User();
        mockUser.setId(-3);
        mockUser.setName("TestName");
        mockUser.setPassword("TestPassword");
        mockUser.setRole(mockRole);
        mockUser.setEmail("Test@test.com");
        mockUser.setCreateDate(LocalDate.now());
        mockUser.setLogin("TestLogin");

        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("User with such ID not found");

        userStore.update(mockUser);
    }

    /**
     * When id is null, then throws exception.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenDeletedUserIdIsNullThenException() throws Exception {
        Integer id = null;

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("ID is null");

        userStore.delete(id);
    }

    /**
     * When updating user nit in the storage, then exception.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserIsNotInStoreThenException() throws Exception {
        Integer fakeId = -6;

        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("User with such ID not found");

        userStore.delete(fakeId);
    }

    /**
     * Tests correct removing from the storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenDeleteUserThenCorrectRemoved() throws Exception {
        Integer id = -5;
        User mockUser = new User();
        mockUser.setId(id);
        mockUser.setName("TestName");
        mockUser.setPassword("TestPassword");
        mockUser.setRole(mockRole);
        mockUser.setEmail("Test@test.com");
        mockUser.setCreateDate(LocalDate.now());
        mockUser.setLogin("TestLogin");

        userStore.add(mockUser);
        userStore.delete(id);

        User actualUser = userStore.findById(id);
        assertThat(actualUser, IsNull.nullValue());
    }

    /**
     * Tests credential function.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserIsCredentialThenTrue() throws Exception {
        Credential<String, String> credential = ValidateService.getInstance();
        Integer id = -7;
        String login = "test_login";
        String password = "test_password";
        User mockUser = new User();

        mockUser.setLogin(login);
        mockUser.setPassword(password);
        mockUser.setId(id);
        mockUser.setName("TestName");
        mockUser.setRole(mockRole);
        mockUser.setEmail("Test@test.com");
        mockUser.setCreateDate(LocalDate.now());

        userStore.add(mockUser);

        assertTrue(credential.isCredential(login, password));
    }

    /**
     * Tests credential function, if not credential.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserNotCredentialThenFalse() throws Exception {
        Credential<String, String> credential = ValidateService.getInstance();
        Integer id = -8;
        String login = "test_login2";
        String password = "test_password2";
        User mockUser = new User();

        mockUser.setId(id);
        mockUser.setLogin(login);
        mockUser.setPassword(password);
        mockUser.setName("TestName");
        mockUser.setRole(mockRole);
        mockUser.setEmail("Test@test.com");
        mockUser.setCreateDate(LocalDate.now());

        userStore.add(mockUser);

        assertTrue(credential.isCredential(login, password));

        userStore.delete(id);

        assertFalse(credential.isCredential(login, password));
    }
}