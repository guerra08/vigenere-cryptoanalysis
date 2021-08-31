package processing;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class General {

    public static byte getMostCommonCharInText(String text){
        Map<Byte, Integer> charOccurrences = new HashMap<>();
        for(byte b : text.getBytes(StandardCharsets.UTF_8)){
            if(charOccurrences.containsKey(b))
                charOccurrences.put(b, charOccurrences.get(b) + 1);
            else charOccurrences.put(b, 1);
        }
        Optional<Map.Entry<Byte, Integer>> entry =
            charOccurrences.entrySet().stream().max((o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1);
        if(entry.isPresent())
            return entry.get().getKey();
        else return 0;
    }

}
