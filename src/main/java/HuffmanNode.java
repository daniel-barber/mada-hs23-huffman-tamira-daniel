public class HuffmanNode implements Comparable<HuffmanNode> {
    int character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency - node.frequency;
    }
}
