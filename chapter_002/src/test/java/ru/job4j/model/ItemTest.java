package ru.job4j.model;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing Item.*/
public class ItemTest {

	/**Testing setName(String name) and getName().*/
	@Test
	public void whenSetNameThenName() {
		Item item = new Item();
		String name = "Task";
		item.setName(name);
		assertThat(item.getName(), is(name));
	}

	/**Testing setDescription(String desc) and getDescription().*/
	@Test
	public void whenSetDescrThenDescr() {
		Item item = new Item();
		String desc = "Description";
		item.setDescription(desc);
		assertThat(item.getDescription(), is(desc));
	}

	/**Testing addComment(String text, String auth) and getComments().*/
	@Test
	public void whenAddCommentThenCommentInArray() {
		Item item = new Item();
		String name = "name";
		String text = "text";
		final long currentTimeInMillis = 1485959504531L;
		final String currentDateTime = "17:31 1/2/2017";
		final int firstComment = 0;
		Comment com = new Comment();

		com.setAuthor(name);
		com.setText(text);
		com.setCreate(currentTimeInMillis);
		item.addComment(text, name, currentTimeInMillis);

		assertThat(item.getComments()[firstComment].getCreate(), is(currentDateTime));
		assertThat(item.getComments()[firstComment].getAuthor(), is(name));
		assertThat(item.getComments()[firstComment].getText(), is(text));
	}

	/**Testing addComment(), when amount of comments more than 30
	 * don't must add new comment.*/
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void whenAddCommentsListIsFullThenNotAddNewCommment() {
		Item item = new Item();
		String name = new String();
		String text = new String();
		long currentTimeInMillis = 1485959504531L;
		final int maxCountOfCommentsPlusOne = 30;

		/**
		 * Must create 30 comments.
		 */
		for (int i = 0; i <= maxCountOfCommentsPlusOne; i++) {
			Comment com = new Comment();
			com.setAuthor(String.valueOf(i));
			com.setText(String.valueOf(i));
			com.setCreate(currentTimeInMillis++);
			item.addComment(text, name, currentTimeInMillis);
		}

		Comment comments = item.getComments()[maxCountOfCommentsPlusOne];
	}

	/**Testing setCreate(long millis) and getCreate().*/
	@Test
	public void whenSetCreateThenName() {
		Item item = new Item();
		final long timeInMillis = 1485959504531L;
		item.setCreate(timeInMillis);
		assertThat(item.getCreate(), is("17:31 1/2/2017"));
	}


	/**Testing generateId() and getId().*/
	@Test
	public void whenGenerateIdThenUniqueId() {
		Item item = new Item();
		String idOfFirstItem = "1";
		item.generateId();
		assertThat(item.getId(), is(idOfFirstItem));
	}
}
