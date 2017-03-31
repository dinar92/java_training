package ru.job4j.ioservice;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests Client.
 */
public class ClientTest {

    /**
     * When response contains "Bye", then client exits.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenResponseIsByeThenExit() throws IOException {

        /**The client's console input emulation.*/
        System.setIn(new ByteArrayInputStream("some text".getBytes()));

        ByteArrayInputStream in = new ByteArrayInputStream("Bye".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.startClient();
        assertThat(out.toString(), is("some text\n"));
    }
}
