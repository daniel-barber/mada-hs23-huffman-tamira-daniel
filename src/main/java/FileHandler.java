import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler {
    static Map<Integer, Integer> readTextFileToFrequencyTable(String fileName) {
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

    static void writeHuffmanToFile(String fileName, Map<Integer, String> huffmanCodes) {
        StringBuilder output = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter("target/" + fileName)) {
            for (Map.Entry<Integer, String> huffmanCode : huffmanCodes.entrySet()) {
                // gebe aus in Format asciicode:huffmancode-
                output.append(huffmanCode.getKey()).append(":").append(huffmanCode.getValue()).append("-");
            }
            if (output.length() > 0) {
                output.setLength(output.length() - 1);
            }
            fileWriter.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, String> readHuffmanFromFile(String fileName) {
        Map<Integer, String> huffmanCodes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                String[] entries = line.split("-");
                for (String entry : entries) {
                    String[] parts = entry.split(":");
                    if (parts.length == 2) {
                        int key = Integer.parseInt(parts[0]);
                        String value = parts[1];
                        huffmanCodes.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return huffmanCodes;
    }

    public static byte[] readByteArrayFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/" + fileName)) {
            File file = new File("src/main/resources/" + fileName);
            byte[] bFile = new byte[(int) file.length()];
            fis.read(bFile);
            return bFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDecodedTextToFile(String decodedText) {
        try (FileWriter fileWriter = new FileWriter("target/decompress.txt")) {
            fileWriter.write(decodedText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
