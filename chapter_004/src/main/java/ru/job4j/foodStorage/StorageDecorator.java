package ru.job4j.foodStorage;

import java.util.List;

/**
 * Created by pacman on 27.04.17.
 */
public class StorageDecorator implements Storage {

    /**
     * A storage of food.
     */
    private Storage storage;

    /**
     * Definition of a decorated storage.
     *
     * @param storage storage.
     */
    StorageDecorator(Storage storage) {
        this.storage = storage;
    }

    /**
     * Checking the product for compliance with expiration dates.
     * Returns true if a product meets the requirements, or
     * false if does not meet the requirements.
     *
     * @param food product.
     * @return result of checking.
     */
    public boolean conformityTest(Food food) {
        return this.storage.conformityTest(food);
    }

    /**
     * Adds the product to the storage.
     *
     * @param food product.
     * @return success of adding.
     */
    public boolean addProduct(Food food) {
        return this.storage.addProduct(food);
    }

    /**
     * Returns all products from the storage.
     * @return products.
     */
    @Override
    public List<Food> getProducts() {
        return this.storage.getProducts();
    }

    /**
     * Clears the full storage.
     */
    @Override
    public void clearStorage() {
        this.storage.clearStorage();
    }

    /**
     * Removes specified product from the storage.
     * @param food specified product.
     */
    @Override
    public void removeProduct(Food food) {
        this.storage.removeProduct(food);
    }
}
