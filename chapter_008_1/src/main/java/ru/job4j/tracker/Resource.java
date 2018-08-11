package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Application resources.
 */
public class Resource {

    /**
     * Returns content of specified resource file.
     *
     * @param fileName - the name of resource.
     * @return - content of resource.
     * @throws IOException -   If an I/O error occurs.
     */
    public String content(String fileName) throws IOException {
        StringBuilder script = new StringBuilder();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            script.append(line);
        }
        return script.toString();
    }
}
