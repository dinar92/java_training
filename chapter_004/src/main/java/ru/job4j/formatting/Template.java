package ru.job4j.formatting;

import java.util.Map;

/**
 * Created by pacman on 11.06.17.
 * Inserts arguments into a text template.
 */
public interface Template {

    /**
     * Generates a new string using the template and arguments.
     *
     * @param template - template with placeholders in format ${key}.
     * @param args     - specified args in Map<key, value>.
     * @return the result string.
     * @throws KeyNotFoundException - key not contains in Map.
     * @throws RedundantElementsFoundException - Map contains unused elements.
     * @throws InvalidArgumentException - one or more args equals to null.
     */
    String generate(String template, Map<String, String> args) throws KeyNotFoundException, RedundantElementsFoundException, InvalidArgumentException;
}
