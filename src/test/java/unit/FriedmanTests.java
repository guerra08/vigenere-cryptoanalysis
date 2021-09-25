package unit;

import cipher.Friedman;
import dto.FriedmanDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriedmanTests {

    @Test
    void shouldResultInKeyLengthOf7ForEnUs(){
        FriedmanDTO result = Friedman.computeFriedman("cifradoEnUs.txt", "en-US");

        assert result != null;

        assertEquals(7, result.getKeyLength());
    }

    @Test
    void shouldResultInKeyLengthOf7ForPtBr(){
        FriedmanDTO result = Friedman.computeFriedman("cifradoPtBr.txt", null);

        assert result != null;

        assertEquals(7, result.getKeyLength());
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher1(){
        FriedmanDTO result = Friedman.computeFriedman("cipher1.txt", null);

        assert result != null;

        assertEquals(8, result.getKeyLength());
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher2(){
        FriedmanDTO result = Friedman.computeFriedman("cipher2.txt", null);

        assert result != null;

        assertEquals(5, result.getKeyLength());
    }
}
