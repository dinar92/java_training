package ru.job4j.ioservice;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by pacman on 03.07.17.
 */
public class PackTest {

    /**
     * Tests main().
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenSetArgsInMainThenGetSpecifiedArchive() throws IOException {
        String pathWithFiles = "/home/pacman/Development/java_training/chapter_003/src/test/java/ru/job4j/ioservice";
        String extensions = "txt,pdf";
        String archiveName = "ResultPack.zip";
        String fileTxt1 = pathWithFiles + "/networkManager/SendFile.txt";
        String fileTxt2 = pathWithFiles + "/networkManager/GetFile.txt";
        ArrayList<String> filesInList = new ArrayList<>();
        filesInList.add(fileTxt1);
        filesInList.add(fileTxt2);

        String resultArchivePath = "/home/pacman/Development/java_training/chapter_003/ResultPack.zip";
        String[] clargs = {"-d", pathWithFiles, "-o", archiveName, "-e", extensions};

        Pack.main(clargs);

        File archive = new File(resultArchivePath);
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(archive))) {
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                Assert.assertThat(ze.getName(), Matchers.isIn(filesInList));
                ze = zis.getNextEntry();
            }
        }
    }
}
