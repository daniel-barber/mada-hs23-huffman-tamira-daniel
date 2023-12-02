package src.main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FileReader {
    static Map<Integer, Integer> readTextFile(String fileName) {
        var map = new TreeMap<Integer, Integer>();
        int currentChar;
        try (BufferedReader textReader = new BufferedReader(new java.io.FileReader("src/main/resources/" + fileName))) {
            while ((currentChar = textReader.read()) != -1) {
                if (map.containsKey(currentChar)) {
                    map.put(currentChar, map.get(currentChar) + 1);
                } else {
                    map.put(currentChar, 1);
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(
                "couldn't find " + fileName + " in /resources folder or file not in valid format");
        }
//        if (!isAscii(input)) {
//            throw new RuntimeException(fileName + " contains non-ASCII characters");
//        }

        return map;
    }

//    public static boolean isAscii(String input) {
//        for (int i = 0; i < input.length(); i++) {
//            if (input.charAt(i) > 127) {
//                return false;
//            }
//        }
//        return true;
//    }


}
