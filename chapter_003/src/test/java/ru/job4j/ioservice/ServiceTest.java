package ru.job4j.ioservice;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Tests Service.*/
public class ServiceTest {

    /**An object of a service for tests.*/
    Service service = new Service();

    /**Tests isEvenNumber().
     * @throws IOException IOException*/
    @Test
    public void whenIsEvenNumberThenTrue() throws IOException {
        byte[] arr = {2};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(true));
        }
    }

    /**Tests isEvenNumber().
     * @throws IOException IOException*/
    @Test
    public void whenIsNotEvenNumberThenFalse() throws IOException {
        byte[] arr = {1};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(false));
        }
    }

    /**Tests dropAbuse().
     * @throws IOException IOException*/
    @Test
    public void whenInputThenFilteredOutput() throws IOException {
        byte[] inputStream = new String("streamToFilter".toCharArray()).getBytes(StandardCharsets.UTF_8);
        byte[] expectOutStream = new String("stToter".toCharArray()).getBytes(StandardCharsets.UTF_8);
        String[] abuseWords = {"Fil", "ream"};
        try (ByteArrayInputStream input = new ByteArrayInputStream(inputStream); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            service.dropAbuses(input, out, abuseWords);
            for (int i = 0; i < out.toByteArray().length; i++) {
                assertThat(out.toByteArray()[i], is(expectOutStream[i]));
            }
        }
    }
}