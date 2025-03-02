public class Leaf extends Node {
    private final char character;

    /*
     * TODO
     * The Leaf class should have a constructor which accepts a char character and an int frequency.
     * The Leaf constructor should use keyword super to call the parent Node class constructor
     * providing a frequency and set the provided character to the private, final instance member.
     * 
     * Lastly, the Leaf class should implement a getCharacter() getter method.
     */

    public Leaf(char character, int frequency) {
        super(frequency);
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }
}
