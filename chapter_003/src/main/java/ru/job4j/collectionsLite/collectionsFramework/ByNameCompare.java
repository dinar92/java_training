package ru.job4j.collectionsLite.collectionsFramework;

import java.util.Comparator;

/**
 * Created by pacman on 28.08.17.
 * Compares by name.
 */
public class ByNameCompare implements Comparator<UserComparable> {


    /**
     * Compares its two arguments of UserComparable's name. Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
                                  being compared by this comparator.
     */
    @Override
    public int compare(UserComparable o1, UserComparable o2) {
        if (o1 != null || o2 != null) {
            return o1.name.compareTo(o2.name);
        }
        throw new NullPointerException();
    }
}
