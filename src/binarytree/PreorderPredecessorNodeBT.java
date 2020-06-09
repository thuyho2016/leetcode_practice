package binarytree;

/*
 * Given a binary tree and a node in the binary tree, find Preorder predecessor of the given node.
Examples:

Consider the following binary tree
              20            
           /      \         
          10       26       
         /  \     /   \     
       4     18  24    27   
            /  \
           14   19
          /  \
         13  15
Input :  4
Output : 10

Preorder traversal of given tree is 20, 10, 4, 18, 14, 13, 15, 19, 26, 24, 27.

Input :  19
Output : 15

Solution:

Time Complexity : O(n)
Auxiliary Space : O(n)

An efficient solution is based on below observations.

If the given node is root, then return NULL as preorder predecessor.
If node is the left child of its parent or left child of parent is NULL, then return parent as its preorder predecessor.
If node is the right child of its parent and left child of parent exists, then predecessor would be the rightmost node (max value) of the left subtree of parent.
If node is the right child of its parent and the parent has no left child, then predecessor would be the parent node (max value).
 */

public class PreorderPredecessorNodeBT {
	
	static class Node {  
	    Node left, right, parent;  	   
	    int value;  
	}
	
	// Utility function to create a new node with  
	// given value.  
	static Node newNode(int value)  
	{  
	    Node temp = new Node();  
	    temp.left = null; 
	    temp.right = null; 
	    temp.parent = null;  
	    temp.value = value;  
	    return temp;  
	} 
	
	static Node preorderPredecessor(Node root, Node n)  
	{  
	    // Root has no predecessor in preorder  
	    // traversal  
	    if (n == root)  
	        return null;  
	  
	    // If given node is left child of its  
	    // parent or parent's left is empty, then  
	    // parent is Preorder Predecessor.  
	    Node parent = n.parent;  
	    if (parent.left == null || parent.left == n)  
	        return parent;  
	  
	    // In all other cases, find the rightmost  
	    // child in left substree of parent.  
	    Node curr = parent.left;  
	    while (curr.right != null)  
	        curr = curr.right;  
	  
	    return curr;  
	} 
	
	public static void main(String[] args)  
	{  
	    Node root = newNode(20);  
	    root.parent = null;  
	    root.left = newNode(10);  
	    root.left.parent = root;  
	    root.left.left = newNode(4); 
	    root.left.left.parent = root.left;  
	    root.left.right = newNode(18);  
	    root.left.right.parent = root.left;  
	    root.right = newNode(26);  
	    root.right.parent = root;  
	    root.right.left = newNode(24);  
	    root.right.left.parent = root.right;  
	    root.right.right = newNode(27);  
	    root.right.right.parent = root.right;  
	    root.left.right.left = newNode(14);  
	    root.left.right.left.parent = root.left.right;  
	    root.left.right.left.left = newNode(13);  
	    root.left.right.left.left.parent = root.left.right.left;  
	    root.left.right.left.right = newNode(15);  
	    root.left.right.left.right.parent = root.left.right.left;  
	    root.left.right.right = newNode(19);  
	    root.left.right.right.parent = root.left.right;  
	  
	    Node res = preorderPredecessor(root, root.left.right.right);  
	    if (res != null)  
	        System.out.println("Preorder predecessor of " + root.left.right.right.value + " is " + res.value);      
	    else
	        System.out.println("Preorder predecessor of " + root.left.right.right.value + " is null");  
	  
	}
	
}
