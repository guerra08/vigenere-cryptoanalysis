package utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class Reader {

    public static byte[] readFileFromResourcesFolder(String fileName) throws IOException, URISyntaxException {
        ClassLoader classLoader = Reader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null)
            throw new FileNotFoundException();

        File f = new File(resource.toURI());

        return Files.readAllBytes(f.toPath());
    }

    public static byte[] readFile(String fileName) throws IOException {
        File f = new File(fileName);

        return Files.readAllBytes(f.toPath());
    }

}
