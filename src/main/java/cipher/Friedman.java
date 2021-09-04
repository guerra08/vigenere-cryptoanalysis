package cipher;

import com.google.common.primitives.Bytes;
import dto.FriedmanDTO;
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

    public static FriedmanDTO computeFriedman(byte[] text) {

        List<Byte> substrBytes = new ArrayList<>();
        int JUMP_INCREMENT = 2;
        for(int i = 0; i < MAX_LENGTH; i++){
            List<Double> indexesOfCoincidence = new ArrayList<>();
            for (int startingIndex = 0; startingIndex < JUMP_INCREMENT; startingIndex++) {
                for (int j = startingIndex; j < text.length; j += JUMP_INCREMENT) {
                    substrBytes.add(text[j]);
                }
                double indexOfCoincidence = IOC.computeIndexOfCoincidence(Bytes.toArray(substrBytes));
                indexesOfCoincidence.add(indexOfCoincidence);
                substrBytes = new ArrayList<>();
            }
            String language = checkIndexesByLanguages(indexesOfCoincidence);
            if(language != null)
                return new FriedmanDTO(JUMP_INCREMENT, language);
            JUMP_INCREMENT++;
        }

        return null;
    }

    public static FriedmanDTO computeFriedman(String filePath) {
        try {
            File textFile = FileReader.getFileFromResourcesFolder(filePath);
            return computeFriedman(Files.readAllBytes(textFile.toPath()));
        } catch (IOException | URISyntaxException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private static String checkIndexesByLanguages(List<Double> indexesOfCoincidence) {
        for (Double value : indexesOfCoincidence) {
            if (IOC.getIndexByLanguage("pt-BR") - value <= THRESHOLD)
                return "pt-BR";
            else if (IOC.getIndexByLanguage("en-US") - value <= THRESHOLD)
                return "en-US";
        }
        return null;
    }

}
