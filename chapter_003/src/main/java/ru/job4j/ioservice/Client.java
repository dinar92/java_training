package ru.job4j.ioservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * A typical curious client.
 */
public class Client {

    /**Socket for interaction with the outside world.*/
    private final Socket socket;

    /**Sets a specific socket.
     * @param socket a specified socket*/
    public Client(Socket socket) {
        this.socket = socket;
    }

    /**Constantly asking some questions.*/
    public void startClient() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            String str = in.readLine();
            System.out.println(str);
            do {
                String question = console.nextLine();
                out.println(question);
                str = in.readLine();
                while (!("Bye".equals(str)) && !(str.isEmpty())) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!"Bye".equals(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
