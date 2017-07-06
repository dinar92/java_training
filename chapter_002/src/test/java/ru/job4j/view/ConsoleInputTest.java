package ru.job4j.view;

import org.junit.Test;
import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing console input.*/
public class ConsoleInputTest {

    /**
     * The line separator.
     */
    private final String ls = System.lineSeparator();

	/**Testing input of ask(String question).*/
	@Test
	public void whenQuestionThenReturnAnswer() {
		String question = "Why?";
        ByteArrayInputStream bais = new ByteArrayInputStream(("Because\r" + ls).getBytes());

        System.setIn(bais);

		assertThat(new ConsoleInput().ask(question), is("Because"));
	}

    /**
     * Tests input of ask(String question, int[] range).
     */
    @Test
    public void whenInputExitThenReturnExit() {
        ByteArrayInputStream bais = new ByteArrayInputStream(("exit\r" + ls).getBytes());
        int[] range = {1, 2, 3};
        String question = "Why?";

        System.setIn(bais);

        assertThat(new ConsoleInput().ask(question, range), is("exit"));
    }

    /**
     * Tests input of ask(String question, String[] range).
     */
    @Test
    public void whenKeyInRageThenReturnKey() {
        ByteArrayInputStream bais = new ByteArrayInputStream(("3\r" + ls).getBytes());
        String[] range = {"1", "2", "3"};
        String question = "Why?";

        System.setIn(bais);

        assertThat(new ConsoleInput().ask(question, range), is("3"));
    }
}
