package cipher;

import com.google.common.primitives.Bytes;
import dto.FriedmanDTO;
import processing.IOC;
import utils.Reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Friedman {

    private static final int MAX_LENGTH = 20;
    private static final double THRESHOLD = 0.005;
    private static final String DEFAULT_LANGUAGE = "pt-BR";

    public static FriedmanDTO computeFriedman(byte[] text, String language) {
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
        return computeAndBuildFriedmanDto(iocByKeySize, language);
    }

    public static FriedmanDTO computeFriedman(String filePath, String language) {
        try {
            byte[] bytes = Reader.readFileFromResourcesFolder(filePath);
            return computeFriedman(bytes, language);
        } catch (IOException | URISyntaxException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private static FriedmanDTO computeAndBuildFriedmanDto(Map<Integer, List<Double>> iocByKeySize, String findingFor) {
        boolean matched = false;
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
        if(!matched) return null;
        Map.Entry<Integer, Double> entry = iocByKeySizes.entrySet().iterator().next();
        return new FriedmanDTO(entry.getKey(), language, entry.getValue());
    }

}
