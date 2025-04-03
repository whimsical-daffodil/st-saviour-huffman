public class Node implements Comparable<Node> {
   private int frequency;
   private Node left;
   private Node right;




  
   /*
    * TODO
    * The Node class should have two constructors. The first should accept an int frequency and
    * set both left and right Node values to null (this covers Leaf, which exends Node). The
    * second Node constructor should accept two Nodes (left and right) and set the frequency to
    * the sum of both Node frequencies (this is used when building a Tree from a PriorityQueue).
    *
    * Finally, the Node class should implement getLeftNode() and getRightNode() getter methods.
    */


      // Constructor one
   public Node(int frequency) {
       this.frequency = frequency;
       this.left = null;
       this.right = null;
   }


   // Constructor two
   public Node(Node left, Node right) {
       this.frequency = left.frequency + right.frequency;
       this.left = left;
       this.right = right;
   }


    // Getter stuff
  
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


