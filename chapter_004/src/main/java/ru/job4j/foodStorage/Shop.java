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
    private List<Food> shop = new ArrayList<>();
    /**
     * Adds the product to the storage.
     * @param food product.
     * @return success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        return shop.add(food);
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
