package ru.job4j.collectionsLite.collectionsFramework;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pacman on 09.08.17.
 * Measures the speed of performing insert and delete functions in collections.
 */
public class SpeedTester {

    /**
     * Measures the adding speed to collection a specified count of Strings.
     * @param collection the collection.
     * @param amount the count of strings.
     * @return run time in milliseconds.
     */
    public long add(Collection<String> collection, int amount) {
        ArrayList<String> data = new ArrayList<>(amount);

        for (int i = 0; i < amount; i++) {
            data.add(String.valueOf(i));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(data.get(i));
        }
        return System.currentTimeMillis() - start;
    }


    /**
     * Measures the removing speed from collection a specified count of Strings.
     * @param collection the collection.
     * @param amount the count of strings.
     * @return run time in milliseconds.
     */
    public long delete(Collection<String> collection, int amount) {
        ArrayList<String> data = new ArrayList<>(amount);

        //Creating an inverted array of strings for more complex search.
        for (int i = 0; i < amount; i++) {
//            data.add(String.valueOf((amount - i) - 1));
            data.add(String.valueOf(amount));
        }
        //Adding strings in the normal order.
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf(i));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < amount; i++) {
            collection.remove(data.get(i));
        }
        return System.currentTimeMillis() - start;
    }
}
