package ru.job4j.foodStorage;

import jdk.nashorn.internal.runtime.StoredScript;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by pacman on 25.04.17.
 * Tests class ControlQuality.
 */
public class ControlQualityTest {

    /**
     * Tests addStorage().
     */
    @Test
    public void whenAddStorageThenSuccess() {
        assertThat(new ControlQuality().addStorage(new Trash()), is(true));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenAddProductThenGetSuccess() {
        ControlQuality controlQuality = new ControlQuality();
        int yearOfCreate = 2017, yearOfExpiry = 2017;
        int monthOfCreate = 2, monthOfExpiry = 3;
        int dayOfCreate = 23, dayOfExpiry = 30;

        Food food = new Food("Meat", 6.0);
        food.setCreateDate(yearOfCreate, monthOfCreate, dayOfCreate);
        food.setExpiryDate(yearOfExpiry, monthOfExpiry, dayOfExpiry);
        controlQuality.addStorage(new Trash());

        assertThat(controlQuality.addProduct(food), is(true));
    }

    /**
     * Tests getAllStorage().
     */
    @Test
    public void whenGetAllStorageThenReturnList() {
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        ControlQuality controlQuality = new ControlQuality();
        List<Storage> containerOfStorage = new ArrayList<>();

        containerOfStorage.add(trash);
        containerOfStorage.add(warehouse);
        controlQuality.addStorage(trash);
        controlQuality.addStorage(warehouse);

        assertThat(controlQuality.getAllStorage(), is(containerOfStorage));
    }
}