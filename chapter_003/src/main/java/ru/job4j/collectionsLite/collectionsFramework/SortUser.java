package ru.job4j.collectionsLite.collectionsFramework;

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
}
