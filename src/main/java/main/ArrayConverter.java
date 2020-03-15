package main;

import java.util.ArrayList;
import java.util.List;

public class ArrayConverter {
    public static List<Integer> convertToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        return list;
    }
}
