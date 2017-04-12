package ru.job4j.ioservice.networkManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Сервер.
 */
public class NetworkServer {

    /**Сокет.*/
    private final Socket socket;

    /**Определение сокета.
     * @param socket - socket*/
    public NetworkServer(Socket socket) {
        this.socket = socket;
    }

    /**Константа для выхода из  программы.*/
    private static final String EXIT = "exit";

    /**Старт сервера.
     * @param pathToRoot корневая директория сервера
     * @throws IOException IOException*/
    public void startServer(String pathToRoot) throws IOException {
        System.out.println("Connection complete!");

        /**Объекты для работы с байтовыми потоками.*/
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

        /**Объекты для работы с байтовыми потоками.*/
        BufferedReader input = new BufferedReader(new InputStreamReader(bufferedInputStream));
        PrintWriter output = new PrintWriter(bufferedOutputStream, true);

        File file = new File(pathToRoot);
        String option = "";
        do {
            System.out.println("Wait...");

            /**Получение клиентского ввода.*/
            option = input.readLine();
            if ("ls".equals(option)) {
                this.ls(output, file);
            } else if (option.startsWith("cd ..")) {
                file = this.cdUp(output, file);
            } else if (option.startsWith("cd ")) {
                file = this.cdDown(option.replace("cd ", ""), output, file);
            } else if (option.startsWith("download")) {
                this.download(option.replace("download ", ""), output, bufferedOutputStream, file);
            } else if (option.startsWith("upload")) {
                this.upload(bufferedInputStream, input, output, file);
            } else if (!EXIT.equals(option)) {
                output.println("Unknown command: " + option + ". Try again:");
                output.println("");
            }
        } while (!EXIT.equals(option));
        output.println("Server stopped!");
    }

    /**Отображение всех файлов и директорий.
     * Передает список объектов в текущей директории
     * или "Empty directory", если она пустая.
     * @param out поток для передачи списка объектов клиенту
     * @param file текущая директория*/
    private void ls(PrintWriter out, File file) {
        if (file.list() != null) {
            for (String item : file.list()) {
                out.println(item);
            }
        } else {
            out.println("Empty directory!");
        }
        out.println("");
    }

    /**Переход в родительскую директорию при вводе "cd .."
     * и отображение полного пути к ней.
     * @return возвращает ссылку на родительскую директорию
     * @param out поток для передачи пути к директории клиенту
     * @param file текущая директория*/
    private File cdUp(PrintWriter out, File file) {
        file = file.getParentFile();
        out.println(file.getPath());
        out.println("");
        return file;
    }

    /**Переход в указанную директорию. Отправляет клиенту
     * "Not found such directory!", если такой директории нет в текущей.
     * "Current directory is empty!", если текущая директория пуста.
     * Или путь к указанной директории, если переход возможен.
     * @param path наззвание директории в которую следует перейти
     * @param out поток для передачи пути к директории клиенту
     * @param file текущая директория
     * @return возвращает директорию, указанную клиентом*/
    private File cdDown(String path, PrintWriter out, File file) {
        File tmpFile;
        if (file.list() != null) {
            for (String item : file.list()) {
                if (item.equals(path)) {
                    tmpFile = new File(file.getPath() + File.separatorChar + path);
                    if (tmpFile.isDirectory()) {
                        file = tmpFile;
                        out.println(file.getPath());
                        out.println("");
                        break;
                    } else {
                        out.println("Not found such directory!");
                        out.println("");
                        break;
                    }
                }
            }
        } else {
            out.println("Current directory is empty!");
            out.println("");
        }
        return file;
    }

    /**Передача файла клиенту и отправляет клиенту сообщение об успешной отправке.
     * @param nameOfFile имя отправляемого файла
     * @param output поток для отправки имени и размера файла и сообщения об успешно отправленном файле
     * @param bos поток для отправки самого файла
     * @param file определение текущей директории из которой отправляется файл
     * @throws IOException IOException*/
    private void download(String nameOfFile, PrintWriter output, BufferedOutputStream bos, File file) throws IOException {
        new FileTransfer().sendFile(nameOfFile, output, bos, file);
        output.println("Downloading complete!");
        output.println("");
    }

    /**Получение файла от клиента и отправляет клиенту сообщение об успешном получении.
     * @param bis байтовый поток для получения файла
     * @param input поток для получения имени и размера скачиваемого файла
     * @param output поток для отправки сообщения об успешно полученном файле
     * @param file определение текущей директории, куда будет скачан файл
     * @throws IOException IOException*/
    private void upload(BufferedInputStream bis, BufferedReader input, PrintWriter output, File file) throws IOException {
        new FileTransfer().getFile(bis, input, file);
        output.println("Uploading complete!");
        output.println("");
    }

    /**Main.
     * @param args - args
     * @throws IOException IOException*/
    public static void main(String[] args) throws IOException {
        new NetworkServer(new ServerSocket(8000).accept()).startServer("/home/pacman/Tests/");
    }
}
