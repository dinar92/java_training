package ru.job4j.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests ConsoleDisplay.
 */
public class ConsoleDisplayTest {

    /**
     * Tests show().
     */
    @Test
    public void whenSetItemThenSysoutHisInfo() {
        Item item = new Item();
        String prefix = "1";
        int identifier = 1;
        String name = "Menu's item";
        item.init(prefix, identifier, name);
        ConsoleDisplay consoleDisplay = new ConsoleDisplay();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        consoleDisplay.show(item);

        assertThat(out.toString(), is("1.1 Menu's item\n"));
    }
}