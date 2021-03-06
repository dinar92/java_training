package ru.job4j.foodStorage;

import java.util.List;

/**
 * Created by pacman on 23.04.17.
 * A storage of food.
 */
public interface Storage {

    /**
     * Checking the product for compliance with expiration dates.
     * Returns true if a product meets the requirements, or
     * false if does not meet the requirements.
     * @param food product.
     * @return result of checking.
     */
    boolean conformityTest(Food food);

    /**
     * Adds the product to the storage.
     * @param food product.
     * @return success of adding.
     */
    boolean addProduct(Food food);

    /**
     * Returns all products from the storage.
     * @return products.
     */
    List<Food> getProducts();

    /**
     * Clears the full storage.
     */
    void clearStorage();

    /**
     * Removes specified product from the storage.
     * @param food specified product.
     */
    void removeProduct(Food food);
}
