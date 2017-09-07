package ru.job4j.foodStorage;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 27.04.17.
 * Tests  Ecofriendly.
 */
public class RefrigerantTest {

    /**
     * Returns overdue Food.
     *
     * @return Food.
     */
    public Food createFreshVegFood() {
        int yearOfCreate = 2017, yearOfExpiry = 2018;
        int monthOfCreate = 7, monthOfExpiry = 12;
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

        assertThat(new Refrigerant(new Warehouse()).conformityTest(food), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenProductSentToRefrigeratorThenReturnTrue() {

        Food food = this.createFreshVegFood();

        assertThat(new Refrigerant(new Warehouse()).addProduct(food), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenSetStorageShouldContainsIt() {
        Shop shop = new Shop();
        Refrigerant refrigerant = new Refrigerant(shop);
        Food food = new Food("egg", 0.5);
        boolean isInStorage = true;

        refrigerant.addProduct(food);

        assertThat(refrigerant.getProducts().contains(food), is(isInStorage));
    }
}