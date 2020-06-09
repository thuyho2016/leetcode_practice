package linkedList;

public class Node {
    public int val;       // data stored in this node is integer type
    public Node next;  // link to next node in the list

    // post: constructs a node with data 0 and null link
    public Node() {
        this(0, null);
    }

    // post: constructs a node with given data and null link
    public Node(int x) {
        val = x;
    }

    // post: constructs a node with given data and given link
    public Node(int x, Node next) {
        this.val = x;
        this.next = next;
    }
}

