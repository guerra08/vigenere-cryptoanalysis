package processing;

import com.google.common.primitives.Bytes;
import utils.Reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IOC {

    private static final Map<String, Double> indexesByLanguage =
            Map.ofEntries(Map.entry("pt-BR", 0.074), Map.entry("en-US", 0.066));

    public static double getIndexByLanguage(String language){
        return indexesByLanguage.get(language);
    }

    public static double computeIndexOfCoincidence(byte[] textBytes) {
        long textSize = textBytes.length;
        var occurrences = Bytes.asList(textBytes).stream()
            .map(b -> (char)b.byteValue())
            .filter(c -> !c.equals('\n') && !c.equals('\r'))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return occurrences.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * (e.getValue() - 1)))
                .values().stream().reduce(Long::sum).map(value -> {
                    long denominator = textSize * (textSize - 1);
                    return ((double) value / denominator);
                }).orElseThrow(RuntimeException::new);
    }

    public static double computeIndexOfCoincidence(String filePath){
        try{
            byte[] bytes = Reader.readFileFromResourcesFolder(filePath);
            return computeIndexOfCoincidence(bytes);
        } catch (IOException | URISyntaxException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return Double.NEGATIVE_INFINITY;
    }

}
