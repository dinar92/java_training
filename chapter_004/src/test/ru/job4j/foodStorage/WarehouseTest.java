package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 24.04.17.
 * Tests class Warehouse.
 */
public class WarehouseTest {

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

        assertThat(new Warehouse().conformityTest(food), is(true));
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

        assertThat(new Warehouse().conformityTest(food), is(false));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenAddProductThenItInStorage() {
        Food food = new Food("Meat", 6.0);
        Shop shop = new Shop();

        shop.addProduct(food);

        assertThat(shop.getProducts(), contains(food));
    }

    /**
     * Tests getProducts().
     */
    @Test
    public void whenGetProductsThenReturnAllInStorage() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(meat);
        warehouse.addProduct(cheese);

        assertThat(warehouse.getProducts(), contains(meat, cheese));
    }

    /**
     * Tests clearStorage().
     */
    @Test
    public void whenClearStorageThenAllIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Warehouse warehouse = new Warehouse();
        boolean storageIsEmpty = true;

        warehouse.addProduct(meat);
        warehouse.addProduct(cheese);
        warehouse.clearStorage();

        assertThat(warehouse.getProducts().isEmpty(), is(storageIsEmpty));
    }

    /**
     * Tests removeProduct().
     */
    @Test
    public void whenRemoveProductThenItIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Warehouse warehouse = new Warehouse();
        boolean storageContainsProduct = false;

        warehouse.addProduct(meat);
        warehouse.addProduct(cheese);
        warehouse.removeProduct(meat);

        assertThat(warehouse.getProducts().contains(meat), is(storageContainsProduct));
    }
}