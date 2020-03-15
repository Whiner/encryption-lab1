package main;

public class Main {
    private static final String WORD_FOR_CODE = "Prison cipher is a method of transcoding a message in order to bring " +
            "it to a form convenient for transmission over a communication channel";
    private static final String KEY = "alahuachs";

    public static void main(String... args) {
        JailEncoder jailEncoder = new JailEncoder(KEY);
        String coded = jailEncoder.encodeWord(WORD_FOR_CODE);
        String decoded = jailEncoder.decodeWord(coded);
        System.out.println("DECODED WORD: " + decoded);
    }
}
