package integration;

import cipher.Friedman;
import dto.FriedmanDTO;
import org.junit.jupiter.api.Test;
import processing.General;

import static org.junit.jupiter.api.Assertions.*;

public class MostCommonCharTests {

    @Test
    void shouldReturnMostCommonCharFriedmanOfEnUs(){
        FriedmanDTO result = Friedman.computeFriedman("cifradoEnUs.txt");

        assert result != null;

        byte mostCommon = General.getMostCommonCharInText(result.getSubstrings().get(0));

        assertEquals(113, mostCommon);
    }

}
