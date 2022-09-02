package unit;

import cipher.Friedman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriedmanTests {

    @Test
    void shouldResultInKeyLengthOf7ForEnUs(){
        var result = Friedman.computeFriedman("cifradoEnUs.txt", "en-US");

        assertTrue(result.isPresent());

        assertEquals(7, result.get().keyLength());
    }

    @Test
    void shouldResultInKeyLengthOf7ForPtBr(){
        var result = Friedman.computeFriedman("cifradoPtBr.txt", null);

        assertTrue(result.isPresent());

        assertEquals(7, result.get().keyLength());
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher1(){
        var result = Friedman.computeFriedman("cipher1.txt", null);

        assertTrue(result.isPresent());

        assertEquals(8, result.get().keyLength());
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher2(){
        var result = Friedman.computeFriedman("cipher2.txt", null);

        assertTrue(result.isPresent());

        assertEquals(5, result.get().keyLength());
    }
}
