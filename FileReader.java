import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    static String readTextFile(String fileName) {
        String input;
        try (
            BufferedReader textReader = new BufferedReader(new java.io.FileReader("src/main/resources/" + fileName))) {
            input = textReader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException("couldn't find " + fileName + " in /resources folder or file not in valid format");
        }
        if (!isAscii(input)) {
            throw new RuntimeException(fileName + " contains non-ASCII characters");
        }

        return input;
    }

    public static boolean isAscii(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }


}
