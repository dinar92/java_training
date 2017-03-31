package ru.job4j.ioservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * The little console chat.
 * @author  pacman
 * @version 1.0
 */
public class ConsoleChat {

    /**Creates a chat that answers the phrases from the specified file.
     * Creates a log file with all messages.
     * At the beginning of the word "стоп" stops returning a response.
     * When you type "продолжить," it resumes the answers.
     * Completes the work, if you enter "закончить".
     * @param pathToPhrases a path to file with phrases
     * @param inputStream a specified input stream*/
    public void startChat(String pathToPhrases, InputStream inputStream) {
        BufferedReader usersInput = new BufferedReader(new InputStreamReader(inputStream));
        File logfile = new File("log.txt");

        try (RandomAccessFile log = new RandomAccessFile(logfile, "rw")) {
            logfile.createNewFile();
            String input = "";
            Boolean isActive = true;

            while (!"закончить".equals(input)) {
                input = usersInput.readLine();
                byte[] arr = (input + '\n').getBytes("UTF-8");
                log.write(arr);

                if ("стоп".equals(input) || "закончить".equals(input)) {
                    isActive = false;
                } else if ("продолжить".equals(input)) {
                    isActive = true;
                }

                if (isActive) {
                    String answer = getRandPhrase(pathToPhrases);
                    byte[] secArr = (answer + '\n').getBytes("UTF-8");
                    log.write(secArr);
                    System.out.println(answer);
                }
            }
            } catch (Exception ex) {
                ex.printStackTrace();
        }
    }

    /**Gets a random phrase from a specified text file with phrases.
     * @param pathToPhrases a path to the file with phrases
     * @return a random phrase*/
    public String getRandPhrase(String pathToPhrases) {

        int i = 0;
        String out = "";
        int randNum = (int) (Math.random() * 100);
        File phrases = new File(pathToPhrases);

        try (RandomAccessFile phrase = new RandomAccessFile(phrases, "r")) {
            while (i < randNum) {

                if (phrase.getFilePointer() == phrase.length()) {
                    phrase.seek(0);
                }
                out = new String(phrase.readLine().getBytes("ISO-8859-1"), "UTF-8");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
