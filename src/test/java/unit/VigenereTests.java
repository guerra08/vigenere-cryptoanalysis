package unit;

import cipher.Vigenere;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class VigenereTests {

    public String rawText = "test";
    public String key = "myhero";
    public String encText = "fczx";

    @Test
    void shouldEncryptText(){
        byte[] encryptedText = Vigenere.encryptText(
                rawText.getBytes(StandardCharsets.UTF_8),
                key.getBytes(StandardCharsets.UTF_8)
        );

        assertEquals(encText, new String(encryptedText));
    }

    @Test
    void shouldDecryptText(){
        byte[] decryptedText = Vigenere.decryptText(
                encText.getBytes(StandardCharsets.UTF_8),
                key.getBytes(StandardCharsets.UTF_8)
        );

        assertEquals(rawText, new String(decryptedText));
    }

}
