package main;

import java.io.IOException;

public class Main {
    private static final String WORD_FOR_CODE = "Prison cipher is a method of transcoding a message in order to bring " +
            "it to a form convenient for transmission over a communication channel";
    private static final String TRANSPOSITION_KEY = "45456";
    private static final String VIGENERE_KEY = Vigenere.generateKey(10);

    public static void main(String... args) throws IOException {
        String coded = Transposition.encrypt(WORD_FOR_CODE, TRANSPOSITION_KEY);
        String decoded = Transposition.decrypt(coded, TRANSPOSITION_KEY);
        System.out.println("TRANSPOSITION");
        System.out.println("CODED WORD: " + coded);
        FileUtil.write(coded, "transposition-coded-file.txt");
        System.out.println("DECODED WORD: " + decoded);
        System.out.println();

        coded = Vigenere.encrypt(WORD_FOR_CODE, VIGENERE_KEY);
        decoded = Vigenere.decrypt(coded, VIGENERE_KEY);

        System.out.println("VIGENERE");
        System.out.println("CODED WORD: " + coded);
        FileUtil.write(coded, "vigenere-coded-file.txt");
        System.out.println("DECODED WORD: " + decoded);
    }
}
