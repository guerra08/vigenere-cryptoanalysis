package cipher;

import com.google.common.primitives.Bytes;
import processing.IOC;
import utils.FileReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;

public class Friedman {

    private static final int MAX_LENGTH = 20;
    private static final double THRESHOLD = 0.005;

    public static Map.Entry<Integer, List<String>> computeKeyLength(byte[] text) {

        List<Byte> substrBytes = new ArrayList<>();
        int JUMP_INCREMENT = 2;
        for(int i = 0; i < MAX_LENGTH; i++){
            List<Double> indexesOfCoincidence = new ArrayList<>();
            List<String> substrings = new ArrayList<>();
            for (int startingIndex = 0; startingIndex < JUMP_INCREMENT; startingIndex++) {
                for (int j = startingIndex; j < text.length; j += JUMP_INCREMENT) {
                    substrBytes.add(text[j]);
                }
                double indexOfCoincidence = IOC.computeIndexOfCoincidence(Bytes.toArray(substrBytes));
                indexesOfCoincidence.add(indexOfCoincidence);
                substrings.add(new String(Bytes.toArray(substrBytes)));
                substrBytes = new ArrayList<>();
            }
            if(isLengthAdequate(indexesOfCoincidence))
                return Map.entry(JUMP_INCREMENT, substrings);
            JUMP_INCREMENT++;
        }

        return null;
    }

    public static Map.Entry<Integer, List<String>> computeKeyLength(String filePath) {
        try {
            File textFile = FileReader.getFileFromResourcesFolder(filePath);
            return computeKeyLength(Files.readAllBytes(textFile.toPath()));
        } catch (IOException | URISyntaxException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private static boolean isLengthAdequate(List<Double> indexesOfCoincidence) {
        for (Double value : indexesOfCoincidence) {
            if (IOC.getIndexByLanguage("pt-BR") - value <= THRESHOLD || IOC.getIndexByLanguage("en-US") - value <= THRESHOLD)
                return true;
        }
        return false;
    }

}
