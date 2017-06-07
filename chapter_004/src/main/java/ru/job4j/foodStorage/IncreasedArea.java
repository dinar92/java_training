package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 27.04.17.
 * Expands storage area.
 */
public class IncreasedArea extends StorageDecorator {

    /**
     * Definition of a decorated storage.
     * @param storage storage.
     */
    IncreasedArea(Storage storage) {
        super(storage);
    }

    /**
     * Additional area.
     */
    List<Food> storage = new ArrayList<>();

    /**
     * One more storage will be added if the add(Food food) returns false in case of lack of space in the main storage.
     * @param food product.
     * @return false if storage is full, true - success of adding.
     */
    @Override
    public boolean addProduct(Food food) {
        return super.addProduct(food) || this.storage.add(food);
    }
}
