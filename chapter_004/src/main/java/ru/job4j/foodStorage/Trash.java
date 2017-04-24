package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 24.04.17.
 * Stores products for utilization.
 */
public class Trash implements Storage {

    /**
     * A storage of products.
     */
    private List<Food> trash = new ArrayList<>();

    /**
     * Adds the product to the storage.
     * @param food product.
     * @return success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        return trash.add(food);
    }

    /**
     * Checking the product for compliance with expiration dates.
     * Returns true if the product is spoiled by more than 100%, or
     * false if is spoiled by less than or equal 100%.
     * @param food product.
     * @return result of checking.
     */
    @Override
    public boolean conformityTest(Food food) {
        return (food.degreeOfDeterioration() >= 100);
    }
}
