package src.main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    public static void main(String[] args) {
        Map<Integer, Integer> frequencyTable = FileHandler.readTextFile("hallo-ipt");
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        System.out.println(frequencyTable);

        //Blätter erzeugen
        for (Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            priorityQueue.add(node);
        }

        //Baum erstellen
        while (priorityQueue.size()>1){
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode merged = new HuffmanNode(-1, left.frequency+ right.frequency);
            merged.left = left;
            merged.right = right;

            priorityQueue.add(merged);
        }

        //Huffman Code implementieren
        HuffmanNode root = priorityQueue.poll();
        Map<Integer,String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        //write codes to file
        FileHandler.writeHuffmanToFile("dec_tab.txt", huffmanCodes);

    }

    private static void generateCodes(HuffmanNode node, String code, Map<Integer, String> huffmanCodes) {
        if(node.character != -1){
            huffmanCodes.put(node.character, code);
            return;
        }
        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }
}
