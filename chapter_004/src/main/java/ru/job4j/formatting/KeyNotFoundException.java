package ru.job4j.formatting;

import java.util.ArrayList;

/**
 * Created by pacman on 11.06.17.
 * Throws when there are no necessary keys.
 */
public class KeyNotFoundException extends FormattingException {

    /**
     * Sets the necessary keys to exception.
     * @param keys - keys.
     */
    public KeyNotFoundException(ArrayList<String> keys) {
        super(keys);
    }

    /**
     * Returns the message of the exception.
     * @return message.
     */
    @Override
    public String toString() {
        return String.format("The next values not found among args: %s", this.getStringOfList());
    }

}
