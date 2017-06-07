package ru.job4j.foodStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman on 21.04.17.
 * Conducts quality control.
 */
public class ControlQuality implements ControlQualityInterface {

    /**
     * The product storage.
     */
    private List<Storage> productStorage = new ArrayList<>();

    /**
     * Amount of discount for products,
     * the expiration date of which is close to the end.
     */
    private final double amountOfDiscount = 10.00;

    /**
     * Adds new storage.
     * @param storage storage.
     * @return success of adding.
     */
    public boolean addStorage(Storage storage) {
        return this.productStorage.add(storage);
    }

    /**
     * Distribution food by repositories depending on the degree of deterioration.
     * Sets a discount on almost overdue products.
     * @param food a product.
     * @return success of adding.
     */
    private boolean storageDistribution(Food food) {
        boolean wasAdded = false;
        for (Storage storage : this.productStorage) {
            if (storage.conformityTest(food)) {
                if (this.isDiscount(food)) {
                    food.setDiscount(amountOfDiscount);
                }
                wasAdded = storage.addProduct(food);
            }
        }
        return wasAdded;
    }

    /**
     * Checks for the need to set a discount.
     * @param food product
     * @return success of setting.
     */
    private boolean isDiscount(Food food) {
        return (75 < food.degreeOfDeterioration()) && (food.degreeOfDeterioration() < 100);
    }

    /**
     * Adds product for distribution.
     * @param food product.
     * @return success of adding.
     */
    public boolean addProduct(Food food) {
       return this.storageDistribution(food);
    }

    /**
     * Returns list of all exist storage.
     * @return storage.
     */
    public List<Storage> getAllStorage() {
        return this.productStorage;
    }
}
