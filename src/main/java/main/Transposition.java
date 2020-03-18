package main;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Transposition {
    private static String start(String initData, String key, String flag) {
        String[] keyArr = key.split("|");
        String[] dataArr = initData.split("|");
        int keyLen = keyArr.length;
        int dataLen = dataArr.length;
        int textLen = (dataLen / keyLen);
        LinkedHashMap<String, String[]> dataMatrix = new LinkedHashMap<>();
        StringBuilder resultText = new StringBuilder();

        if (dataLen % keyLen != 0) {
            textLen++;
        }

        String[][] textMatrix = new String[keyLen][textLen];

        int dataArrIndex = 0;
        for (int j = 0; j < textLen; j++) {
            for (int i = 0; i < keyLen; i++) {
                if (dataArrIndex < dataLen) {
                    textMatrix[i][j] = dataArr[dataArrIndex];
                    dataArrIndex++;
                }
            }
        }

        int letter = 1;
        for (int i = 0; i < keyLen; i++) {
            keyArr[i] = keyArr[i] + letter;
            letter++;
        }

        String[] initKeyArr = keyArr.clone();

        if (flag.equals("decode")) {
            Arrays.sort(keyArr);
        }
        for (int i = 0; i < keyLen; i++) {
            dataMatrix.put(keyArr[i], textMatrix[i]);
        }

        Arrays.sort(keyArr);

        if (flag.equals("decode")) {
            keyArr = initKeyArr;
        }

        int temp = 0;
        for (String k : keyArr) {
            textMatrix[temp] = dataMatrix.get(k);
            temp++;
        }

        for (int j = 0; j < textLen; j++) {
            for (int i = 0; i < keyLen; i++) {
                if (textMatrix[i][j] != null) {
                    resultText.append(textMatrix[i][j]);
                } else {
                    resultText.append(" ");
                }
            }
        }

        return resultText.toString();
    }

    public static String encrypt(String text, String key) {
        return start(text, key, "encode");
    }

    public static String decrypt(String text, String key) {
        return start(text, key, "decode");
    }
}
