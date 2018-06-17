package sqlru.core;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResourceTest {

    @Test
    public void whenInvokeContentThenGetsContentOfResource() throws IOException {
        String resourceFile = "config.properties";
        Resource resource = new Resource();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/config.properties"));
        StringBuilder expectContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            expectContent.append(line);
        }
        assertThat(resource.content(resourceFile), is(expectContent.toString()));
    }
}