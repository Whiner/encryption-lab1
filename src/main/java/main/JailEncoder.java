package main;

import java.util.ArrayList;
import java.util.List;

public class JailEncoder {
    private String codeWord;
    private List<Integer> codes = new ArrayList<>();
    private final int SMALL_CODES_BEGIN = 97;
    private final int SMALL_CODES_END = 122;
    private final int CAPITAL_CODES_BEGIN = 65;
    private final int CAPITAL_CODES_END = 90;
    private final int SPACE_VALUE = 32;
    private final int MATRIX_HEIGHT = 5;
    private final int MATRIX_WIDTH = 6;

    public JailEncoder(String codeWord) {
        this.codeWord = codeWord;
        createCodingMatrix(codeWord);
    }

    public String encodeWord(String word) {
        List<Integer> codedWord = new ArrayList<>();
        word.chars().forEach(character -> {
            codedWord.add(getWidthCode(character));
            codedWord.add(getHeightCode(character));
        });
        StringBuilder stringBuilder = new StringBuilder();
        codedWord.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public String decodeWord(String word) {
        List<Character> decodedWord = new ArrayList<>();
        for (int i = 0; i < word.length() - 1; i += 2) {
            decodedWord.add((char) getValue(
                    Integer.parseInt(String.valueOf(word.charAt(i))),
                    Integer.parseInt(String.valueOf(word.charAt(i + 1)))
            ));
        }
        StringBuilder stringBuilder = new StringBuilder();
        decodedWord.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private void createCodingMatrix(String word) {
        if (word == null) {
            return;
        }

        codes.clear();
        List<Integer> newWord = ArrayConverter.convertToList(deleteRepeatedCharacters(word));
        for (int i = SMALL_CODES_BEGIN; i < SMALL_CODES_END; i++) {
            codes.add(i);
        }
        for (int i = CAPITAL_CODES_BEGIN; i < CAPITAL_CODES_END; i++) {
            codes.add(i);
        }
        codes.add(SPACE_VALUE);
        codes.removeAll(newWord);
        codes.addAll(0, newWord);
    }

    private int[] deleteRepeatedCharacters(String word) {
        return word.chars()
                .distinct()
                .toArray();
    }

    private int getValue(int width, int height) {
        return codes.get(height * MATRIX_WIDTH + width);
    }

    private int getWidthCode(int value) {
        return codes.indexOf(value) % MATRIX_WIDTH;
    }

    private int getHeightCode(int value) {
        return codes.indexOf(value) / MATRIX_WIDTH;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }
}
