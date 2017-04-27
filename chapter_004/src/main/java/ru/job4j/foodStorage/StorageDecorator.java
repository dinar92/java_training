package ru.job4j.foodStorage;

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
}
