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

public class DBTrackerTest {

    private final File driver = new File("src/test/java/ru/job4j/data/driver");
    private final File url = new File("src/test/java/ru/job4j/data/url");
    private final DBTracker tracker = new DBTracker(driver, url);
    private Connection connection = null;
    private final long timeInMillis = 1523391395416L;
    private final String timeInFormat = "23:16 10/4/2018";


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

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public static void removeDatabase() {
        new File("database1.sqlite").delete();
    }

    @Test
    public void whenAddItemThenItemInDatabase() {
        String name = "test1";
        String desc = "desc1";
        Item item = getNewItem(name, desc, Type.BUG);
        item.addComment("comment", "author", this.timeInMillis);

        tracker.add(item);
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

    @Test
    public void whenFindByIdThenGetItem() {
        String id = null;
        String name = "test2";
        String desc = "desc2";
        Item item = getNewItem(name, desc, Type.TASK);

        tracker.add(item);
        openConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM item WHERE name = ? AND description = ?;")) {
            statement.setString(1, name);
            statement.setString(2, desc);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = String.valueOf(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        Item resultItem = tracker.findById(id);
        assertThat(item.getName(), is(resultItem.getName()));
        assertThat(item.getDescription(), is(resultItem.getDescription()));
    }

    @Test
    public void whenUpdateItemThenUpdatedInDatabase() {
        String name = "test3";
        String desc = "desc3";
        Item item = getNewItem(name, desc, Type.TASK);

        this.tracker.add(item);

        //Get the id of the added item from the database.
        int idOfExist = Integer.parseInt(item.getId());
        //Update the item.
        String updatedName = "test3 updated";
        String updatedDesc = "desc3 updated";
        item.setName(updatedName);
        item.setDescription(updatedDesc);
        item.addComment("new comment", "author", this.timeInMillis);
        this.tracker.update(item);
        int idOfUpdated = Integer.parseInt(item.getId());
        assertThat(idOfExist, is(idOfUpdated));
    }

    @Test
    public void whenFindByNameThenGetsListOfItems() {
        String name1 = "test4", name2 = "test4", name3 = "test4";
        String desc1 = "desc4", desc2 = "desc5", desc3 = "desc6";
        Item item1 = getNewItem(name1, desc1, Type.BUG);
        Item item2 = getNewItem(name2, desc2, Type.TASK);
        Item item3 = getNewItem(name3, desc3, Type.TASK);
        item3.addComment("comment", "author", this.timeInMillis);

        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        ArrayList<Item> actualList = tracker.findByName("test4");

        assertThat(actualList.get(0).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
        assertThat(actualList.get(1).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
        assertThat(actualList.get(2).getDescription(), anyOf(equalTo(desc1), equalTo(desc2), equalTo(desc3)));
    }

    @Test
    public void whenRemovedFromTrackerThenItemNotIn() {
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

    @Test
    public void whenGetAllItemsThenReturnsListOfItems() {
        String[] ids = new String[]{"1", "2", "4", "5", "6", "7"};
        List<Item> actualItems = tracker.getAll();

        for (Item item : actualItems) {
            assertThat(item.getId(), isIn(ids));
        }
    }

    @Test
    public void  whenGetIdsThenReturnsListOfIds() {
        String[] ids = new String[]{"1", "2", "4", "5", "6", "7"};
        List<String> actualIds = tracker.getIds();

        for (String id : actualIds) {
            assertThat(id, isIn(ids));
        }
    }

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