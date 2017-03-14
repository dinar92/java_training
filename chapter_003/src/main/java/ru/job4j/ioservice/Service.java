package ru.job4j.ioservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**IO handling.*/
public class Service {

    /**
     * Parity checks InputStream's numbers.
     *
     * @param in - input stream
     * @return number is even
     * @throws IOException IOException
     */
    public boolean isEvenNumber(InputStream in) throws IOException {
        return in.read() % 2 != 1;
    }

    /**Filters out forbidden words.
     * @param in - input stream
     * @param out - output stream
     * @param abuse - forbidden words
     * @throws IOException IOException
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (InputStreamReader charIn = new InputStreamReader(in); OutputStreamWriter charOut = new OutputStreamWriter(out)) {
            int currentChar = charIn.read();
            int longestWordsLength = 0;

            for (String word : abuse) {
                if (word.length() > longestWordsLength) {
                    longestWordsLength = word.length();
                }
            }

            int[][] queueCollector = new int[abuse.length + 1][longestWordsLength];

            while (currentChar != -1) {
                boolean mustOut = true;
                for (int wordCounter = 0; wordCounter < abuse.length; wordCounter++) {
                    System.out.println(queueCollector[abuse.length][wordCounter]);
                    if (currentChar == abuse[wordCounter].charAt(queueCollector[abuse.length][wordCounter])) {
                        queueCollector[wordCounter][queueCollector[abuse.length][wordCounter]] = currentChar;
                        queueCollector[abuse.length][wordCounter]++;
                        mustOut = false;
                    } else {
                        queueCollector[abuse.length][wordCounter] = 0;
                    }
                    if (queueCollector[abuse.length][wordCounter] == abuse[wordCounter].length()) {
                        queueCollector[abuse.length][wordCounter] = 0;
                    }
                }
                if (mustOut) {
                    charOut.write(currentChar);
                }
                currentChar = charIn.read();
            }
        }
    }
}