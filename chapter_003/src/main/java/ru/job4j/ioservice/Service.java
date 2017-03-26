package ru.job4j.ioservice;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * IO handling.
 */
public class Service {

    /**
     * Parity checks InputStream's numbers.
     *
     * @param in - input stream
     * @return number is even
     * @throws IOException IOException
     */
    public boolean isEvenNumber(InputStream in) throws IOException {
        return in.read() % 2 != 1;
    }

    /**
     * Filters out forbidden words.
     *
     * @param charIn  - input stream
     * @param charOut - output stream
     * @param abuse   - forbidden words
     * @throws IOException IOException
     */
    public void dropAbuses(InputStream charIn, OutputStream charOut, String[] abuse) throws IOException {
        int currentChar = charIn.read();
        int longestWordsLength = 0;

        for (String word : abuse) {
            if (word.length() > longestWordsLength) {
                longestWordsLength = word.length();
            }
        }

        int[][] queueCollector = new int[abuse.length + 1][longestWordsLength];

        while (currentChar != -1) {
            boolean mustOut = true;
            for (int wordCounter = 0; wordCounter < abuse.length; wordCounter++) {
                System.out.println(queueCollector[abuse.length][wordCounter]);
                if (currentChar == abuse[wordCounter].charAt(queueCollector[abuse.length][wordCounter])) {
                    queueCollector[wordCounter][queueCollector[abuse.length][wordCounter]] = currentChar;
                    queueCollector[abuse.length][wordCounter]++;
                    mustOut = false;
                } else {
                    queueCollector[abuse.length][wordCounter] = 0;
                }
                if (queueCollector[abuse.length][wordCounter] == abuse[wordCounter].length()) {
                    queueCollector[abuse.length][wordCounter] = 0;
                }
            }
            if (mustOut) {
                charOut.write(currentChar);
            }
            currentChar = charIn.read();
        }
    }

//    public void sort(File source, File destination) throws IOException {
//        int filesCounter = 0;
//        int amountLines = 0;
//        try (RandomAccessFile file = new RandomAccessFile(source, "r")) {
//            while (file.getFilePointer() < file.length()) {
//                int lineCounter = 0;
//                new File(String.format("%s.data", filesCounter));
//                try (RandomAccessFile tempFile = new RandomAccessFile(String.format("%s.data", filesCounter), "rw")) {
//                    while (tempFile.length() < 512000000 || tempFile.getFilePointer() != -1) {
//                        tempFile.writeUTF(file.readLine().ge);
//                        lineCounter++;
//                    }
//                    amountLines += lineCounter;
//                    String[] arrOfStrings = new String[lineCounter];
//                    tempFile.seek(0L);
//                    for (int counter = 0; counter < arrOfStrings.length; counter++) {
//                        arrOfStrings[counter] = tempFile.readLine();
//                    }
//                    for (int outer = 0; outer < arrOfStrings.length; outer++) {
//                        for (int inner = 0; inner < arrOfStrings.length - outer - 1; inner++) {
//                            if (arrOfStrings[inner].length() > arrOfStrings[inner + 1].length()) {
//                                String tmp = arrOfStrings[inner + 1];
//                                arrOfStrings[inner + 1] = arrOfStrings[inner];
//                                arrOfStrings[inner] = tmp;
//                            }
//                        }
//                    }
//                    tempFile.seek(0L);
//                    for (int counter = 0; counter < arrOfStrings.length; counter++) {
//                        tempFile.writeUTF(arrOfStrings[counter]);
//                    }
//                }
//                filesCounter++;
//            }
//        }
//        String[] destArr = new String[amountLines];
//        for (int i = 0; i < filesCounter - 1; i++) {
//
//        }
//        source = new File("destination.data", "rw");
//
//
//    }

    /**
     * The method for sorting large text files by length of strings.
     * Is intended for max size of RAM 512Mb.
     * Creates new file "destination.data"
     *
     * @param source - a source file
     * @throws IOException IOException
     */
    public void textFileSort(File source) throws IOException {
        //Хранилище для разбитых файлов.
        List<File> tmpArrays = new ArrayList<>();
        int filesCounter = 0;
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r")) {

            //Разбиваем файл на файлы с подмассивами строк по 500Мб.
            while (sourceFile.getFilePointer() < sourceFile.length() - 1) {
                File tmpFile = new File(String.format("%s.data", filesCounter++));
                tmpFile.createNewFile();
                int lineBytesCounter = 0;
                try (RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw")) {
                    while (lineBytesCounter < 500000000 && sourceFile.getFilePointer() < sourceFile.length() - 1) {
                        String tmpLine = sourceFile.readLine();
                        lineBytesCounter += tmpLine.getBytes().length;
                        raf.writeBytes(tmpLine + "\n");
                    }
                }
                tmpArrays.add(tmpFile);
            }
        }

        //Сортировка каждого файла методом вставки в порядке возрастания и его перезапись.
        for (File tmpFile : tmpArrays) {
            try (RandomAccessFile raf = new RandomAccessFile(tmpFile, "rw")) {
                List<String> tmpArray = new ArrayList<>();
                while (raf.getFilePointer() < raf.length() - 1) {
                    tmpArray.add(raf.readLine());
                }
                tmpFile.delete();
                int out, in;
                for (out = 1; out < tmpArray.size(); out++) {
                    String tmp = tmpArray.get(out);
                    in = out;
                    while (in > 0 && tmpArray.get(in - 1).length() > tmp.length()) {
                        tmpArray.set(in, tmpArray.get(in - 1));
                        in--;
                    }
                    tmpArray.set(in, tmp);
                }
                tmpFile.createNewFile();
                for (String str : tmpArray) {
                    raf.writeBytes(str + "\n");
                }
            }
        }


        //Создаем массив для хранения всех строк и вставляем первый файл и удаляем его.
        List<String> sortedArr = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(tmpArrays.get(0), "r")) {
            while (raf.getFilePointer() < raf.length() - 1) {
                sortedArr.add(raf.readLine());
            }
        } finally {
            tmpArrays.get(0).delete();
        }


        //Слияние упорядоченных подмассивов и удаление временных файлов.
        for (int fileCounter = 1; fileCounter < tmpArrays.size(); fileCounter++) {
            try (RandomAccessFile raf = new RandomAccessFile(tmpArrays.get(fileCounter), "r")) {
                List<String> tmpArray = new ArrayList<>();
                while (raf.getFilePointer() < raf.length() - 1) {
                    tmpArray.add(raf.readLine());
                }
                int i = 0, j = 0;
                while (i < sortedArr.size() && j < tmpArray.size()) {
                    if (sortedArr.get(i).compareTo(tmpArray.get(j)) > 0) {
                        sortedArr.add(i, tmpArray.get(j));
                        j++;
                    } else {
                        i++;
                    }
                }
                if (i == sortedArr.size()) {
                    while (j < tmpArray.size()) {
                        sortedArr.add(i, tmpArray.get(j++));
                    }
                }
            }
            tmpArrays.get(fileCounter).delete();
        }

        //Создание файла и запись строк.
        File destination = new File("destination.data");
        destination.createNewFile();
        try (RandomAccessFile raf = new RandomAccessFile(destination, "rw")) {
            for (String str : sortedArr) {
                raf.writeBytes(str);
            }
        }
    }
}