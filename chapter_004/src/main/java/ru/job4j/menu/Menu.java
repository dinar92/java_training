package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 10.05.17.
 * The items controller.Interface of
 * Provides operations: add item, add subitem to item,
 * search engine of items, removing items and show all items.
 */
public class Menu {

    /**
     * The main container for all other items.
     */
    private final Item mainItem;

    /**
     * The display for menu's items.
     */
    private Display display;

    /**
     * Default constructor creates the main item
     * and setts default display for items.
     */
    public Menu() {
        this.mainItem = new Item();
        this.display = new ConsoleDisplay();
    }

    /**
     * Setts the specified method of display items.
     * @param display display.
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     * Shows all items with subitems as an ordered list.
     */
    public void showMenu() {
        List<Item> list = new ArrayList<>();
        this.mainItem.getAsList(list);
        for (Item item : list) {
            this.display.show(item);
        }
    }

    /**
     * Adds a new item in the main container (this.mainItem).
     * @param name name of the item.
     */
    public void addItem(String name) {
        this.mainItem.addItem(name);
    }

    /**
     * Adds a new subitem to specified item.
     *
     * @param itemsIdentifier parent item's identifier.
     * @param subItemsName name of the subitem.
     * @throws NotFoundItemException throws if specified parent can not be found.
     */
    public void addSubItem(String itemsIdentifier, String subItemsName) throws NotFoundItemException {
        this.searchItem(itemsIdentifier).addItem(subItemsName);
    }

    /**
     * Searches for the specified item.
     * @param identifier identifier of the item.
     * @return item.
     * @throws NotFoundItemException throws if specified parent can not be found.
     */
    public Item searchItem(String identifier) throws NotFoundItemException {
        Item expect = this.mainItem.searchItem(identifier);
        if (expect == null) {
            throw new NotFoundItemException();
        }
        return expect;

    }

    /**
     * Removes for the specified item.
     * @param identifier identifier of the item.
     * @throws NotFoundItemException throws if specified parent can not be found.
     */
    public void deleteItem(String identifier) throws NotFoundItemException {
        if (!identifier.contains(".")) {
            this.mainItem.delete(Integer.parseInt(identifier));
        } else {
            String prefix = identifier.substring(0, identifier.lastIndexOf("."));
            String key = identifier.substring((identifier.lastIndexOf(".") + 1), identifier.length());
            this.searchItem(prefix).delete(Integer.parseInt(key));
        }
    }
}
