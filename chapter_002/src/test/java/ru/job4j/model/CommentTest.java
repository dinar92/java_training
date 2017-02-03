package ru.job4j.model;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing Comment.*/
public class CommentTest {

	/**Testing setAuthor(String auth) and getAuthor().*/
	@Test
	public void whenSetAuthorThenGetAuthor() {
		String name = "Nick";
		Comment com = new Comment();
		com.setAuthor(name);
		assertThat(com.getAuthor(), is(name));
	}

	/**Testing setText(String text) and getText().*/
	@Test
	public void whenSetTextThenGetText() {
		String text = "Text";
		Comment com = new Comment();
		com.setText(text);
		assertThat(com.getText(), is(text));
	}

	/**Testing setCreate(long millis) and getCreate().*/
	@Test
	public void whenSetCreateThenGetDateAndTime() {
		final long mill = 1486053072507L;
		Comment com = new Comment();
		com.setCreate(mill);
		assertThat(com.getCreate(), is("19:31 2/2/2017"));
	}
}
