package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileReader {

    public static File getFileFromResourcesFolder(String fileName) throws FileNotFoundException, URISyntaxException {
        ClassLoader classLoader = FileReader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null)
            throw new FileNotFoundException();

        return new File(resource.toURI());
    }

}
