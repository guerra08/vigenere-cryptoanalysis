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

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("thisebook", finalString.substring(0, 9));
    }

    @Test
    void shouldDecryptCipher5() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher5.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher15() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher15.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher20() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher20.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher13() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher13.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher10() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher10.txt");

        FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assert friedmanResult != null;

        ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);

        String finalString = new String(result.getCrackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

}
