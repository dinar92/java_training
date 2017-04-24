package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by pacman on 24.04.17.
 * Tests class Trash.
 */
public class TrashTest {

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsSpiledThenTrue() {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 2, monthOfExpiry = 3;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Trash().conformityTest(food), is(true));
    }

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsGoodForTrashThenFalse() {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 3, monthOfExpiry = 5;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Trash().conformityTest(food), is(false));
    }
}