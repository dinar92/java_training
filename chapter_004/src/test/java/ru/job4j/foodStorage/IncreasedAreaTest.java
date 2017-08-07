package ru.job4j.foodStorage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pacman on 05.07.17.
 * Tests the class IncreasedArea.
 */
public class IncreasedAreaTest {

    /**
     * Tests addProduct().
     */
    @Test
    public void whenStorageIsFullShouldOnIncreasedArea() {
        Food food = new Food("potato", 0.5);
        Warehouse storage = mock(Warehouse.class);
        when(storage.addProduct(food)).thenReturn(false);
        boolean isInStorage = true;

        IncreasedArea increasedWarehouse = new IncreasedArea(storage);
        increasedWarehouse.addProduct(food);
        assertThat(increasedWarehouse.storage.contains(food), is(isInStorage));
    }

    /**
     * Tests addProduct().
     */
    @Test
    public void whenSetStorageShouldContainsIt() {
        Warehouse warehouse = new Warehouse();
        IncreasedArea increasedArea = new IncreasedArea(warehouse);
        Food food = new Food("potato", 0.5);
        boolean isInStorage = true;

        increasedArea.addProduct(food);

        assertThat(increasedArea.getProducts().contains(food), is(isInStorage));

    }
}
