package ru.job4j.menu;

/**
 * Created by pacman on 10.05.17.
 * Shows menu's items in the console.
 */
public class ConsoleDisplay implements Display {

    /**
     * Shows specified element.
     *
     * @param item item.
     */
    @Override
    public void show(Item item) {
            System.out.println(String.join(" ",
                    item.getIdentifier(),
                    item.getName()));
    }
}
