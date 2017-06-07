package ru.job4j.foodStorage;


import org.junit.Test;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

/**
 * Created by pacman on 07.06.17.
 * Tests class Resortable.
 */
public class ResortableControlQualityTest {

    /**
     * Tests resort().
     */
    @Test
    public void whenResortThenProductMovedToAnotherStorage() {
        Trash trash = new Trash();
        Shop shop = new Shop();
        ResortableControlQuality controlQuality = new ResortableControlQuality();
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 2, monthOfExpiry = 3;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);
        shop.addProduct(food);
        controlQuality.addStorage(shop);
        controlQuality.addStorage(trash);
        controlQuality.resort();

        assertThat(trash.getProducts(), contains(food));
    }
}
