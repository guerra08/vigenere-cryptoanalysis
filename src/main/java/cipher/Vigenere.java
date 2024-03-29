package cipher;

import com.google.common.primitives.Bytes;
import model.FriedmanData;
import model.ResultData;
import processing.General;

import java.util.ArrayList;
import java.util.List;

public class Vigenere {

    private static final Character DEFAULT_BASE_CHAR = 'e';

    public static byte[] encryptText(byte[] rawText, byte[] key){
        byte[] generatedKey = generateKey(rawText.length, key);
        int size = rawText.length;
        byte[] encrypted = new byte[size];
        for(int i = 0; i < size; i++){
            char keyChar = (char) generatedKey[i];
            char textChar = (char) rawText[i];
            char result;
            if(Character.isUpperCase(textChar)){
                result = (char)((textChar + Character.toUpperCase(keyChar) - 2 * 'A') % 26);
                result += 'A';
            }
            else{
                result = (char)((textChar + Character.toLowerCase(keyChar) - 2 * 'a') % 26);
                result += 'a';
            }
            encrypted[i] = (byte) result;
        }
        return encrypted;
    }

    public static byte[] decryptText(byte[] encText, byte[] key){
        byte[] generatedKey = generateKey(encText.length, key);
        int size = encText.length;
        byte[] decrypted = new byte[size];
        for(int i = 0; i < size; i++){
            char keyChar = (char) generatedKey[i];
            char textChar = (char) encText[i];
            char result;
            if(Character.isUpperCase(textChar))
                result = (char) ('Z' - (25 - (textChar - Character.toUpperCase(keyChar)) % 26));
            else{
                result = (char) ('z' - (25 - (textChar - Character.toLowerCase(keyChar))) % 26);
            }
            decrypted[i] = (byte) result;
        }
        return decrypted;
    }

    public static ResultData crackText(byte[] encText, FriedmanData friedman, Character baseChar){
        char commonChar = baseChar == null ? DEFAULT_BASE_CHAR : baseChar.charValue();
        StringBuilder key = new StringBuilder();
        int keyLength = friedman.keyLength();
        byte[] crackedText = new byte[encText.length];
        for (int i = 0; i < keyLength; i++) {
            List<Byte> bytesOfSubstr = new ArrayList<>();
            for (int j = i; j < encText.length; j+=keyLength) {
                bytesOfSubstr.add(encText[j]);
            }
            byte mostCommonCharOfSubstr = General.getMostCommonCharInText(new String(Bytes.toArray(bytesOfSubstr)));
            int difference = mostCommonCharOfSubstr - commonChar;
            int keyChar = ('a' + difference < 'a') ? 'z' + difference + 1 : 'a' + difference;
            key.append((char) keyChar);
            for (int j = i, k = 0; j < encText.length && k < bytesOfSubstr.size(); j+=keyLength, k++) {
                int crackedInt = bytesOfSubstr.get(k) - difference;
                if(crackedInt >= 'a' && crackedInt <= 'z')
                    crackedText[j] = (byte) (bytesOfSubstr.get(k) - difference);
                else if(crackedInt < 'a')
                    crackedText[j] = (byte) (crackedInt + 'z' - 'a' + 1);
            }
        }
        return new ResultData(key.toString(), crackedText);
    }

    public static ResultData crackText(byte[] encText, FriedmanData friedman){
        return crackText(encText, friedman, DEFAULT_BASE_CHAR);
    }

    private static byte[] generateKey(int size, byte[] key){
        byte[] genKey = new byte[size];
        int i = 0;
        int j = 0;
        while(true){
            if(i == key.length - 1){
                i = 0;
            }
            if(j == size){
                break;
            }
            genKey[j] = key[i];
            i++;
            j++;
        }
        return genKey;
    }
    
}
