package ru.job4j.foodStorage;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 27.04.17.
 * Tests  Ecofriendly.
 */
public class RefrigerantTest {

    /**
     * Returns overdue Food.
     * @return Food.
     */
    public Food createFreshVegFood() {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 5, monthOfExpiry = 7;
        int dayOfCreate = 26, dayOfExpiry = 30;
        Food food = new Food("potato", 0.5);
        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);
        return food;
    }

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsVegThenReturnTrue() {

        Food food = this.createFreshVegFood();

        assertThat(new Refrigerant(new Warehous()).conformityTest(food), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenProductSentToRefrigeratorThenReturnTrue() {

        Food food = this.createFreshVegFood();

        assertThat(new Refrigerant(new Warehous()).addProduct(food), is(true));
    }

}