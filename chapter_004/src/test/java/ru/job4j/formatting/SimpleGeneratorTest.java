package ru.job4j.formatting;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by pacman on 11.06.17.
 * Tests class SimpleGenerator.
 */
public class SimpleGeneratorTest {

    /**
     * Rule of exception.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Tests generate() in normal mode.
     */
    @Test
    public void whenSetsTemplateShouldGetsFormattedString() {
        Template template = new SimpleGenerator();
        String stringForFormat = "Hello, ${name}!";
        Map<String, String> args = new HashMap<>();
        String expected = "Hello, world!";

        args.put("name", "world");
        try {
            String actual = template.generate(stringForFormat, args);
            assertThat(actual, is(expected));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Tests generate() with multiple matches to one arg.
     */
    @Test
    public void whenManyMatchesToOneArgShouldGetsFormattedString() {
        Template template = new SimpleGenerator();
        String stringForFormat = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> args = new HashMap<>();
        String expected = "Help, Aaa, Aaa, Aaa";

        args.put("sos", "Aaa");
        try {
            String actual = template.generate(stringForFormat, args);
            assertThat(actual, is(expected));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Tests generate() when template's key not in the Map.
     * @throws KeyNotFoundException            - key not contains in Map.
     * @throws RedundantElementsFoundException - Map contains unused elements.
     * @throws InvalidArgumentException        - one or more args equals to null.
     */
    @Test
    public void whenKeyNotFoundInListShouldException() throws KeyNotFoundException, RedundantElementsFoundException, InvalidArgumentException {
        Template template = new SimpleGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Nick");
        String stringForFormatting = "Hello, ${name} ${surname}";
        String expectedMessage = "The next values not found among args: surname.";

        expectedException.expect(KeyNotFoundException.class);
        expectedException.expectMessage(expectedMessage);
        template.generate(stringForFormatting, args);
    }

    /**
     * Tests generate() when Map has redundant elements.
     * @throws KeyNotFoundException            - key not contains in Map.
     * @throws RedundantElementsFoundException - Map contains unused elements.
     * @throws InvalidArgumentException        - one or more args equals to null.
     */
    @Test
    public void whenRedundantKeysInMapShouldException() throws KeyNotFoundException, RedundantElementsFoundException, InvalidArgumentException {
        Template template = new SimpleGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Nick");
        args.put("surname", "Fisher");
        String stringForFormatting = "Hello, ${name}";
        String expectedMessage = "Found redundant elements with keys: surname.";

        expectedException.expect(RedundantElementsFoundException.class);
        expectedException.expectMessage(expectedMessage);
        template.generate(stringForFormatting, args);
    }

    /**
     * Tests generate() when invokes with nulls.
     * @throws KeyNotFoundException            - key not contains in Map.
     * @throws RedundantElementsFoundException - Map contains unused elements.
     * @throws InvalidArgumentException        - one or more args equals to null.
     */
    @Test
    public void whenArgsEqualToNullShouldException() throws KeyNotFoundException, RedundantElementsFoundException, InvalidArgumentException {
        Template template = new SimpleGenerator();
        Map<String, String> args = null;
        String stringForFormatting = null;
        String expectedMessage = "Please, check arguments for equality to null!";

        expectedException.expect(InvalidArgumentException.class);
        expectedException.expectMessage(expectedMessage);
        template.generate(stringForFormatting, args);
    }
}