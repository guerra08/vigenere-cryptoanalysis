package cipher;

import com.google.common.primitives.Bytes;
import model.FriedmanData;
import processing.IOC;
import utils.Reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Friedman {

    private static final int MAX_LENGTH = 20;
    private static final double THRESHOLD = 0.005;
    private static final String DEFAULT_LANGUAGE = "pt-BR";

    public static Optional<FriedmanData> computeFriedman(byte[] text, String language) {
        List<Byte> substrBytes = new ArrayList<>();
        Map<Integer, List<Double>> iocByKeySize = new HashMap<>();
        int JUMP_INCREMENT = 2;
        for (int i = 0; i < MAX_LENGTH; i++) {
            List<Double> indexesOfCoincidence = new ArrayList<>();
            for (int startingIndex = 0; startingIndex < JUMP_INCREMENT; startingIndex++) {
                for (int j = startingIndex; j < text.length; j += JUMP_INCREMENT) {
                    substrBytes.add(text[j]);
                }
                double indexOfCoincidence = IOC.computeIndexOfCoincidence(Bytes.toArray(substrBytes));
                indexesOfCoincidence.add(indexOfCoincidence);
                substrBytes = new ArrayList<>();
            }
            iocByKeySize.put(JUMP_INCREMENT, indexesOfCoincidence);
            JUMP_INCREMENT++;
        }
        return computeFriedman(iocByKeySize, language);
    }

    public static Optional<FriedmanData> computeFriedman(String filePath, String language) {
        try {
            byte[] bytes = Reader.readFileFromResourcesFolder(filePath);
            return computeFriedman(bytes, language);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static Optional<FriedmanData> computeFriedman(Map<Integer, List<Double>> iocByKeySize, String findingFor) {
        var matched = false;
        String language = findingFor == null ? DEFAULT_LANGUAGE : findingFor;
        Map<Integer, Double> iocByKeySizes = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Double>> entry : iocByKeySize.entrySet()) {
            double sumOfIoc = entry.getValue().stream().mapToDouble(e -> e).sum();
            double avgIoc = sumOfIoc / entry.getKey();
            double diff = IOC.getIndexByLanguage(language) - avgIoc;
            if (diff <= THRESHOLD){
                matched = true;
                iocByKeySizes.put(entry.getKey(), avgIoc);
            }
        }
        if(!matched) return Optional.empty();
        var entry = iocByKeySizes.entrySet().iterator().next();
        return Optional.of(new FriedmanData(entry.getKey(), language, entry.getValue()));
    }

}
