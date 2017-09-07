package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 24.04.17.
 * Tests class Trash.
 */
public class TrashTest {

    /**
     * Tests conformityTest().
     */
    @Test
    public void whenProductIsSpoiledThenTrue() {
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
        int monthOfCreate = 6, monthOfExpiry = 10;
        int dayOfCreate = 23, dayOfExpiry = 30;
        Food food = new Food("Meat", 6.0);

        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);

        assertThat(new Trash().conformityTest(food), is(false));
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
        Trash trash = new Trash();

        trash.addProduct(meat);
        trash.addProduct(cheese);

        assertThat(trash.getProducts(), contains(meat, cheese));
    }

    /**
     * Tests clearStorage().
     */
    @Test
    public void whenClearStorageThenAllIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Trash trash = new Trash();
        boolean storageIsEmpty = true;

        trash.addProduct(meat);
        trash.addProduct(cheese);
        trash.clearStorage();

        assertThat(trash.getProducts().isEmpty(), is(storageIsEmpty));
    }

    /**
     * Tests removeProduct().
     */
    @Test
    public void whenRemoveProductThenItIsRemoved() {
        Food meat = new Food("Meat", 6.0);
        Food cheese = new Food("Cheese", 2.0);
        Trash trash = new Trash();
        boolean storageContainsProduct = false;

        trash.addProduct(meat);
        trash.addProduct(cheese);
        trash.removeProduct(meat);

        assertThat(trash.getProducts().contains(meat), is(storageContainsProduct));
    }
}