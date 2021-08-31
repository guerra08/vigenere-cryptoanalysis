import cipher.Friedman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriedmanTests {

    @Test
    void shouldResultInKeyLengthOf7ForEnUs(){
        int len = Friedman.computeKeyLength("cifradoEnUs.txt");

        assertEquals(7, len);
    }

    @Test
    void shouldResultInKeyLengthOf7ForPtBr(){
        int len = Friedman.computeKeyLength("cifradoPtBr.txt");

        assertEquals(7, len);
    }

    @Test
    void shouldResultInKeyLengthOf8ForCipher1(){
        int len = Friedman.computeKeyLength("cipher1.txt");

        assertEquals(8, len);
    }
}
