package ru.job4j.foodStorage;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 27.04.17.
 * Tests  Ecofriendly.
 */
public class EcofriendlyTest {

    /**
     * Returns overdue Food.
     *
     * @return Food.
     */
    public Food createOverdueFood() {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 2, monthOfExpiry = 3;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("potato", 0.5);
        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);
        return food;
    }

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsProcessedThenReturnTrue() {

        Food food = this.createOverdueFood();

        assertThat(new Ecofriendly(new Trash()).conformityTest(food), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenProductSentToRecyclerThenReturnTrue() {

        Food food = this.createOverdueFood();

        assertThat(new Ecofriendly(new Trash()).addProduct(food), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenSetStorageShouldContainsIt() {
        Trash trash = new Trash();
        Ecofriendly ecofriendly = new Ecofriendly(trash);
        Food food = new Food("meat", 0.5);
        boolean isInStorage = true;

        ecofriendly.addProduct(food);

        assertThat(ecofriendly.getProducts().contains(food), is(isInStorage));
    }
}