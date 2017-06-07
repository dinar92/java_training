package ru.job4j.foodStorage;

import java.util.List;

/**
 * Created by pacman on 07.06.17.
 * This decorator adds resortable function.
 */
public class ResortableControlQuality extends ControlQuality implements Resortable {

    /**
     * New function for resorting products.
     */
    public void resort() {
        for (Storage storage : this.getAllStorage()) {
            List<Food> list = storage.getProducts();
            Food[] listOfFood = list.toArray(new Food[list.size()]);
            storage.clearStorage();
            for (Food food : listOfFood) {
                this.addProduct(food);
            }
        }
    }
}
