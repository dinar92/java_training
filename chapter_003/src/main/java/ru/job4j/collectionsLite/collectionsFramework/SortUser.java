package ru.job4j.collectionsLite.collectionsFramework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by pacman on 27.08.17.
 * Sorts users by age.
 */
public class SortUser {

    /**
     * Returns the sorted by age set from the unsorted list.
     * @param list List of comparable users.
     * @return sorted set.
     */
    public Set<UserComparable> sort(List<UserComparable> list) {
        Set<UserComparable> set = new TreeSet<>();
        set.addAll(list);
        return set;
    }

    /**
     * Returns the sorted List by the name length.
     * @param list list for sorting.
     * @return the sorted List.
     */
    public List<UserComparable> sortByNameLength(List<UserComparable> list) {
        ArrayList<UserComparable> sorted = new ArrayList<>(list);
        sorted.sort(new Comparator<UserComparable>() {
            @Override
            public int compare(UserComparable o1, UserComparable o2) {
                return Integer.compare(o1.name.length(), o2.name.length());
            }
        });
        return sorted;
    }

    /**
     * Returns the sorted List by name, then by age.
     * @param list list for sorting.
     * @return the sorted List.
     */
    public List<UserComparable> sortByAllFields(List<UserComparable> list) {
        List<UserComparable> sorted = new ArrayList<>(list);
        sorted.sort(new ByNameCompare().thenComparing(new ByAgeCompare()));
        return sorted;
    }
}
