package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by pacman on 24.04.17.
 * Tests class Shop.
 */
public class ShopTest {

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsTooFreshThenFalse() throws Exception {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 4, monthOfExpiry = 5;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Shop().conformityTest(food), is(false));
    }

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsGoodForSaleThenTrue() throws Exception {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 4, monthOfExpiry = 8;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Shop().conformityTest(food), is(true));
    }
}