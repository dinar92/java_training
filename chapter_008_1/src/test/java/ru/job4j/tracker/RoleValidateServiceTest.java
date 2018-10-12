package ru.job4j.tracker;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for RoleValidateService class.
 */
public class RoleValidateServiceTest {

    /**
     * The role storage.
     */
    private static Validate<Role> roleStore = RoleValidateService.getInstance();

    /**
     * The users store.
     */
    private static Validate<User> userStore = ValidateService.getInstance();

    /**
     * A credential verifier.
     */
    private Credential<String, String> credential = RoleValidateService.getInstance();

    /**
     * The exception handler.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Cleans test data from the storage.
     *
     * @throws Exception - some exception.
     */
    @AfterClass
    public static void cleanRoleStoreFromTestData() throws Exception {
        int[] userIDs = {-1, -2};
        for (int ID :
                userIDs) {
            if (userStore.findById(ID) != null)
            userStore.delete(ID);
        }

        int[] roleIDs = {-1, -3, -5, -8, -9, -10};
        for (int ID :
                roleIDs) {
            if (roleStore.findById(ID) != null)
            roleStore.delete(ID);
        }
    }

    /**
     * Tests adding role to storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenAddingCorrectRoleThenSuccess() throws Exception {
        Integer id = -1;
        String name = "mockRole";
        Role mockRole = new Role();
        mockRole.setId(id);
        mockRole.setName(name);

        roleStore.add(mockRole);

        Role actualRole = roleStore.findById(id);
        assertThat(actualRole, is(mockRole));
    }

    /**
     * Tests exception of the adding role with empty fields.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenAddingNotFullRoleThenException() throws Exception {
        Role mockRole = new Role();
        mockRole.setId(-2);

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Role's fields must be contain the values");

        roleStore.add(mockRole);
    }

    /**
     * Tests exception of adding null value to the storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenAddNullToStoreThenException() throws Exception {
        Role mockRole = null;

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Role must not be null");

        roleStore.add(mockRole);
    }

    /**
     * Tests adding role with id, which already contains in the storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenRoleWithSpecifiedIDAlreadyExistThenException() throws Exception {
        Integer id = -3;
        String name = "Test role";
        Role mockRole = new Role();
        Role copyMockRole = new Role();
        mockRole.setId(id);
        mockRole.setName(name);
        copyMockRole.setId(id);
        copyMockRole.setName(name);

        roleStore.add(mockRole);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Role with this ID already exists");

        roleStore.add(copyMockRole);
    }

    /**
     * Tests updating role, which not contains in the storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUpdateNotExistingRoleThenException() throws Exception {
        Integer id = -4;
        String name = "Test name";
        Role mockRole = new Role();
        mockRole.setId(id);
        mockRole.setName(name);

        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("Role with this ID do not exist");

        roleStore.update(mockRole);
    }

    /**
     * Tests updating role.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUpdateExistingRoleThenSuccess() throws Exception {
        Integer id = -5;
        String name = "Test name";
        String newName = "New test name";
        Role mockRole = new Role();

        mockRole.setId(id);
        mockRole.setName(name);

        roleStore.add(mockRole);

        Role mockRoleForUpdate = roleStore.findById(id);
        mockRoleForUpdate.setName(newName);

        roleStore.update(mockRoleForUpdate);

        Role updatedMockRole = roleStore.findById(id);

        assertThat(updatedMockRole, is(mockRoleForUpdate));
    }

    /**
     * Tests removing role from the storage.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenRoleDeleteThenSuccess() throws Exception {
        Role mockRole = new Role();
        Integer id = -6;
        mockRole.setId(id);
        mockRole.setName("Test name");

        roleStore.add(mockRole);
        roleStore.delete(id);

        Role actualRole = roleStore.findById(id);

        assertNull(actualRole);
    }

    /**
     * Tests deleting function with null argument.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenIdForDeleteIsNullThenException() throws Exception {
        Integer id = null;

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("ID can not be null");

        roleStore.delete(id);
    }

    /**
     * Tests deleting with the not existing id.
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenRoleNotExistForDeleteThenException() throws Exception {
        Integer fakeId = -7;

        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("Role with this ID do not exist");

        roleStore.delete(fakeId);
    }

    /**
     * When user has a specified role then true (is credential).
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserHasRoleThenIsCredential() throws Exception {
        Role mockRole = new Role();
        mockRole.setId(-8);
        mockRole.setName("test_role");

        User mockUser = new User();
        mockUser.setId(-1);
        mockUser.setRole(mockRole);
        mockUser.setLogin("test_login");
        mockUser.setCreateDate(LocalDate.now());
        mockUser.setEmail("test@test.com");
        mockUser.setPassword("test_password");
        mockUser.setName("test_name");

        roleStore.add(mockRole);
        userStore.add(mockUser);

        assertTrue(credential.isCredential(mockUser.getLogin(), mockRole.getName()));
    }

    /**
     * When user has another role then false (is not credential).
     *
     * @throws Exception - some exception.
     */
    @Test
    public void whenUserHasNotRoleThenFalse() throws Exception {
        Role mockRole = new Role();
        mockRole.setId(-9);
        mockRole.setName("test_role");

        Role anotherMockRole = new Role();
        anotherMockRole.setId(-10);
        anotherMockRole.setName("another_test_role");

        User mockUser = new User();
        mockUser.setId(-2);
        mockUser.setRole(mockRole);
        mockUser.setLogin("test_login");
        mockUser.setCreateDate(LocalDate.now());
        mockUser.setEmail("test@test.com");
        mockUser.setPassword("test_password");
        mockUser.setName("test_name");

        roleStore.add(mockRole);
        roleStore.add(anotherMockRole);
        userStore.add(mockUser);

        assertFalse(credential.isCredential(mockUser.getLogin(), anotherMockRole.getName()));
    }
}
