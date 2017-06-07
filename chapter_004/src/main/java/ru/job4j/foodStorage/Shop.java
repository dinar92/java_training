package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 24.04.17.
 * Stores products for sale.
 */
public class Shop implements Storage {

    /**
     * A storage of products.
     */
    private List<Food> storage = new ArrayList<>();
    /**
     * Adds the product to the storage.
     * @param food product.
     * @return success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        return storage.add(food);
    }

    /**
     * Returns all products from the storage.
     * @return products.
     */
    @Override
    public List<Food> getProducts() {
        return this.storage;
    }

    /**
     * Clears the full storage.
     */
    @Override
    public void clearStorage() {
        this.storage.clear();
    }

    /**
     * Removes specified product from the storage.
     * @param food specified product.
     */
    @Override
    public void removeProduct(Food food) {
        this.storage.remove(food);
    }

    /**
     * Checking the product for compliance with expiration dates.
     * Returns true if the product is spoiled by less than 100% and
     * if is spoiled by more or equal than 25%. Return false if product is
     * too fresh or too overdue for sale.
     * @param food product.
     * @return result of checking.
     */
    @Override
    public boolean conformityTest(Food food) {
        return (food.degreeOfDeterioration() >= 25) && (food.degreeOfDeterioration() < 100);
    }
}
