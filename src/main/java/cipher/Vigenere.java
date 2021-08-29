package cipher;

public class Vigenere {

    public static byte[] encryptText(byte[] rawText, byte[] key){
        byte[] generatedKey = generateKey(rawText.length, key);
        int size = rawText.length;
        byte[] encrypted = new byte[size];
        for(int i = 0; i < size; i++){
            char c = (char)((rawText[i] + generatedKey[i]) % 26);
            c += 'A';
            encrypted[i] = (byte) c;
        }
        return encrypted;
    }

    public static byte[] decryptText(byte[] encText, byte[] key){
        byte[] generatedKey = generateKey(encText.length, key);
        int size = encText.length;
        byte[] decrypted = new byte[size];
        for(int i = 0; i < size; i++){
            char c = (char)((encText[i] - generatedKey[i] + 26) % 26);
            c += 'A';
            decrypted[i] = (byte) c;
        }
        return decrypted;
    }

    private static byte[] generateKey(int size, byte[] key){
        byte[] genKey = new byte[size];
        int i = 0;
        int j = 0;
        while(true){
            if(i == key.length - 1){
                i = 0;
            }
            if(j == size - 1){
                break;
            }
            genKey[j] = key[i];
            i++;
            j++;
        }
        return genKey;
    }
    
}
