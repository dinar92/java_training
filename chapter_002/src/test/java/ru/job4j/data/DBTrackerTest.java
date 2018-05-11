package ru.job4j.data;

import org.junit.AfterClass;
import org.junit.Test;
import ru.job4j.model.Bug;
import ru.job4j.model.Item;
import ru.job4j.model.Task;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for DBTracker.
 */
public class DBTrackerTest {

    /**
     * The file with the driver name of the database.
     */
    private final File driver = new File("src/test/java/ru/job4j/data/driver");
    /**
     * The file with the URL of the database.
     */
    private final File url = new File("src/test/java/ru/job4j/data/url");
    /**
     * The connection to the database.
     */
    private Connection connection = null;
    /**
     * Date in milliseconds.
     */
    private final long timeInMillis = 1523391395416L;

    /**
     * Date in the string format.
     */
    private final String timeInFormat = "23:16 10/4/2018";

    /**
     * Opens connection to the database.
     */
    private void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database1.sqlite");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to the database.
     */
    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes the database from the file system after all tests.
     */
    @AfterClass
    public static void removeDatabase() {
        new File("database1.sqlite").delete();
    }

    /**
     * Tests adding the item to the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenAddItemThenItemInDatabase() throws Exception {
        String name = "test1";
        String desc = "desc1";
        Item item = getNewItem(name, desc, Type.BUG);
        item.addComment("comment", "author", this.timeInMillis);
        try (DBTracker tracker = new DBTracker(driver, url)) {
            tracker.add(item);
        }
        this.openConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM item WHERE name = '%s';", name));
            while (result.next()) {
                assertThat(result.getString("name"), is(name));
                assertThat(result.getString("description"), is(desc));
                assertThat(result.getString("createDate"), is(timeInFormat));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeConnection();
    }

    /**
     * Tests findById() of the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenFindByIdThenGetItem() throws Exception {
        String id = null;
        String name = "test2";
        String desc = "desc2";
        Item item = getNewItem(name, desc, Type.TASK);
        Item resultItem = null;
        try (DBTracker tracker = new DBTracker(driver, url)) {
            tracker.add(item);
        }
        openConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM item WHERE name = ? AND description = ?;")) {
            statement.setString(1, name);
            statement.setString(2, desc);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = String.valueOf(resultSet.getInt("id"));
            }
            try (DBTracker tracker = new DBTracker(driver, url)) {
                resultItem = tracker.findById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        assertThat(item.getName(), is(resultItem.getName()));
        assertThat(item.getDescription(), is(resultItem.getDescription()));
    }

    /**
     * Tests update() of the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenUpdateItemThenUpdatedInDatabase() throws Exception {
        String name = "test3";
        String desc = "desc3";
        Item item = getNewItem(name, desc, Type.TASK);
        try (DBTracker tracker = new DBTracker(driver, url)) {
            tracker.add(item);
        }

        //Get the id of the added item from the database.
        int idOfExist = Integer.parseInt(item.getId());
        //Update the item.
        String updatedName = "test3 updated";
        String updatedDesc = "desc3 updated";
        item.setName(updatedName);
        item.setDescription(updatedDesc);
        item.addComment("new comment", "author", this.timeInMillis);
        try (DBTracker tracker = new DBTracker(driver, url)) {
            tracker.update(item);
        }
        int idOfUpdated = Integer.parseInt(item.getId());
        assertThat(idOfExist, is(idOfUpdated));
    }

    /**
     * Tests findByName() of the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenFindByNameThenGetsListOfItems() throws Exception {
        String name1 = "test4", name2 = "test4", name3 = "test4";
        String desc1 = "desc4", desc2 = "desc5", desc3 = "desc6";
        Item item1 = getNewItem(name1, desc1, Type.BUG);
        Item item2 = getNewItem(name2, desc2, Type.TASK);
        Item item3 = getNewItem(name3, desc3, Type.TASK);
        ArrayList<Item> actualList = null;
        item3.addComment("comment", "author", this.timeInMillis);
        try (DBTracker tracker = new DBTracker(driver, url)) {
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            actualList = tracker.findByName("test4");
        }
        assertThat(actualList.get(0).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
        assertThat(actualList.get(1).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
        assertThat(actualList.get(2).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
    }

    /**
     * Tests delete the item from the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenRemovedFromTrackerThenItemNotIn() throws Exception {
        try (DBTracker tracker = new DBTracker(driver, url)) {
            String name = "test5";
            String desc = "desc7";
            Item item = getNewItem(name, desc, Type.TASK);

            tracker.add(item);

            Item existItem = tracker.findById(item.getId());

            assertThat(existItem.getName(), is(item.getName()));
            assertThat(existItem.getDescription(), is(item.getDescription()));

            tracker.delete(item);
            Item notExistItem = tracker.findById(item.getId());

            assertThat(notExistItem, nullValue());
        }

    }

    /**
     * Tests getAll() of the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenGetAllItemsThenReturnsListOfItems() throws Exception {
        try (DBTracker tracker = new DBTracker(driver, url)) {
            String[] ids = new String[]{"1", "2", "3", "4", "5", "6", "7"};
            List<Item> actualItems = tracker.getAll();

            for (Item item : actualItems) {
                assertThat(item.getId(), isIn(ids));
            }
        }
    }

    /**
     * Tests getIds() of the tracker.
     * @throws Exception - Exception.
     */
    @Test
    public void whenGetIdsThenReturnsListOfIds() throws Exception {
        try (DBTracker tracker = new DBTracker(driver, url)) {
            String[] ids = new String[]{"1", "3", "2", "4", "5", "6", "7"};
            List<String> actualIds = tracker.getIds();

            for (String id : actualIds) {
                assertThat(id, isIn(ids));
            }
        }
    }

    /**
     * Service method. Returns the new item with specified parameters.
     * @param name - the name.
     * @param description - the dexcription.
     * @param type - the type.
     * @return - new Item.
     */
    private Item getNewItem(String name, String description, Type type) {
        Item item;
        if (type == Type.BUG) {
            item = new Bug();
        } else {
            item = new Task();
        }
        item.setName(name);
        item.setDescription(description);
        item.setCreate(this.timeInMillis);
        item.generateId();
        return item;
    }
}