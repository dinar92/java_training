package ru.job4j.ioservice.networkManager;

import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;

/**
 * Created by pacman on 30.06.17.
 * Tests class FileTransfer.
 */
public class FileTransferTest {

    /**
     * Tests sendFile().
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenSenBigFileThenGetCorrectFile() throws IOException {
        String nameOfFile = "inputForSort.txt";
        File fileToTransfer = new File("/home/pacman/Development/java_training/chapter_003");
        PrintWriter nameSize = new PrintWriter(new ByteArrayOutputStream());
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(content);
        String expectedContent = Files.toString(new File(fileToTransfer + "/" + nameOfFile), Charset.forName("UTF-8"));

        new FileTransfer().sendFile(nameOfFile, nameSize, bos, fileToTransfer);

        Assert.assertThat(content.toString(), is(expectedContent));
    }

    /**
     * Tests getFile().
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenGetFileShouldCreateSendedFile() throws IOException {
        String nameOfFile = "inputForSort.txt";
        File fileToTransfer = new File("/home/pacman/Development/java_training/chapter_003");
        String contentOfFile = Files.toString(new File(fileToTransfer + "/" + nameOfFile), Charset.forName("UTF-8"));
        Integer sizeOfFile = new Integer(contentOfFile.length());
        BufferedInputStream inputStreamContent = new BufferedInputStream(new ByteArrayInputStream(contentOfFile.getBytes()));
        String nameAndSize = "GetFile.txt\n" + sizeOfFile + "\n";
        BufferedReader readerNameAndSize = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(nameAndSize.getBytes())));
        File path = new File("/home/pacman/Development/java_training/chapter_003");
        FileTransfer transfer = new FileTransfer();

        transfer.getFile(inputStreamContent, readerNameAndSize, path);
        String expectFile = Files.toString(new File(fileToTransfer + "/GetFile.txt"), Charset.forName("UTF-8"));

        Assert.assertThat(expectFile, is(contentOfFile));

    }

    /**
     * Tests getFile(). The file's size.
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenGetFileShouldHasCorrectSize() throws IOException {
        String contentOfFile = "Another test getting";
        Integer sizeOfFile = new Integer(contentOfFile.length());
        BufferedInputStream inputStreamContent = new BufferedInputStream(new ByteArrayInputStream(contentOfFile.getBytes()));
        String nameAndSize = "GetFile.txt\n" + sizeOfFile + "\n";
        BufferedReader readerNameAndSize = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(nameAndSize.getBytes())));
        File path = new File("/home/pacman/Development/java_training/chapter_003/src/test/java/ru/job4j/ioservice/networkManager");
        FileTransfer transfer = new FileTransfer();

        transfer.getFile(inputStreamContent, readerNameAndSize, path);

        long expectedSizeOfFile = 0;
        File expectedFile = new File("/home/pacman/Development/java_training/chapter_003/src/test/java/ru/job4j/ioservice/networkManager/GetFile.txt");
        try (RandomAccessFile accessFile = new RandomAccessFile(expectedFile, "r")) {
            expectedSizeOfFile = accessFile.length();
        }

        Assert.assertThat(sizeOfFile.longValue(), is(expectedSizeOfFile));
    }
}
