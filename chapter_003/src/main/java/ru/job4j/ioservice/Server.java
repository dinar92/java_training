package ru.job4j.ioservice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Wise oracle will answer all questions.
 */
public class Server {

    /**Socket for interaction with the outside world.*/
    private final Socket socket;

    /**Sets a specific socket.
     * @param socket a specified socket*/
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**An instance of ConsoleChat for getting a random phrase from the file.*/
    private final ConsoleChat chat = new ConsoleChat();

    /**
     * Starts the server.
     */
    public void startServer() {

        try {
            System.out.println("Connect completed!");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String ask = "";
            do {
                System.out.println("Wait command...");
                ask = in.readLine();
                if ("Hello".equals(ask)) {
                    out.println("Hello, my dear friend! I'm an Oracle!");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    out.println(chat.getRandPhrase("Oracle's answers.txt"));
                    out.println();
                } else {
                    out.println("Bye");
                }
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
