package ru.job4j.ioservice;

import java.io.*;
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
        try (BufferedReader charInBuffRead = new BufferedReader(new InputStreamReader(charIn));
        BufferedWriter charOutBuffWriter = new BufferedWriter(new OutputStreamWriter(charOut))) {
            String str = charInBuffRead.readLine();
            for (String substr : abuse) {
                if (str.contains(substr)) {
                    str = str.replace(substr, "");
                }
            }
            charOutBuffWriter.write(str);
        }
    }

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