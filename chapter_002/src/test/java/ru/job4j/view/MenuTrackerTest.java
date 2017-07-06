package ru.job4j.view;

import org.junit.Test;
import ru.job4j.data.Tracker;
import ru.job4j.model.Bug;
import ru.job4j.model.Item;
import ru.job4j.model.Task;
import org.laughingpanda.beaninject.Inject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 22.06.17.
 * Tests class MenuTracker.
 */
public class MenuTrackerTest {

    /**
     * System's line separator.
     */
    private final String ls = System.lineSeparator();

    /**
     * Creates the simple class of MenuTracker, Tracker with items and
     * StubInput.
     * @param answers array of answers on questions of MenuTracker.
     * @param type necessary type of items in Tracker.
     * @param countOfItems the count of items in Tracker.
     * @return instance of MenuTracker.
     */
    private MenuTracker createTestMenuTracker(String[] answers, String type, int countOfItems) {
        Tracker tracker = new Tracker();
        Inject.field("idCounter").of(new Item()).with(0);
        Input input = new StubInput(answers);
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        long currentTimeInMillis = 1485959504531L;
        menuTracker.fillAction();
        for (int i = 0; i < countOfItems; i++) {
            Item item;
            if ("task".equalsIgnoreCase(type)) {
                item = new Task();
            } else {
                item = new Bug();
            }
            item.generateId();
            item.setName(String.valueOf(i));
            item.setCreate(currentTimeInMillis);
            item.setDescription(String.valueOf(i));
            tracker.add(item);
        }

        return menuTracker;
    }

    /**
     * Tests the search by name function of MenuTracker.
     */
    @Test
    public void whenFindByNameShouldShowItem() {
        String[] answers = {"1"};
        int keyForFindByName = 4;
        String necessaryTypes = "task";
        int countOfItems = 5;
        MenuTracker menuTracker = this.createTestMenuTracker(answers, necessaryTypes, countOfItems);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expectedOut = new StringBuilder().append("Type: task")
                .append(ls)
                .append("ID: 2")
                .append(ls)
                .append("Create: 17:31 1/2/2017")
                .append(ls)
                .append("Name: 1")
                .append(ls)
                .append("Description: 1")
                .append(ls)
                .append("Comments list:")
                .append(ls).toString();

        menuTracker.select(keyForFindByName);
        assertThat(out.toString(), is(expectedOut));
    }

    /**
     * Tests the search by ID function of MenuTracker.
     */
    @Test
    public void whenFindByIdShouldShowItem() {
        String[] answers = {"1"};
        int keyForFindByID = 3;
        String necessaryTypes = "task";
        int countOfItems = 3;
        MenuTracker menuTracker = this.createTestMenuTracker(answers, necessaryTypes, countOfItems);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expectedOut = new StringBuilder().append("Type: task")
                .append(ls)
                .append("ID: 1")
                .append(ls)
                .append("Create: 17:31 1/2/2017")
                .append(ls)
                .append("Name: 0")
                .append(ls)
                .append("Description: 0")
                .append(ls)
                .append("Comments list:")
                .append(ls).toString();

        menuTracker.select(keyForFindByID);
        assertThat(out.toString(), is(expectedOut));
    }

    /**
     * Tests the show bugs function.
     */
    @Test
    public void whenCreateBugsShouldDisplay() {
        String[] answers = {"1"};
        int keyForShowBugs = 2;
        String necessaryTypes = "bug";
        int countOfItems = 3;
        MenuTracker menuTracker = this.createTestMenuTracker(answers, necessaryTypes, countOfItems);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringBuilder expectedOut = new StringBuilder("All bug's list:\n");
        for (int i = 0; i < countOfItems; i++) {
            expectedOut.append("ID: " + (i + 1))
                    .append(" ")
                    .append("Name: " + i)
                    .append(" ")
                    .append("Create: 17:31 1/2/2017")
                    .append(ls);
        }

        menuTracker.select(keyForShowBugs);
        assertThat(out.toString(), is(expectedOut.toString()));
    }

    /**
     * Tests the show tasks function.
     */
    @Test
    public void whenCreateTasksShouldDisplay() {
        String[] answers = {"2"};
        int keyForShowTasks = 1;
        String necessaryTypes = "task";
        int countOfItems = 3;
        MenuTracker menuTracker = this.createTestMenuTracker(answers, necessaryTypes, countOfItems);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringBuilder expectedOut = new StringBuilder("All tasks:\n");
        for (int i = 0; i < countOfItems; i++) {
            expectedOut.append("ID: " + (i + 1))
                    .append(" ")
                    .append("Name: " + i)
                    .append(" ")
                    .append("Create: 17:31 1/2/2017")
                    .append(ls);
        }

        menuTracker.select(keyForShowTasks);
        assertThat(out.toString(), is(expectedOut.toString()));
    }

    /**
     * Tests the show info about item function.
     */
    @Test
    public void whenSetItemThenShowDetailAboutIt() {
        Tracker tracker = new Tracker();

    }
}
