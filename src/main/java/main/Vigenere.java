package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class Vigenere {
    private final static String NAME = "Vigenere Cipher";
    private static HashMap<Character, Integer> charMap;
    private final static char encryptionArr[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    static {
        charMap = new HashMap<>();
        charMap.put('A', 0);
        charMap.put('B', 1);
        charMap.put('C', 2);
        charMap.put('D', 3);
        charMap.put('E', 4);
        charMap.put('F', 5);
        charMap.put('G', 6);
        charMap.put('H', 7);
        charMap.put('I', 8);
        charMap.put('J', 9);
        charMap.put('K', 10);
        charMap.put('L', 11);
        charMap.put('M', 12);
        charMap.put('N', 13);
        charMap.put('O', 14);
        charMap.put('P', 15);
        charMap.put('Q', 16);
        charMap.put('R', 17);
        charMap.put('S', 18);
        charMap.put('T', 19);
        charMap.put('U', 20);
        charMap.put('V', 21);
        charMap.put('W', 22);
        charMap.put('X', 23);
        charMap.put('Y', 24);
        charMap.put('Z', 25);
    }

    public static String generateKey(int length) {
        if (length <= 0) {
            throw new RuntimeException("мда дурачек");
        }
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int chosenLength = secureRandom.nextInt(length);
        if (chosenLength == 0) {
            chosenLength = 1;
        }
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < chosenLength; i++) {
            int randomValue = secureRandom.nextInt(26);
            key.append(encryptionArr[randomValue]);
        }
        return key.toString();
    }

    public static String encrypt(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        if (plainText.length() <= 0 || key.length() <= 0) {
            throw new RuntimeException("мда дурачек");
        }
        plainText = plainText.trim();
        plainText = plainText.replaceAll("\\W", "");
        key = key.trim();
        if (plainText.contains(" ") || key.contains(" ")) {
            plainText = plainText.replaceAll(" ", "");
            key = key.replaceAll(" ", "");
        }
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            char keyLetter = key.charAt((i % key.length()));
            int lookUp = (charMap.get(letter) + charMap.get(keyLetter)) % 26;
            encryptedText.append(encryptionArr[lookUp]);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        if (cipherText.length() <= 0 || key.length() <= 0) {
            throw new RuntimeException("мда дурачек");
        }
        cipherText = cipherText.trim();
        cipherText = cipherText.replaceAll("\\W", "");
        key = key.trim();
        if (cipherText.contains(" ") || key.contains(" ")) {
            cipherText = cipherText.replaceAll(" ", "");
            key = key.replaceAll(" ", "");
        }
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            char keyLetter = key.charAt((i % key.length()));
            int lookUp = (charMap.get(letter) - charMap.get(keyLetter)) % 26;
            if (lookUp < 0) {
                lookUp += 26;
            }
            plainText.append(encryptionArr[lookUp]);
        }
        return plainText.toString();
    }
}
