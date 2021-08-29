import cipher.Vigenere;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class VigenereTests {

    public String rawText = "TEST";
    public String key = "MYHERO";
    public String encText = "FCZX";

    @Test
    void shouldEncryptText(){
        byte[] encryptedText = Vigenere.encryptText(
                rawText.getBytes(StandardCharsets.UTF_8),
                key.getBytes(StandardCharsets.UTF_8)
        );

        System.out.println(encText);
        System.out.println(new String(encryptedText));

        assertEquals(encText.getBytes(StandardCharsets.UTF_8), encryptedText);
    }

}
