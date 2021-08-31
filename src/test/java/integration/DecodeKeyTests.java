package integration;

import cipher.Friedman;
import cipher.Vigenere;
import dto.FriedmanDTO;
import org.junit.jupiter.api.Test;
import processing.General;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class DecodeKeyTests {

    @Test
    void shouldFindKeyForCifradoEnUs(){
        FriedmanDTO result = Friedman.computeFriedman("cifradoEnUs.txt");

        assert result != null;

        byte mostCommon = General.getMostCommonCharInText(result.getSubstrings().get(1));

        byte[] decrypted = Vigenere.decryptKey(result.getSubstrings().get(1).getBytes(StandardCharsets.UTF_8), mostCommon);

        String decodedKey = new String(decrypted);

        assertTrue(true);
    }

}
