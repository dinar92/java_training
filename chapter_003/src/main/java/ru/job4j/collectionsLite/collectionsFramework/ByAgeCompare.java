package ru.job4j.collectionsLite.collectionsFramework;

import java.util.Comparator;

/**
 * Created by pacman on 29.08.17.
 * Compares by age.
 */
public class ByAgeCompare implements Comparator<UserComparable> {
    /**
     * Compares its two UserComparable's age. Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     *                              being compared by this comparator.
     */
    @Override
    public int compare(UserComparable o1, UserComparable o2) {
        if (o1 != null || o2 != null) {
            return o1.age.compareTo(o2.age);
        }
        throw new NullPointerException();
    }
}

