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


   /*
    * populateFrequenciesMap() is invoked within the Huffman constructor. The method initializes the
    * frequencies HashMap and records all unique characters as keys and their respective frequencies as
    * the associated values. ex: text = "aaabbc", frequencies = {'a': 3, 'b': 2, 'c': 1}
    */
   private void populateFrequenciesMap() {
      
       frequencies = new HashMap<>();


       // TODO
       // Loop over every char in the text String by using text.toCharArray().
       // If the char does not exist as a key in the frequencies HashMap, create a new entry and set the value to 1.
       // If the char already exists in the frequencies HashMap, increase its current value by 1.
       for (char c : text.toCharArray()) {
           frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
       }
   }


   /*
    * The encode() method builds the Huffman Tree using a PriorityQueue and returns the encoded text.
    */
   public String encode() {
       Queue<Node> queue = new PriorityQueue<>();


       // TODO
       // For every entry in the frequencies HashMap, create a new Leaf and add it to the queue.
       for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
           queue.add(new Leaf(entry.getKey(), entry.getValue()));
       }
       // TODO
       // While the queue's size is greater than 1, create a new Node using the two Nodes with lowest frequencies.
       // HINT: use queue.poll() to get the nodes with lowest frequncies.
       while (queue.size() > 1) {
           Node left = queue.poll();
           Node right = queue.poll();
           queue.add(new Node(left, right));
       }
       // TODO
       // Set the root of the tree equal to the final Node in the queue (the greatest, by definition).
       root = queue.poll();
       // TODO
       // Call generateCodes() to populate the codes HashMap by providing the root Node and an empty String
       // for the initial code value.
       generateCodes(root, "");


       return getEncodedText();
   }


   /*
    * generateCodes() populates the codes HashMap given a starting root Node and a code String.
    * The codes HashMap maps characters to Strings which represent edge paths.
    * ex: {'a': "0", 'b': "11", 'c': "101"}
    */
   private void generateCodes(Node node, String code) {


       // TODO
       // If the Node is an instance of Leaf, add the Leaf's character as a key in the codes HashMap, the
       // code String as its value, and return. 
       if (node instanceof Leaf) {
           codes.put(((Leaf) node).character, code);
           return;
       }
       // TODO
       // Recursively call generateCodes with the left and right Nodes. Add a "0" to the left Node code
       // and a "1" to the right Node code when making recursive calls.
       generateCodes((Node) node.getLeftNode(), code + "0");
       generateCodes((Node) node.getRightNode(), code + "1");
   }


   /*
    * getEncodedText() returns the Huffman Encoding of the original text.
    */
   private String getEncodedText() {
       StringBuilder builder = new StringBuilder();


       // TODO
       // For every char in the original String text, use the char as a key to obtain a Huffman code from
       // the codes HashMap. Use builder.append() to add the Huffman code to the result String.
      
       for (char c : text.toCharArray()) {
           builder.append(codes.get(c));
       }
       return builder.toString();
   }


  /*
   * The decode() method accepts a huffman-encoded String and returns a plaintext String.
   */
   public String decode(String encoded) {
     
       StringBuilder builder = new StringBuilder();


        /*
        * TODO
        * Start by creating a variable of type Node to keep track of the current Node.
        * The initial starting value should be root. (ex: Node current = this.root;)
        * For every char in the encoded String ...
        *    - If the character is '0' navigate left by pointing the current Node to current's left Node.
        *    - Otherwise, navigate right by pointing the current Node to the current's right Node.
        *    - If the current Node is a Leaf, append the character to the StringBuilder and reset current Node to root.
        */
       Node current = this.root;


       for (char bit : encoded.toCharArray()) {
           current = (bit == '0') ? (Node) current.getLeftNode() : (Node) current.getRightNode();
           if (current instanceof Leaf) {
               builder.append(((Leaf) current).character);
               current = root;
           }
       }
       return builder.toString();
   }


   // printCodes() displays the current Huffman Code value for each character in the codes HashMap.
   public void printCodes() {
       codes.forEach((character, code) ->
               System.out.println(character + ": " + code)
       );
   }
}
