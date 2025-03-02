import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {

    private Node root;
    private final String text;
    private Map<Character, Integer> frequencies;
    private final Map<Character, String> codes;

    public Huffman(String text) {
        this.text = text;
        populateFrequenciesMap();
        codes = new HashMap<>();
    }

    private void populateFrequenciesMap() {
        frequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            if (frequencies.get(character) == null) {
                frequencies.put(character, 0);
            } else {
                frequencies.put(character, frequencies.get(character) + 1);
            }
        }
    }

    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();

        for(Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new Leaf(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node node = new Node(queue.poll(), queue.poll());
            queue.add(node);
        }

        this.root = queue.poll();

        generateCodes(this.root, "");
        return getEncodedText();
    }

    private void generateCodes(Node node, String code) {
        if (node instanceof Leaf leaf) {
            codes.put(leaf.getCharacter(), code);
            return;
        }

        generateCodes(node.getLeftNode(), code.concat("0"));
        generateCodes(node.getRightNode(), code.concat("1"));
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for (char character : text.toCharArray()) {
            sb.append(codes.get(character));
        }
        return sb.toString();
    }

   /*
    * The decode() method accepts a huffman-encoded String and returns a plaintext String.
    */
    public String decode(String encoded) {
        /*
         * 1. Start by creating a variable of type Node to keep track of the current Node.
         *    The initial starting value should be root. (ex: Node current = this.root;) 
         * 2. For every char in the encoded String ...
         *      - If the character is '0' navigate left by pointing the current Node to current's left Node.
         *      - Otherwise, navigate right by pointing the current Node to the current's right Node.
         *      - If the current Node is a Leaf, append the character to the StringBuilder and reset current Node to root.
         */
        StringBuilder builder = new StringBuilder();
        Node current = this.root;
        for (char character : encoded.toCharArray()) {
            if(character == '0') {
                current = current.getLeftNode();
            } else {
                current = current.getRightNode();
            }
            if (current instanceof Leaf leaf) {
                builder.append(leaf.getCharacter());
                current = root;
            }
        }
        return builder.toString();
    }


    public void printCodes() {
        codes.forEach((character, code) ->
                System.out.println(character + ": " + code)
        );
    }
}