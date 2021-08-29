package processing;

import utils.FileReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IOC {

    private static final Map<String, Double> indexesByLanguage =
            Map.ofEntries(Map.entry("pt-BR", 0.074), Map.entry("en-US", 0.066));

    public static double getIndexByLanguage(String language){
        return indexesByLanguage.get(language);
    }

    public static double computeIndexOfCoincidence(byte[] textBytes) {
        Map<Character, Long> charOccurrences = new HashMap<>();
        long textSize = textBytes.length;
        for (byte b : textBytes) {
            char letter = (char) b;
            if (charOccurrences.containsKey(letter))
                charOccurrences.put(letter, charOccurrences.get(letter) + 1);
            else
                charOccurrences.put(letter, 1L);
        }
        charOccurrences.remove('\n');
        return charOccurrences.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * (e.getValue() - 1)))
                .values().stream().reduce(Long::sum).map(value -> {
                    long denominator = textSize * (textSize - 1);
                    return ((double) value / denominator);
                }).orElseThrow(RuntimeException::new);
    }

    public static double computeIndexOfCoincidence(String filePath){
        try{
            File textFile = FileReader.getFileFromResourcesFolder(filePath);
            return computeIndexOfCoincidence(Files.readAllBytes(textFile.toPath()));
        } catch (IOException | URISyntaxException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return Double.NEGATIVE_INFINITY;
    }

}
