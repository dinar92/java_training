package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 24.04.17.
 * Warehouse for fresh products.
 */
public class Warehous implements Storage {

    /**
     * A storage of products.
     */
    private List<Food> warehouse = new ArrayList<>();

    /**
     * Adds the product to the storage.
     * @param food product.
     * @return success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        return warehouse.add(food);
    }

    /**
     * Checking the product for compliance with expiration dates.
     * Returns true if the product is spoiled by less than 25%, or
     * false if is spoiled by more than 25%.
     * @param food product.
     * @return result of checking.
     */
    @Override
    public boolean conformityTest(Food food) {
        return food.degreeOfDeterioration() < 25;
    }
}
