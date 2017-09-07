package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 24.04.17.
 * Tests class Shop.
 */
public class ShopTest {

    /**
     * Tests conformityTest().
     * @throws Exception exception.
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
     * @throws Exception exception.
     */
    @Test
    public void whenProductIsGoodForSaleThenTrue() throws Exception {
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 4, monthOfExpiry = 10;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Shop().conformityTest(food), is(true));
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
        Shop shop = new Shop();

        shop.addProduct(meat);
        shop.addProduct(cheese);

        assertThat(shop.getProducts(), contains(meat, cheese));
    }

    /**
     * Tests clearStorage().
     */
    @Test
    public void whenClearStorageThenAllIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Shop shop = new Shop();
        boolean storageIsEmpty = true;

        shop.addProduct(meat);
        shop.addProduct(cheese);
        shop.clearStorage();

        assertThat(shop.getProducts().isEmpty(), is(storageIsEmpty));
    }

    /**
     * Tests removeProduct().
     */
    @Test
    public void whenRemoveProductThenItIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Shop shop = new Shop();
        boolean storageContainsProduct = false;

        shop.addProduct(meat);
        shop.addProduct(cheese);
        shop.removeProduct(meat);

        assertThat(shop.getProducts().contains(meat), is(storageContainsProduct));
    }
}