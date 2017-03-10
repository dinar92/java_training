package ru.job4j.ioservice;

import java.io.IOException;
import java.io.InputStream;


/**IO handling.*/
public class Service {

    /**Parity checks InputStream's numbers.
     * @param in - input stream
     * @return number is even
     * @throws  IOException IOException
     */
    public boolean isEvenNumber(InputStream in) throws IOException {
        return in.read() % 2 != 1;
    }

}
