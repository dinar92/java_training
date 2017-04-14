package ru.job4j.ioservice.networkManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * Client.
 */
public class NetworkClient {

    /**Сокет.*/
    private final Socket socket;

    /**Определение сокета.
     * @param socket - socket*/

    public NetworkClient(Socket socket) {
        this.socket = socket;
    }

    /**Константа для выхода из программы.*/
    private static final String EXIT = "exit";

    /**Запуск сетевого файлового менеджера.
     * @param  pathToRoot корневая директория клиента
     * @throws  IOException IOException*/
    public void startClient(String pathToRoot) throws IOException {

        /**Объекты для работы с байтовыми потоками.*/
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

        /**Оюъекты для работы с символьными потоками.*/
        BufferedReader input = new BufferedReader(new InputStreamReader(bufferedInputStream));
        PrintWriter output = new PrintWriter(bufferedOutputStream, true);


        File file = new File(pathToRoot);
        Scanner console = new Scanner(System.in);
        String usersInput = "";

        do {
            /**Получение пользовательского ввода.*/
            usersInput = console.nextLine();

            /**Отправка на сервер полученной строки.*/
            output.println(usersInput);

            if (usersInput.startsWith("download")) {
                this.download(bufferedInputStream, input, file);
            } else if (usersInput.startsWith("upload")) {
                this.upload(usersInput.replace("upload ", ""), output, bufferedOutputStream, file);
            }

            /**Отображает входящий симовльный поток пока не получит пустую строку (для возможности отображения множества строк).*/
            String itemToDisplay = input.readLine();
            while (itemToDisplay != null && !itemToDisplay.isEmpty()) {
                System.out.println(itemToDisplay);
                itemToDisplay = input.readLine();
            }
        } while (!EXIT.equals(usersInput));

    }

    /**Скачивание файла.
     * @param bis байтовый поток для получения файла
     * @param input поток для получения имени и размера скачиваемого файла
     * @param file определение текущей директории, куда будет скачан файл
     * @throws IOException IOException*/
    private void download(BufferedInputStream bis, BufferedReader input, File file) throws IOException {
        new FileTransfer().getFile(bis, input, file);
    }

    /**Отправка фаайла на сервер.
     * @param nameOfFile имя отправляемого файла
     * @param output поток для отправки имени и размера файла
     * @param bos поток для отправки самого файла
     * @param file определение текущей директории из которой отправляется файл
     * @throws IOException IOException*/
    private void upload(String nameOfFile, PrintWriter output, BufferedOutputStream bos, File file) throws IOException {
        new FileTransfer().sendFile(nameOfFile, output, bos, file);
        output.print("");
    }

    /**Main.
     * @param args - args
     * @throws IOException IOException*/
    public static void main(String[] args) throws IOException {
        new NetworkClient(new Socket("127.0.0.1", 8000)).startClient("/home/pacman/Tests2");
    }
}
