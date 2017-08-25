package ru.job4j.collectionsLite.collectionsFramework;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pacman on 23.08.17.
 * Converts users.
 */
public class UserConvert {

    /**
     * Generates HashMap when key - ID, value - User from the list of users.
     * @param list the list of users.
     * @return the map.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.id, user);
        }
        return map;
    }
}
