package ru.job4j.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The engine for search a text in files from the specified root directory.
 */
@ThreadSafe
public class ParallelSearch {
    /**
     * Text to be found.
     */
    private final String text;
    /**
     * Start directory's path for search.
     */
    private final File root;
    /**
     * Extensions of files to be scan.
     * Must be specified without '.'.
     */
    private final List<String> exts;
    /**
     * Synchronized ArrayList collection.
     */
    @GuardedBy("SynchronizedCollection.mutex") private final List<String> result = Collections.synchronizedList(new ArrayList<>());
    /**
     * Filter for nodes.
     * Returns true for directories and files with specified {@code exts}.
     */
    private final FileFilter filter = new FileFilterForSearch();

    /**
     * Sets search parameters.
     * @param text - Text to be found.
     * @param root - Start directory for search.
     * @param exts - Extensions of files to be scan.
     */
    public ParallelSearch(String text, String root, List<String> exts) {
        this.text = text;
        this.root = new File(root);
        this.exts = exts;
    }

    /**
     * Starts the main thread of search.
     * @return - the List with full path of found files.
     */
    public List<String> search() {
        Thread startThread = new Thread(new SearchThread(root));
        startThread.start();
        try {
            startThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks the files in current directory with specified extensions.
     * If found file that contains the specified text, adds this file's path to {@code result}.
     * If found directory - starts new self thread in this directory.
     */
    private class SearchThread implements Runnable {

        /**
         * Files that passed through the filter {@link FileFilterForSearch}.
         */
        private final File[] filesInCurrent;
        /**
         * The list of active threads in inner directories.
         */
        private final List<Thread> listOfActiveThreads = new ArrayList<>();

        /**
         * Sets the current directory for scan.
         * @param currentDirectory - the current directory.
         */
        private SearchThread(File currentDirectory) {
            filesInCurrent = currentDirectory.listFiles(filter);
        }

        /**
         * Activates new search threads in directories and
         * activates content scanning in files.
         */
        @Override
        public void run() {
            for (File file : filesInCurrent) {
                if (file.isDirectory()) {
                    Thread newThread = new Thread(new SearchThread(file));
                    listOfActiveThreads.add(newThread);
                    newThread.start();
                } else {
                    try {
                        if (this.containsText(file)) {
                            result.add(file.getAbsolutePath());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (Thread activeThread : listOfActiveThreads) {
                try {
                    activeThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Checks for availability text in the specified file.
         * @param file - the file.
         * @return - true - if contains, false - otherwise.
         */
        private boolean containsText(File file) {
            StringBuilder builder = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return builder.toString().contains(text);
        }
    }

    /**
     * The filter accepts files with specified extensions and directories.
     */
    private class FileFilterForSearch implements FileFilter {

        /**
         * Return true - if file has a specified extension or if file is directory.
         * @param file - the file for checking.
         * @return - true - if file has specified extension or file is directory.
         */
        @Override
        public boolean accept(File file) {
            boolean result = false;
            if (file.isDirectory()) {
                result = true;
            } else {
                String fileName = file.getAbsolutePath().toLowerCase();
                for (String extension : exts) {
                    if (fileName.endsWith(extension) && fileName.charAt(fileName.length() - extension.length() - 1) == '.') {
                        result = true;
                        break;
                    }
                }
            }
            return result;
        }
    }
}
