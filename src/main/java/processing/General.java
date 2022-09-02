package processing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class General {

    public static byte getMostCommonCharInText(String text){
        return text.chars()
            .mapToObj(c -> (char)c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(entry -> (byte)entry.getKey().charValue())
            .orElse((byte) 0);
    }

}
