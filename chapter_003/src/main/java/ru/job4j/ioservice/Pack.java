package ru.job4j.ioservice;

import java.io.IOException;

/**Main class.
 * @author gimazetdinov
 * @version 1.0*/
public class Pack {

   /**Creates an instance of Zip archiver and sets parameters.
    * @param args command line arguments
    * @throws IOException IOException*/
    public static void main(String[] args) throws IOException {

        String path = "/";
        String zipName = "default.zip";
        String extensions = "java, xml";
        for (int arg = 0; arg < args.length; arg++) {
            if ("-d".equals(args[arg])) {
                path = args[arg + 1];
            } else if ("-o".equals(args[arg])) {
                zipName = args[arg + 1];
            } else if ("-e".equals(args[arg])) {
                extensions = args[arg + 1];
            }
        }

        new ZipPacker(path, extensions, zipName).pack();
    }
}
