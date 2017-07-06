package ru.job4j.ioservice;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by pacman on 03.07.17.
 * Tests ZipPacker.
 */
public class ZipPackerTest {

    /**
     * Tests pack().
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenSetsFilesParamShouldGetZipArchive() throws IOException {
        String pathWithFiles = "/home/pacman/Development/java_training/chapter_003/src/test/java/ru/job4j/ioservice";
        String extensions = "txt,pdf";
        String archiveName = "Result.zip";
        String fileTxt1 = pathWithFiles + "/networkManager/SendFile.txt";
        String fileTxt2 = pathWithFiles + "/networkManager/GetFile.txt";
        ArrayList<String> filesInList = new ArrayList<>();
        filesInList.add(fileTxt1);
        filesInList.add(fileTxt2);

        String resultArchivePath = "/home/pacman/Development/java_training/chapter_003/Result.zip";

        new ZipPacker(pathWithFiles, extensions, archiveName).pack();

        File archive = new File(resultArchivePath);
        try (ZipInputStream inputStream = new ZipInputStream(new FileInputStream(archive))) {
            ZipEntry zipEntry = inputStream.getNextEntry();
            while (zipEntry != null) {
                Assert.assertThat(zipEntry.getName(), Matchers.isIn(filesInList));
                zipEntry = inputStream.getNextEntry();
            }
        }
    }

    /**
     * Tests the file filter: accept().
     */
    @Test
    public void whenSetExtensionThenReturnFilteredResult() {
        String fileForFilter = "/home/pacman/Development/java_training/chapter_003/Result.zip";
        String pathToFile = "/home/pacman/Development/java_training/chapter_003";
        File file = new File(fileForFilter);
        String extensions = "zip";
        boolean expectedFilter = true;

        ZipPacker.Filter filter = new ZipPacker(pathToFile, extensions, "Some.zip").new Filter(extensions);

        assertThat(filter.accept(file), is(expectedFilter));
    }
}
