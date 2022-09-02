package integration;

import cipher.Friedman;
import cipher.Vigenere;
import org.junit.jupiter.api.Test;
import utils.Reader;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class DecryptText {

    @Test
    void shouldDecryptCifradoEnUs() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cifradoEnUs.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, "en-US");

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("thisebook", finalString.substring(0, 9));
    }

    @Test
    void shouldDecryptCipher5() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher5.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher15() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher15.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher20() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher20.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher13() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher13.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

    @Test
    void shouldDecryptCipher10() throws IOException, URISyntaxException {
        byte[] encryptedFileBytes = Reader.readFileFromResourcesFolder("cipher10.txt");

        var friedmanResult = Friedman.computeFriedman(encryptedFileBytes, null);

        assertTrue(friedmanResult.isPresent());

        var result = Vigenere.crackText(encryptedFileBytes, friedmanResult.get());

        var finalString = new String(result.crackedText());

        assertEquals("biblia", finalString.substring(0,6));
    }

}
