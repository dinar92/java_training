package ru.job4j.ioservice;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**Tests Service.*/
public class ServiceTest {

    /**Tests isEvenNumber().
     * @throws IOException IOException*/
    @Test
    public void whenIsEvenNumberThenTrue() throws IOException {
        Service service = new Service();
        byte[] arr = {2};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(true));
        }
    }

    /**Tests isEvenNumber().
     * @throws IOException IOException*/
    @Test
    public void whenIsNotEvenNumberThenTrue() throws IOException {
        Service service = new Service();
        byte[] arr = {1};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(false));
        }
    }
}