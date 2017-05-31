package ru.job4j.foodStorage;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 24.04.17.
 * Tests class Warehouse.
 */
public class WarehousTest {

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsFreshThenTrue() throws Exception {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 5, monthOfExpiry = 7;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Warehous().conformityTest(food), is(true));
    }

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsBadThenFalse() throws Exception {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 1, monthOfExpiry = 5;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Warehous().conformityTest(food), is(false));
    }

}