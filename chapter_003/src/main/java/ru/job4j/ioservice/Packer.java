package ru.job4j.ioservice;

import java.io.IOException;

/**
 * The interface foe all packers.
 * @version 1.0
 * @author gimazetdinov
 */
public interface Packer {

    /**The method for packing data.
     * @throws IOException IOException*/
    void pack() throws IOException;
}
