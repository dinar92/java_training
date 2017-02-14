package ru.job4j.model;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
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
		final int maxSizeOfCommentsList = 30;
		final int firstComment = 0;
		final long currentTimeInMillis = 1485959504531L;
		Comment com = new Comment();
		com.setAuthor(name);
		com.setText(text);
		com.setCreate(currentTimeInMillis);
		item.addComment(text, name, currentTimeInMillis);
		assertThat(item.getComments()[firstComment], samePropertyValuesAs(com));
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
