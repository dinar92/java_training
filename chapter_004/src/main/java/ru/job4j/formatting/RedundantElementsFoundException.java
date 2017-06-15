package ru.job4j.formatting;

import java.util.ArrayList;

/**
 * Created by pacman on 11.06.17.
 * Throws when found redundant elements.
 */
public class RedundantElementsFoundException extends FormattingException {

    /**
     * Sets redundant keys in the exception.
     * @param keys - keys.
     */
    public RedundantElementsFoundException(ArrayList<String> keys) {
        super(keys);
    }

    /**
     * Returns message with redundant keys.
     * @return message.
     */
    @Override
    public String toString() {
        return String.format("Found redundant elements with keys: %s", this.getStringOfList());
    }




}

