package ru.job4j.menu;


import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class KeyGeneratorForMenuItems().
 */
public class KeyGeneratorForMenuItemsTest {

    /**
     * Tests getNextKey().
     */
    @Test
    public void whenNextKeyThenReturnNextKey() {
        int initialValue = 1;
        assertThat(new KeyGeneratorForMenuItems().getNextKey(), is(initialValue));
    }

    /**
     * Tests reset().
     */
    @Test
    public void whenGeneratorResetThenCounterResetsTiInitial() {
        KeyGenerator keyGenerator = new KeyGeneratorForMenuItems();
        int initialValue = 1;

        for (int i = 0; i < 100; i++) {
            keyGenerator.getNextKey();
        }

        keyGenerator.reset();
        assertThat(keyGenerator.getNextKey(), is(initialValue));
    }
}
