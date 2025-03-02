public class Node implements Comparable<Node> {
    private final int frequency;
    private Node left;
    private Node right;

    public Node(int frequency) {
        this.left = null;
        this.right = null;
        this.frequency = frequency;
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }

    public Node getLeftNode() {
        return this.left;
    }

    public Node getRightNode() {
        return this.right;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.frequency, n.frequency);
    }
}
