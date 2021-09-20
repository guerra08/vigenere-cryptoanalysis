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

    public static FriedmanDTO computeFriedman(byte[] text) {
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
        return computeAndBuildFriedmanDto(iocByKeySize);
    }

    public static FriedmanDTO computeFriedman(String filePath) {
        try {
            byte[] bytes = Reader.readFileFromResourcesFolder(filePath);
            return computeFriedman(bytes);
        } catch (IOException | URISyntaxException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private static FriedmanDTO computeAndBuildFriedmanDto(Map<Integer, List<Double>> iocByKeySize) {
        String language = null;
        Map<Integer, Double> iocByKeySizes = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Double>> entry : iocByKeySize.entrySet()) {
            for (Double value : entry.getValue()) {
                double diffPtBr = IOC.getIndexByLanguage("pt-BR") - value;
                double diffEnUs = IOC.getIndexByLanguage("en-US") - value;
                if (diffPtBr <= THRESHOLD){
                    language = "pt-BR";
                    iocByKeySizes.put(entry.getKey(), value);
                }
                else if (diffEnUs <= THRESHOLD){
                    language = "en-US";
                    iocByKeySizes.put(entry.getKey(), value);
                }
            }
        }
        if(language != null){
            Map.Entry<Integer, Double> entry = iocByKeySizes.entrySet().iterator().next();
            return new FriedmanDTO(entry.getKey(), language, entry.getValue());
        }
        return null;
    }

}
