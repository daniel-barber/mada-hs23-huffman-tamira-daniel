import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanGenerator {

    public static void main(String[] args) {
        Map<Integer, Integer> frequencyTable = FileHandler.readTextFileToFrequencyTable("hallo-ipt");
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        System.out.println(frequencyTable);

        //Blätter erzeugen
        for (Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            priorityQueue.add(node);
        }

        //Baum erstellen
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode merged = new HuffmanNode(-1, left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            priorityQueue.add(merged);
        }

        //Huffman Code implementieren
        HuffmanNode root = priorityQueue.poll();
        Map<Integer, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        //write codes to file
        FileHandler.writeHuffmanToFile("dec_tab.txt", huffmanCodes);

        //encode to Bitstring
        String encodedBitString = encodeFile("hallo-ipt", huffmanCodes);

        // Schritt 6: Bitstring erweitern
        encodedBitString += "1";  // Fügen Sie eine 1 hinzu
        while (encodedBitString.length() % 8 != 0) {
            encodedBitString += "0";  // Fügen Sie Nullen hinzu, bis die Länge ein Vielfaches von 8 ist
        }

        // Schritt 7: Byte-Array erstellen und in externer Datei speichern
        byte[] byteArray = convertBitStringToByteArray(encodedBitString);
        saveByteArrayToFile(byteArray, "output.dat");
    }


    private static String encodeFile(String fileName, Map<Integer, String> huffmanCodes) {
        StringBuilder encodedBitString = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                String huffmanCode = huffmanCodes.get(c);
                if (huffmanCode != null) {
                    encodedBitString.append(huffmanCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedBitString.toString();
    }


    private static void generateCodes(HuffmanNode node, String code, Map<Integer, String> huffmanCodes) {
        if (node.character != -1) {
            huffmanCodes.put(node.character, code);
            return;
        }
        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }

    private static byte[] convertBitStringToByteArray(String bitString) {
        int length = bitString.length();
        byte[] byteArray = new byte[length / 8];

        for (int i = 0; i < length; i += 8) {
            String byteStr = bitString.substring(i, i + 8);
            byteArray[i / 8] = (byte) Integer.parseInt(byteStr, 2);
        }

        return byteArray;
    }

    private static void saveByteArrayToFile(byte[] out, String fileName) {
        try (FileOutputStream fos = new FileOutputStream("target/" + fileName)) {
            fos.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
