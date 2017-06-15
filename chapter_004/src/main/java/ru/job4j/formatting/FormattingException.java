package ru.job4j.formatting;

import java.util.ArrayList;

/**
 * Created by pacman on 11.06.17.
 * Main class for all exceptions in package.
 */
public class FormattingException extends Exception {

    /**
     * List of keys.
     */
    private ArrayList<String> keys;

    /**
     * Contains keys to ArrayList.
     * @param keys - keys.
     */
    public FormattingException(ArrayList<String> keys) {
        this.keys = keys;
    }

    /**
     * Converts the list to the string format with commas between keys and dot
     * at the end.
     * @return the converted string.
     */
    protected String getStringOfList() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < this.keys.size(); i++) {
            msg.append(this.keys.get(i));
            if (i < (this.keys.size() - 1)) {
                msg.append(", ");
            } else {
                msg.append('.');
            }
        }
        return msg.toString();
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return  the detail message string of this.
     */
    public String getMessage() {
        return this.toString();
    }
}
