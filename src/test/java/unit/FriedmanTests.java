package unit;

import cipher.Friedman;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FriedmanTests {

    @Test
    void shouldResultInKeyLengthOf7ForEnUs(){
        Map.Entry<Integer, List<String>> result = Friedman.computeKeyLength("cifradoEnUs.txt");

        assert result != null;

        assertEquals(7, result.getKey());
    }

    @Test
    void shouldResultInKeyLengthOf7ForPtBr(){
        Map.Entry<Integer, List<String>> result = Friedman.computeKeyLength("cifradoPtBr.txt");

        assert result != null;

        assertEquals(7, result.getKey());
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher1(){
        Map.Entry<Integer, List<String>> result = Friedman.computeKeyLength("cipher1.txt");

        assert result != null;

        assertEquals(8, result.getKey());
    }
}
