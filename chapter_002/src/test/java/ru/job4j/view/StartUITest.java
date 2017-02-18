package ru.job4j.view;

import ru.job4j.data.Tracker;
import ru.job4j.model.Item;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

/**Class for testing StartUI.
*@author gimazetdinov
*@version 1.0
*/
public class StartUITest {

	/**Testing adding item.*/
	@Test
	public void whenAddItemThenCorrectWorking() {
		String[] answers = {"1", "4", "First name", "First description", "1", "0", "0", "0"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("First name");
		item.setDescription("First description");
		secTracker.add(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("First name"), samePropertyValuesAs(secTracker.findByName("First name")));
	}

	/**Testing deleting item.*/
	@Test
	public void whenDeleteItemThenCorrectWorking() {
		String[] answers = {"1", "4", "Sec name", "Sec description", "1", "0", "3", "6", "2", "0", "0", "0", "0", "0"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Sec name");
		item.setDescription("Sec description");
		secTracker.add(item);
		secTracker.delete(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.getAll(), is(secTracker.getAll()));
	}

	/**Testing updating item.*/
	@Test
	public void whenUpdateItemThenCorrectWorking() {
		String[] answers = {"1", "4", "Third name", "Third description", "3", "3", "1", "1", "newName", "2", "new description", "0", "0", "0", "0", "0", "0"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Third name");
		item.setDescription("Third description");
		item.generateId();
		secTracker.add(item);
		item.setName("newName");
		item.setDescription("new description");
		secTracker.update(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("newName"), samePropertyValuesAs(secTracker.findByName("newName")));
	}

	/**Testing adding comment to the item.*/
	@Test
	public void whenAddCommentToItemThenCorrectWorking() {
		String[] answers = {"1", "4", "Four name", "Four description", "3", "4", "3", "Comment's text", "Comment's author", "0", "0", "0", "0", "0", "0"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Four name");
		item.setDescription("Four description");
		item.addComment("Comment's text", "Comment's author", System.currentTimeMillis());
		secTracker.add(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("Four name"), samePropertyValuesAs(secTracker.findByName("Four name")));
	}
}
