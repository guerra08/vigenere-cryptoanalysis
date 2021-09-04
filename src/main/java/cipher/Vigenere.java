package cipher;

public class Vigenere {

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

    public static byte[] decryptKey(byte[] original, byte mostCommonChar){
        int difference = mostCommonChar - 101;
        byte[] decryptedKey = new byte[original.length];

        for (int i = 0; i < original.length; i++) {
            byte b = (byte) ((original[i] - difference));
            if(b < 97)
                b = (byte) (122 - (97 - b));
            decryptedKey[i] = b;
        }

        return decryptedKey;
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
