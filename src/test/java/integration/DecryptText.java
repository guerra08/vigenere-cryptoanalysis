package integration;

import cipher.Friedman;
import cipher.Vigenere;
import dto.FriedmanDTO;
import org.junit.jupiter.api.Test;
import utils.FileReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class DecryptText {

    @Test
    void shouldDecryptCifradoEnUs() throws IOException, URISyntaxException {

        File f = FileReader.getFileFromResourcesFolder("cifradoEnUs.txt");
        byte[] fileBytes = Files.readAllBytes(f.toPath());

        FriedmanDTO friedmanResult = Friedman.computeFriedman(fileBytes);

        assert friedmanResult != null;

        Vigenere.crackText(fileBytes, friedmanResult.getLanguage(), friedmanResult.getKeyLength());

        assertTrue(false);

    }


}
