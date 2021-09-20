import cipher.Friedman;
import cipher.Vigenere;
import dto.FriedmanDTO;
import utils.Reader;
import utils.Writer;

public class Application {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Vigenere Cryptoanalysis - Bruno Guerra\n");
            System.out.println("java -jar vigenere-cryptoanalysis.jar [file-name] \n");
            return;
        }
        try{
            String fileName = args[0];
            byte[] encryptedFileBytes = Reader.readFile(fileName);
            FriedmanDTO friedmanResult = Friedman.computeFriedman(encryptedFileBytes);
            if (friedmanResult != null) {
                byte[] crackedBytes = Vigenere.crackText(encryptedFileBytes, friedmanResult);
                Writer.writeToFile(crackedBytes, "output.txt");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
