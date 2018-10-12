package ru.job4j.tracker;

import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RoleStoreTest {

    /**
     * Cleans the store from test data.
     */
    @AfterClass
    public static void cleanStore() {
        int[] ids = {-1, -2, -4, -5, -6, -7};
        Store<Role> store = RoleStore.getInstance();
        for (Integer id :
                ids) {
            store.delete(id);
        }
    }

    /**
     * An instance of role storage.
     */
    private Store<Role> store = RoleStore.getInstance();

    /**
     * Tests add(Role). Checks added role existence in the store.
     */
    @Test
    public void whenAddNewRoleThenInStore() {
        Role roleForAdd = new Role();
        String roleName = "Test Role 1";
        Integer roleID = new Integer(-1);

        roleForAdd.setName(roleName);
        roleForAdd.setId(roleID);

        this.store.add(roleForAdd);

        List<Role> roleList = this.store.findAll();
        assertTrue(roleList.stream().anyMatch(role -> role.equals(roleForAdd)));
    }

    /**
     * Tests update(Role).
     */
    @Test
    public void whenUpdateRoleThenUpdatedInStore() {
        Role roleForUpdate = new Role();
        String beforeUpdateName = "Test Role 2";
        String updatedName = "Updated Test Role 2";
        Integer roleID = new Integer(-2);

        roleForUpdate.setId(roleID);
        roleForUpdate.setName(beforeUpdateName);
        this.store.add(roleForUpdate);

        roleForUpdate.setName(updatedName);
        this.store.update(roleForUpdate);

        List<Role> roleList = this.store.findAll();
        assertTrue(roleList.stream().anyMatch(role -> role.getName().equals(updatedName) &&
                role.getId().equals(roleID)));
    }

    /**
     * Tests delete(Integer).
     */
    @Test
    public void whenRemoveRoleThenIsNotInStore() {
        Role roleForRomove = new Role();
        String roleName = "Test Role 3";
        Integer roleID = new Integer(-3);

        roleForRomove.setId(roleID);
        roleForRomove.setName(roleName);
        this.store.add(roleForRomove);

        this.store.delete(roleID);

        List<Role> roleList = this.store.findAll();
        assertFalse(roleList.stream().anyMatch(role -> role.equals(roleForRomove)));
    }

    /**
     * Tests findAll():List<Role>.
     */
    @Test
    public void whenFindAllThenReturnsAddedRoles() {
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();

        role1.setId(-5);
        role2.setId(-6);
        role3.setId(-7);
        role1.setName("Test Role 5");
        role2.setName("Test Role 6");
        role3.setName("Test Role 7");

        this.store.add(role1);
        this.store.add(role2);
        this.store.add(role3);

        List<Role> roleList = this.store.findAll();
        assertTrue(roleList.stream().anyMatch(role -> role.equals(role1)));
        assertTrue(roleList.stream().anyMatch(role -> role.equals(role2)));
        assertTrue(roleList.stream().anyMatch(role -> role.equals(role3)));
    }

    /**
     * Tests findById(Integer).
     */
    @Test
    public void whenFindByIDThenReturnsRole() {
        Role roleForSearch = new Role();
        String roleName = "Test Role 4";
        Integer roleID = -4;

        roleForSearch.setName(roleName);
        roleForSearch.setId(roleID);
        this.store.add(roleForSearch);

        Role foundRole = this.store.findById(roleID);
        assertEquals(roleForSearch, foundRole);
    }
}