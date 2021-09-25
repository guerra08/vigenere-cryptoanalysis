import cipher.Friedman;
import cipher.Vigenere;
import dto.FriedmanDTO;
import dto.ResultDTO;
import utils.Reader;
import utils.Writer;

public class Application {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Vigenere Cryptoanalysis - Bruno Guerra\n");
            System.out.println("java -jar vigenere-cryptoanalysis.jar <file-name> [pt-BR|en-US] \n");
            return;
        }
        try{
            String fileName = args[0];
            byte[] encryptedFileBytes = Reader.readFile(fileName);
            String language = args.length == 2 ? args[1] : null;
            FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes, language);
            if (friedmanResult != null) {
                ResultDTO result = Vigenere.crackText(encryptedFileBytes, friedmanResult);
                System.out.println("Cracked cipher for file: " + fileName);
                System.out.println("Language: " + friedmanResult.getLanguage());
                System.out.println("Key: " + result.getKey());
                Writer.writeToFile(result.getCrackedText(), "output.txt");
                System.out.println("Clear text written on output.txt");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
