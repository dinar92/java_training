package ru.job4j.view;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Testing console input.*/
public class ConsoleInputTest {

	/**Testing input of ask().*/
	@Test
	public void whenQuestionThenReturnAnswer() {
		StubInput input = new StubInput(new String[] {"Because"});
		String question = "Why?";
		String answer = input.ask(question);
		assertThat(answer, is("Because"));
	}
}
