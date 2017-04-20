package ru.job4j.ioservice;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
/**
 * Created by pacman on 31.03.17.
 */
public class ConsoleChatTest {

    /**A line separator for joining strings.*/
    private final String ln = System.getProperty("line.separator");
    /**An instance of ConsoleChat.*/
    private ConsoleChat chat = new ConsoleChat();
    /**A file, that contains random phrases.*/
    private File fileWithPhrases = new File("Phrases.txt");

    /**If you enter "закончить", the program does not return anything and stops the work.*/
    @Test
    public void whenAskExitThenExitChat() {
        ByteArrayInputStream in = new ByteArrayInputStream("закончить".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        chat.startChat(fileWithPhrases.getPath(), in);
        assertThat(out.toString(), is(""));
    }

    /**If you enter "stop", the program stops.
     * @throws IOException IOException*/
    @Test
    public void whenAskStopThenChatStops() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileWithPhrases, "r")) {
            byte[] fullText = new byte[(int) file.length()];
            file.readFully(fullText);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    String.join(ln,
                            "стоп",
                            "закончить").getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            chat.startChat(fileWithPhrases.getPath(), in);
            assertThat(out.toString(), is(""));
        }
    }

    /**If you enter "stop", the program is silenced, but after "continue" resumes work.
     * @throws IOException IOException*/
    @Test
    public void whenAskStopAndContinueThenChatWorks() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileWithPhrases, "r")) {
            byte[] fullText = new byte[(int) file.length()];
            file.readFully(fullText);
            ByteArrayInputStream in = new ByteArrayInputStream(
                    String.join(ln,
                            "стоп",
                            "всяко-разно",
                            "продолжить",
                            "закончить").getBytes());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            chat.startChat(fileWithPhrases.getPath(), in);
            assertThat(new String(fullText), containsString(out.toString()));
        }
    }
}
