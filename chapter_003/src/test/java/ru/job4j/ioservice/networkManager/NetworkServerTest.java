package ru.job4j.ioservice.networkManager;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pacman on 28.06.17.
 * Tests class NetworkServer.
 */
public class NetworkServerTest {

    /**
     * Tests startServer(String pathToRoot).
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenInputLsShouldDisplayContentOfCurrentDirectory() throws IOException {
        String usersInput = "ls\nexit\n";
        ByteArrayInputStream inputFromUser = new ByteArrayInputStream(usersInput.getBytes());
        ByteArrayOutputStream outputToUser = new ByteArrayOutputStream();

        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(inputFromUser);
        when(socket.getOutputStream()).thenReturn(outputToUser);

        new NetworkServer(socket).startServer("/home");

        assertThat(outputToUser.toString(), containsString("pacman\n"));
    }

    /**
     * Tests "The change directory" function.
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenCDToSubDirShouldMoveToSubDir() throws IOException {
        String usersInput = "cd ..\nexit\n";
        ByteArrayInputStream inputFromUser = new ByteArrayInputStream(usersInput.getBytes());
        ByteArrayOutputStream outputToUser = new ByteArrayOutputStream();

        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(inputFromUser);
        when(socket.getOutputStream()).thenReturn(outputToUser);

        new NetworkServer(socket).startServer("/home/pacman");

        assertThat(outputToUser.toString(), is("/home\n\nServer stopped!\n"));
    }

    /**
     * Tests "the generate warning" function.
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenUnknownComandShouldWarningMessage() throws IOException {
        String usersInput = "unknown\nexit\n";
        ByteArrayInputStream inputFromUser = new ByteArrayInputStream(usersInput.getBytes());
        ByteArrayOutputStream outputToUser = new ByteArrayOutputStream();

        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(inputFromUser);
        when(socket.getOutputStream()).thenReturn(outputToUser);

        new NetworkServer(socket).startServer("/home");

        assertThat(outputToUser.toString(), containsString("Unknown command: unknown"));
    }

    /**
     * Tests "the not found such directory" function.
     *
     * @throws IOException IOException.
     */
    @Test
    public void whenNonexistentDirShouldWarningMessage() throws IOException {
        String usersInput = "cd description.txt\nexit\n";
        ByteArrayInputStream inputFromUser = new ByteArrayInputStream(usersInput.getBytes());
        ByteArrayOutputStream outputToUser = new ByteArrayOutputStream();

        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(inputFromUser);
        when(socket.getOutputStream()).thenReturn(outputToUser);

        new NetworkServer(socket).startServer("/home/pacman/Development");

        assertThat(outputToUser.toString(), is("Not found such directory!\n\nServer stopped!\n"));
    }


}
