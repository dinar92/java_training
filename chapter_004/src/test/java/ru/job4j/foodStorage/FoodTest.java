package ru.job4j.foodStorage;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by pacman on 24.04.17.
 * Tests class Food.
 */
public class FoodTest {

    /**
     * Tests setExpiryDate() and getExpiryDate().
     * */
    @Test
    public void whenSetExpiryDateThenGetSameExpiryDate() {
        int year = 2017;
        int month = 3;
        int dayOfMonth = 25;
        Food food = new Food("Chips", 2.0);

        food.setExpiryDate(year, month, dayOfMonth);

        assertThat(food.getExpiryDate().getTimeInMillis(), is(new GregorianCalendar(year, month - 1, dayOfMonth).getTimeInMillis()));
    }

    /**
     * Tests setCreateDate() and getCreateDate().
     * */
    @Test
    public void whenSetCreateDateThenGetSameCreateDate() {
        int year = 2017;
        int month = 1;
        int dayOfMonth = 5;
        Food food = new Food("Fish", 5.0);

        food.setCreateDate(year, month, dayOfMonth);

        assertThat(food.getCreateDate().getTimeInMillis(), is(new GregorianCalendar(year, month - 1, dayOfMonth).getTimeInMillis()));
    }

    /**
     * Tests degreeOfDeterioration().
     */
    @Test
    public void whenFoodSpoiledThenGetDecayIndication() {
        int yearOfCreate = 2016, yearOfExpiry = 2017;
        int monthOfCreate = 9, monthOfExpiry = 1;
        int dayOfCreate = 1, dayOfExpiry = 30;
        int productIsSpoiled = 100;
        Food food = new Food("Fish", 6.5);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(food.degreeOfDeterioration(), is(greaterThanOrEqualTo(productIsSpoiled)));
    }

}