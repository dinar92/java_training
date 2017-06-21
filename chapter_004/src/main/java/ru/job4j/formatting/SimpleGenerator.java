package ru.job4j.formatting;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pacman on 11.06.17.
 * Inserts arguments into a text template.
 */
public class SimpleGenerator implements Template {

    /**
     * The regular expression in compiled Pattern.
     */
    private final Pattern pattern = Pattern.compile("\\$\\{([\\w]+)\\}");
    /**
     * Generates a new string using the template and arguments.
     *
     * @param template - template with placeholders in format ${key}.
     * @param args     - specified args in Map<key, value>.
     * @return the result string.
     * @throws KeyNotFoundException            - key not contains in Map.
     * @throws RedundantElementsFoundException - Map contains unused elements.
     * @throws InvalidArgumentException        - one or more args equals to null.
     */
    @Override
    public String generate(String template, Map<String, String> args) throws KeyNotFoundException, RedundantElementsFoundException, InvalidArgumentException {
        if ((template == null) || (args == null)) {
            throw new InvalidArgumentException();
        } else {
            ArrayList<String> redundantArgs = new ArrayList<>();
            ArrayList<String> unsupportedKeys;
            String regex = "";

            for (String key : args.keySet()) {
                String tmp = String.format("${%s}", key);
                if (template.contains(tmp)) {
                    regex = String.format("\\$\\{[%s]+\\}", key);
                    String value = args.get(key);
                    template = template.replaceAll(regex, value);
                    args.remove(key);
                } else {
                    redundantArgs.add(key);
                }
            }
            unsupportedKeys = this.checkOfUnSupportKeys(template);
            if (unsupportedKeys.size() > 0) {
                throw new KeyNotFoundException(unsupportedKeys);
            } else if (redundantArgs.size() > 0) {
                throw new RedundantElementsFoundException(redundantArgs);
            }
        }
        return template;
    }

    /**
     * Checks template of string for the presence of the regular expression.
     * @param template - template of the string.
     * @return ArrayList of concurrences.
     */
    private ArrayList<String> checkOfUnSupportKeys(String template) {
        ArrayList<String> concurrences = new ArrayList<>();
        Matcher matcher = this.pattern.matcher(template);

        while (matcher.find()) {
            concurrences.add(matcher.group(1));
        }
        return concurrences;
    }
}
