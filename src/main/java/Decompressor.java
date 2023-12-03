import java.util.Map;

public class Decompressor {
    public static void main(String[] args) {
        //read the inputs
        Map<Integer, String> huffmanCodes = FileHandler.readHuffmanFromFile("dec_tab-mada.txt");
        byte[] byteArray = FileHandler.readByteArrayFromFile("output-mada.dat");

        String encodedBitString = convertByteArrayToBitString(byteArray);
        String trimmedAndReversedBitString = decodeAndReverse(encodedBitString);
        String decodedText = decodeToASCII(trimmedAndReversedBitString, huffmanCodes);
        FileHandler.writeDecodedTextToFile(decodedText);
    }

    private static String convertByteArrayToBitString(byte[] byteArray) {
        StringBuilder bitString = new StringBuilder();
        for (byte b : byteArray) {
            for (int i = 7; i >= 0; i--) {
                int bit = (b >> i) & 1;
                bitString.append(bit);
            }
        }
        return bitString.toString();
    }

    private static String decodeAndReverse(String encodedBitString) {
        // entferne letztes 1 und hintere nulle
        int lastIndex = encodedBitString.lastIndexOf("1");
        return encodedBitString.substring(0, lastIndex + 1);
    }

    private static String decodeToASCII(String trimmedBitString, Map<Integer, String> huffmanCodes) {
        StringBuilder decodedText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();

        for (char bit : trimmedBitString.toCharArray()) {
            currentCode.append(bit);

            // Check if the current code is a valid Huffman code
            for (Map.Entry<Integer, String> entry : huffmanCodes.entrySet()) {
                if (entry.getValue().equals(currentCode.toString())) {
                    decodedText.append((char) entry.getKey().intValue());
                    currentCode.setLength(0); // Clear the current code
                    break;
                }
            }
        }

        return decodedText.toString();
    }
}
