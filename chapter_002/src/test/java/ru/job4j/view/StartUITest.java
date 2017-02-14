package ru.job4j.view;

import org.junit.Test;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;

/**Class for testing StartUI.
*@author gimazetdinov
*@version 1.0
*/
public class StartUITest {

	/**Testing adding item.*/
	@Test
	public void whenAddItemThenCorrectWorking() {
		String[] answers = {"1", "4", "name", "description", "1", "0", "0", "0"};
		new StartUI(new StubInput(answers)).init();
	}

	/**Testing deleting item.*/
	@Test
	public void whenDeleteItemThenCorrectWorking() {
		String[] answers = {"1", "4", "name", "description", "1", "0", "3", "5", "2", "0", "0", "0", "0", "0"};
		new StartUI(new StubInput(answers)).init();
	}

	/**Testing updating item.*/
	@Test
	public void whenUpdateItemThenCorrectWorking() {
		String[] answers = {"1", "4", "name", "description", "3", "2", "1", "1", "newName", "2", "new description", "0", "0", "0", "0", "0", "0"};
		new StartUI(new StubInput(answers)).init();
	}

	/**Testing adding comment to the item.*/
	@Test
	public void whenAddCommentToItemThenCorrectWorking() {
		String[] answers = {"1", "4", "name", "description", "3", "3", "3", "Comment's text", "Comment's author", "0", "0", "0", "0", "0", "0"};
		new StartUI(new StubInput(answers)).init();
	}
}
