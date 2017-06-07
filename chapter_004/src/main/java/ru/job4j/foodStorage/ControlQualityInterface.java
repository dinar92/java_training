package ru.job4j.foodStorage;

import java.util.List;

/**
 * Created by pacman on 07.06.17.
 * The interface for the quality controller.
 */
public interface ControlQualityInterface {

    /**
     * Adds product for distribution.
     * @param food product.
     * @return success of adding.
     */
    boolean addProduct(Food food);

    /**
     * Adds new storage.
     * @param storage storage.
     * @return success of adding.
     */
    boolean addStorage(Storage storage);

    /**
     * Returns list of all exist storage.
     * @return storage.
     */
    List<Storage> getAllStorage();
}
