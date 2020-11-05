package com.stones.stoneshomework;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TestUtils {
    public static String readResourceFile(ClassLoader loader, String pathToFile) {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream inputStream = Objects.requireNonNull(loader.getResourceAsStream(pathToFile));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = reader.readLine();
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
