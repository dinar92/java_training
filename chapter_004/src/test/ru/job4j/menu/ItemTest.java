package ru.job4j.menu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

/**
 * Tests class Item.
 */
public class ItemTest {

    /**
     * Creates the simple menu.
     * @return the simple menu.
     */
    private Item createSimpleMainItem() {
        Item mainItem = new Item();
        String name1 = "First item";
        String name2 = "Second item";
        String nameSub1 = "First subitem";
        String nameSub2 = "Second subitem";

        mainItem.addItem(name1);
        mainItem.addItem(name2);
        mainItem.searchItem("1").addItem(nameSub1);
        mainItem.searchItem("2").addItem(nameSub2);

        return mainItem;
    }

    /**
     * Tests addItem().
     */
    @Test
    public void whenAddItemThenItemInMenu() {
        Item mainItem = this.createSimpleMainItem();
        String name = "Third item";

        mainItem.addItem(name);

        assertThat(mainItem.searchItem("3").getName(), is(name));
    }

    /**
     * Tests searchItem().
     */
    @Test
    public void whenSearchItemThenGetItem() {
        Item mainItem = this.createSimpleMainItem();

        assertThat(mainItem.searchItem("1.1").getName(), is("First subitem"));
    }

    /**
     * Tests the method delete().
     */
    @Test
    public void whenDeleteItemThenDeleted() {
        Item mainItem = this.createSimpleMainItem();

        mainItem.searchItem("").delete(1);

        assertThat(mainItem.searchItem("1").getName(), is("Second item"));
    }

    /**
     * Tests getAsList().
     */
    @Test
    public void whenTestAsListThenGetOrderedListOfItems() {
        Item mainItem = this.createSimpleMainItem();
        List<Item> list = new ArrayList<>();
        mainItem.getAsList(list);
        String[] treeOfNames = {"First item", "First subitem",
                "Second item",
                "Second subitem"};
        ArrayList<String> orderedListOfItemsName = new ArrayList<>();

        for (Item item : list) {
            orderedListOfItemsName.add(item.getName());
        }

        assertThat(orderedListOfItemsName, contains(treeOfNames));
    }
}