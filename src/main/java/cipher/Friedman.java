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

    public static int computeKeyLength(byte[] text) {

        Map<Integer, Double> indexByKeyLen = new HashMap<>();

        List<Byte> substrBytes = new ArrayList<>();
        int JUMP_INCREMENT = 2;
        for (int startingIndex = 0; startingIndex < MAX_LENGTH; startingIndex++) {
            for (int i = startingIndex; i < text.length; i += JUMP_INCREMENT) {
                substrBytes.add(text[i]);
            }
            double indexOfCoincidence = IOC.computeIndexOfCoincidence(Bytes.toArray(substrBytes));
            indexByKeyLen.put(JUMP_INCREMENT, indexOfCoincidence);
            substrBytes = new ArrayList<>();
            JUMP_INCREMENT++;
        }

        return findFirstMatchingKeyLength(indexByKeyLen);
    }

    public static int computeKeyLength(String filePath) {
        try {
            File textFile = FileReader.getFileFromResourcesFolder(filePath);
            return computeKeyLength(Files.readAllBytes(textFile.toPath()));
        } catch (IOException | URISyntaxException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return -1;
    }

    private static int findFirstMatchingKeyLength(Map<Integer, Double> keyPairs) {
        for (Map.Entry<Integer, Double> entry : keyPairs.entrySet()) {
            double value = entry.getValue();
            if (IOC.getIndexByLanguage("pt-BR") - value <= THRESHOLD || IOC.getIndexByLanguage("en-US") - value <= THRESHOLD)
                return entry.getKey();
        }
        return -1;
    }

}
