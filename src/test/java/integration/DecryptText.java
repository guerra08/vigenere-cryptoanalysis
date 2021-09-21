package integration;

import cipher.Friedman;
import cipher.Vigenere;
import dto.FriedmanDTO;
import dto.ResultDTO;
import org.junit.jupiter.api.Test;
import utils.Reader;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class DecryptText {

    @Test
    void shouldDecryptCifradoEnUs() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cifradoEnUs.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("thisebook", finalString.substring(0, 9));
    }

    @Test
    void shouldDecrypt() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher17.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }


}
