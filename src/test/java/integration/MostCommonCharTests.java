package integration;

import cipher.Friedman;
import org.junit.jupiter.api.Test;
import processing.General;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MostCommonCharTests {

    @Test
    void shouldReturnMostCommonCharFriedmanOfEnUs(){
        Map.Entry<Integer, List<String>> result = Friedman.computeKeyLength("cifradoEnUs.txt");

        assert result != null;

        byte mostCommon = General.getMostCommonCharInText(result.getValue().get(0));

        assertTrue(mostCommon > 0);
    }

}
