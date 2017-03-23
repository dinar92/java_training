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

    /**The method for sorting large text files by length of strings.
     * Is intended for max size of RAM 512Mb.
     * Creates new file "destination.data"
     * @param source - a source file
     * @throws IOException IOException
     * */
    public void textFileSort(File source) throws IOException {
        //Хранилище для подмассивов.
        List<List> tmpArrays = new ArrayList<>();
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r")) {

            //Разбиваем файл на подмассивы строк по 500Мб.
            while (sourceFile.getFilePointer() < sourceFile.length() - 1) {
                List<String> tmpArray = new ArrayList<>();
                int lineBytesCounter = 0;
                while (lineBytesCounter  < 500000000 && sourceFile.getFilePointer() < sourceFile.length() - 1) {
                    String tmpLine = sourceFile.readLine();
                    lineBytesCounter += tmpLine.getBytes().length;
                    tmpArray.add(tmpLine + "\n");
                }

                //Сортировка подмассива методом вставки в порядке возрастания.
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
                tmpArrays.add(tmpArray);
            }
        }
        //Создаем массив для хранения всех строк и вставляем первый подмассив.
        List<String> sortedArr = new ArrayList<>();
        if (sortedArr.size() == 0) {
            for (int i = 0; i < tmpArrays.get(0).size(); i++) {
                sortedArr.add((String) tmpArrays.get(0).get(i));
            }
        }

        //Слияние упорядоченных подмассивов.
        for (int out = 1; out < tmpArrays.size(); out++) {
            int i = 0, j = 0;
            while (i < sortedArr.size() && j < tmpArrays.get(out).size()) {
                if (sortedArr.get(i).compareTo((String) tmpArrays.get(out).get(j)) > 0) {
                    sortedArr.add(i, (String) tmpArrays.get(out).get(j));
                    j++;
                } else {
                    i++;
                }
            }
            //Вставляем остатки от вставляемого подмассива в конец отсортированного,
            //если закончился раньше.
            if (i == sortedArr.size()) {
                while (j < tmpArrays.get(out).size()) {
                    sortedArr.add(i, (String) tmpArrays.get(out).get(j++));
                }
            }
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