package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 27.04.17.
 * Decorator for creating a eco-friendly trash storage.
 */
public class Ecofriendly extends StorageDecorator {

    /**
     * List of products for processing.
     */
    String[] canReproduct = {"potato", "tomato"};

    /**
     * Container for products, that will be process.
     */
    List<Food> containerForReproduct = new ArrayList<>();

    /**
     * Definition of a decorated storage.
     * @param storage storage.
     */
    Ecofriendly(Storage storage) {
        super(storage);
    }

    /**
     * The product will be sent for recycling if it's name is in list.
     * @param food product.
     * @return success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        boolean added = false;
        for (String product : canReproduct) {
            if (food.getName().equals(product)) {
                added = this.containerForReproduct.add(food);
                break;
            }
        }
        return added || super.addProduct(food);
    }
}
