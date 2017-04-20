package ru.job4j.ioservice.networkManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * Класс для отправки и получения файлов в байтовом представлении.
 */
public class FileTransfer {

    /**Отправка указанного файла в батовом представлении.
     * Первой строкой текстового потока отправляется имя и расширение файла,
     * второй - размер в байтах типа long.
     * @param nameOfFile имя отправляемого файла
     * @param out поток для отправки имени и размера файла
     * @param bos поток для отправки самого файла
     * @param file определение текущей директории из которой отправляется файл
     * @throws IOException IOException*/
    public void sendFile(String nameOfFile, PrintWriter out, BufferedOutputStream bos, File file) throws IOException {
        File outFile = new File(file.getPath() + File.separatorChar + nameOfFile);
        out.println(nameOfFile);
        out.println(outFile.length());
        try (RandomAccessFile raf = new RandomAccessFile(outFile, "r")) {
            int lenOfBuffer;
            byte[] bytes = new byte[8192];
            while ((lenOfBuffer =  raf.read(bytes)) != -1) {
                bos.write(bytes, 0, lenOfBuffer);
            }
        }
        bos.flush();
    }

    /**Получение файла от клиента в текущую директорию.
     * @param bis байтовый поток для получения файла
     * @param input поток для получения имени и размера скачиваемого файла
     * @param file определение текущей директории, куда будет скачан файл
     * @throws IOException IOException*/
    public void getFile(BufferedInputStream bis, BufferedReader input, File file) throws IOException {
        File newFile = new File(file.getPath() + File.separatorChar + input.readLine());
        newFile.createNewFile();
        long lengthOfFile = Long.valueOf(input.readLine());
        //try (RandomAccessFile raf = new RandomAccessFile(newFile, "rw")) {
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            byte[] bytes = new byte[8192];
            int lenOfBytes;
            System.out.println(lengthOfFile);
            do {
                lenOfBytes = bis.read(bytes, 0, Math.min((int) lengthOfFile, bytes.length));
                System.out.println("I'm here");
                fos.write(bytes, 0, lenOfBytes);
                lengthOfFile -= lenOfBytes;
            } while (lengthOfFile != 0);
        }
    }
}
