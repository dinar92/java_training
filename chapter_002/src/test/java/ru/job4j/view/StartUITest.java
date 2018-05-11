package ru.job4j.view;

import org.junit.Test;
import ru.job4j.data.Tracker;
import ru.job4j.model.Item;

import java.io.IOException;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Class for testing StartUI.
*@author gimazetdinov
*@version 2.0
*/
public class StartUITest {

	/**Testing adding item.*/
	@Test
	public void whenAddItemThenCorrectWorking() {
		String[] answers = {"0", "First name", "First description", "task", "exit"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("First name");
		item.setDescription("First description");
		secTracker.add(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("First name"), samePropertyValuesAs(secTracker.findByName("First name")));
	}

	/**Testing adding comment to the item.*/
	@Test
	public void whenAddCommentToItemThenCorrectWorking() throws IOException {
		String[] answers = {"0", "Four name", "Four description", "task", "7", "15", "Comment's text", "Comment's author", "exit"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Four name");
		item.setDescription("Four description");
		item.setId("2");
		item.addComment("Comment's text", "Comment's author", System.currentTimeMillis());
		secTracker.add(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("Four name"), samePropertyValuesAs(secTracker.findByName("Four name")));
	}


	/**Testing deleting item.*/
	@Test
	public void whenDeleteItemThenCorrectWorking() {
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Sec name");
		item.setDescription("Sec description");
		item.setId("4");
		secTracker.add(item);
		secTracker.delete(item);

		String[] answers = {"0", "Sec name", "Sec description", "bug", "5", "17", "exit"};
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.getAll(), is(secTracker.getAll()));
	}

	/**Testing updating item.*/
	@Test
	public void whenUpdateItemThenCorrectWorking() {
		String[] answers = {"0", "Third name", "Third description", "task", "6", "14", "newName", "new description", "exit"};
		Tracker tracker = new Tracker();
		Tracker secTracker = new Tracker();
		Item item = new Item();
		item.setName("Third name");
		item.setDescription("Third description");
		item.setId("1");
		secTracker.add(item);
		item.setName("newName");
		item.setDescription("new description");
		secTracker.update(item);
		new StartUI(new StubInput(answers), tracker).init();
		assertThat(tracker.findByName("newName"), samePropertyValuesAs(secTracker.findByName("newName")));
	}
}
