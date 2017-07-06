package ru.job4j.menu;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests class Menu.
 */
public class MenuTest {

    /**
     * Tests searchItem().
     */
    @Test
    public void whenSearchItemThenGetItem() {
        Menu menu = new Menu();
        String name1 = "First item";

        menu.addItem(name1);
        try {
            assertThat(menu.searchItem("1").getName(), is(name1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Tests addItem().
     */
    @Test
    public void whenAddItemThenItemIsIn() {
        Menu menu = new Menu();
        String name1 = "First item";
        String name2 = "Second item";

        menu.addItem(name1);
        menu.addItem(name2);
        try {
            assertThat(menu.searchItem("1").getName(), is(name1));
            assertThat(menu.searchItem("2").getName(), is(name2));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Tests addSubItem().
     */
    @Test
    public void whenAddSubitemThenSubitemIsIn() {
        Menu menu = new Menu();
        String name1 = "First item";
        String name2 = "Second item";
        String subitem = "First subitem";

        menu.addItem(name1);
        menu.addItem(name2);

        try {
            menu.addSubItem("1", subitem);
            assertThat(menu.searchItem("1.1").getName(), is(subitem));
        } catch (NotFoundItemException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Tests deleteItem().
     */
    @Test
    public void whenDeleteItemThenItemWillBeRemove() {
        Menu menu = new Menu();
        String name1 = "First item";
        String name2 = "Second item";
        Boolean wasRemoved = false;

        menu.addItem(name1);
        menu.addItem(name2);
        try {
            menu.deleteItem("2");
            menu.searchItem("2");
        }catch (NotFoundItemException ex) {
            wasRemoved = true;
        }
        assertThat(wasRemoved, is(true));
    }

    /**
     * Tests showMenu().
     */
    @Test
    public void whenInvokeSHouldShowItems() {
        Menu menu = new Menu();
        String name1 = "First item";
        String name2 = "Second item";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        String expectedOut = "1 First item\n2 Second item\n";


        menu.addItem(name1);
        menu.addItem(name2);
        menu.showMenu();

        assertThat(bos.toString(), is(expectedOut));
    }
}