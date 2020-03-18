package main;

import java.io.*;

public class FileUtil {
    public static void write(String text, String file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        }
    }

    public static String read(String file) throws IOException {
        try (BufferedReader writer = new BufferedReader(new FileReader(file))) {
            return writer
                    .lines()
                    .reduce((s, s2) -> s + '\n' + s2)
                    .orElse(null);
        }
    }
}
