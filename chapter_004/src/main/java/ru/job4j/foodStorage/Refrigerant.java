package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 28.04.17.
 * Refrigerant function.
 */
public class Refrigerant extends StorageDecorator {
    /**
     * Definition of a decorated storage.
     *
     * @param storage storage.
     */
    Refrigerant(Storage storage) {
        super(storage);
    }

    /**
     * The list of vegetables.
     */
    String[] listOfVegetables = {"potato", "carrot", "cabbage"};

    /**
     * The refrigerator.
     */
    List<Food> refrigerator = new ArrayList<>();

    /**
     * Checking the product for compliance with expiration dates and
     * contains in the list of vegetables for refrigerator.
     * @param food product.
     * @return corresponds or not.
     */
    @Override
    public boolean addProduct(Food food) {
        boolean added = false;
        for (String product : listOfVegetables) {
            if (food.getName().equals(product)) {
                added = this.refrigerator.add(food);
                break;
            }
        }
        return added || super.addProduct(food);
    }
}