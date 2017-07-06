package ru.job4j.ioservice.networkManager;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pacman on 27.06.17.
 * Tests the class NetworkClient.
 */
public class NetworkClientTest {

    /**
     * Tests startClient(String pathToRoot).
     * @throws IOException IOException.
     */
    @Test
    public void whenGetsFromServerMessageShouldDisplayThem() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String usersInputFromConsole = "exit\n";
        ByteArrayInputStream streamFromConsole = new ByteArrayInputStream(usersInputFromConsole.getBytes());
        System.setIn(streamFromConsole);
        String inputFromServer = "Hello\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputFromServer.getBytes());

        ByteArrayOutputStream outputToServer = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputToServer);
        new NetworkClient(socket).startClient("/home/pacman/Development");
        assertThat(outputStream.toString(), is(inputFromServer));
    }
}
