package sqlru.core;

import java.io.*;

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
