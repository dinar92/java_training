package ru.job4j.ioservice;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Service.
 */
public class ServiceTest {

    /**
     * An object of a service for tests.
     */
    Service service = new Service();

    /**
     * Tests isEvenNumber().
     *
     * @throws IOException IOException
     */
    @Test
    public void whenIsEvenNumberThenTrue() throws IOException {
        byte[] arr = {2};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(true));
        }
    }

    /**
     * Tests isEvenNumber().
     *
     * @throws IOException IOException
     */
    @Test
    public void whenIsNotEvenNumberThenFalse() throws IOException {
        byte[] arr = {1};
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(arr)) {
            assertThat(service.isEvenNumber(inputStream), is(false));
        }
    }

    /**
     * Tests dropAbuse().
     *
     * @throws IOException IOException
     */
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

    /**
     * Tests textFileSort().
     *
     * @throws IOException IOException
     */
    @Test
    public void whenTextFileSortThenNewSortedFile() throws IOException {
        StringBuilder forSort = new StringBuilder().append("Her\n")
                .append("name\n")
                .append("is\n")
                .append("Alice\n")
                .append("!\n");
        StringBuilder expect = new StringBuilder().append("!\n")
                .append("is\n")
                .append("Her\n")
                .append("name\n")
                .append("Alice\n");
        File fileForSort = new File("forSort.data");
        fileForSort.createNewFile();
        File expectFile = new File("expect.data");
        expectFile.createNewFile();
        try (RandomAccessFile raf = new RandomAccessFile(fileForSort, "rw"); RandomAccessFile exp = new RandomAccessFile(expectFile, "rw")) {
            raf.writeChars(forSort.toString());
            exp.writeChars(expect.toString());
        }
        File sorted = new File("destination.data");
        service.textFileSort(fileForSort);
        try (RandomAccessFile rafSorted = new RandomAccessFile(sorted, "r"); RandomAccessFile rafExp = new RandomAccessFile(expectFile, "r")) {
            while (rafExp.getFilePointer() < expect.length() - 1) {
                assertThat(rafSorted.readLine(), is(rafExp.readLine()));
            }
        }
    }

    /**
     * Tests big file textFileSort().
     *
     * @throws IOException IOException
     */
    @Test
    public void whenBigTextFileSortThenNewSortedFile() throws IOException {
        File fileForSort = new File("inputForSort.txt");

        service.textFileSort(fileForSort);

        try (RandomAccessFile expect = new RandomAccessFile(new File("expectedSort.data"), "r"); RandomAccessFile result = new RandomAccessFile(new File("destination.data"), "r")) {
            while ((expect.getFilePointer() != expect.length()) && (result.getFilePointer() != result.length())) {
                assertThat(result.readLine(), is(expect.readLine()));
            }
        }
    }
}