package ru.job4j.ioservice;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The class for packing data to Zip archive.
 * @version 1.0
 * @author gimazetdinov
 */
public class ZipPacker implements Packer {

    /**Path of the data, that will be pack.*/
    private String path;

    /**Name of the Zip archive, that will be create.*/
    private String zipName;

    /**Extensions of files, which will be include in the Zip archive.*/
    private String extensions;

    /**Sets parameters of the created archive.
     * @param path of the data
     * @param exts extensions
     * @param zipName name of the archive*/
    public ZipPacker(String path, String exts, String zipName) {
        this.path = path;
        this.extensions = exts;
        this.zipName = zipName;
    }

    /**The method for packing data.
     * @throws IOException IOException*/
    public void pack() throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName))) {
            List<File> store = crawlingFiles(path);
            for (File file : store) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry ze = new ZipEntry(file.getPath());
                    zout.putNextEntry(ze);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }

        }
    }

    /**Crawling all files in specified path.
     * @param path path
     * @return List of collected files*/
    private List<File> crawlingFiles(String path) {
        File dir = new File(path);
        List<File> store = new ArrayList<>();
        Filter filter = new Filter(this.extensions);
        for (File file : dir.listFiles()) {
            if (file.isFile() && filter.accept(file)) {
                store.add(file);
            } else if (file.isDirectory()) {
                store.addAll(crawlingFiles(file.getPath()));
            }
        }
        return store;
    }

    /**The local class of filter by specified files extensions.
     * @author gimazetdinov
     * @version 1.0*/
    class Filter implements FileFilter {

        /**List of extensions.*/
        String[] ext;
        /**Sets files extensions for selection.
         * @param ext extensions*/
        Filter(String ext) {
            this.ext = ext.split(",");
        }

        /**Return extension of file.
         * @param pathname pathname of the file
         * @return extension of the file*/
        private String getExtension(File pathname) {
            String filename = pathname.getPath();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
            return "";
        }

        /**Return true if the files extension
         * is equal to one of the specified extensions.
         * @param pathname pathname
         * @return equal(true) or not equal(false)*/
        public boolean accept(File pathname) {
            if (!pathname.isFile()) {
                return false;
            }
            String extension = getExtension(pathname);
            for (String e : ext) {
                if (e.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
            return false;
        }
    }
}