package ru.job4j.ioservice;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Tests Server.
 */
public class ServerTest {

    /**
     * A line separator.
     */
    private final String ln = System.getProperty("line.separator");

    /**
     * Method for testing output values from the server.
     *
     * @param inStr  input string
     * @param outStr what must be in out
     * @throws IOException IOException
     */
    private void startTest(String inStr, String outStr) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(inStr.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.startServer();
        assertThat(out.toString(), is(outStr));
    }

    /**
     * When request contains "exit", then must be response "Bye".
     *
     * @throws IOException IOException
     */
    @Test
    public void whenRequestExitThenResponseBye() throws IOException {
        startTest("exit", "Bye\n");
    }

    /**
     * When request contains "Hello", then must be response "Hello".
     *
     * @throws IOException IOException
     */
    @Test
    public void whenRequestHelloThenResponseHello() throws IOException {
        startTest(String.join(
                ln,
                "Hello",
                "exit"), String.join(
                ln,
                "Hello, my dear friend! I'm an Oracle!",
                "",
                "Bye",
                ""));
    }

    /**
     * When request contains any question, then must be response any phrase, that
     * contains in the file with phrases.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenRequestQuestionThenResponseAnswer() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(new File("Oracle's answers.txt"), "r")) {
            byte[] fullText = new byte[(int) file.length()];
            file.readFully(fullText);
            ByteArrayInputStream in = new ByteArrayInputStream(String.join(
                    ln,
                    "Will I become a java programmer?",
                    "exit"
            ).getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Socket socket = mock(Socket.class);
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Server server = new Server(socket);
            server.startServer();
            assertThat(new String(fullText), is(containsString(out.toString().replaceAll("\\n\\nBye", ""))));
        }
    }
}
