package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

/*
 * 173. Binary Search Tree Iterator
https://leetcode.com/problems/binary-search-tree-iterator/

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

Time complexity: O(1) and spaceO(1) for both hasNext() and next()
 */


public class BinaryTreeIterator {		
	
    /**
     * Algorithm
	Initialize an empty array that will contain the nodes of the binary search tree in the sorted order.
	We traverse the binary search tree in the in-order fashion and for each node that we process, we add it to our array nodes. 
	Note that before processing a node, its left subtree has to be processed (or recursed upon) and after processing a node, its right subtree has to be recursed upon.
	
	Once we have all the nodes in an array, we simply need a pointer or an index in that array to implement the two functions next and hasNext.
	Whenever there's a call to hasNext, we simply check if the index has reached the end of the array or not.
	For the call to next function, we simply return the element pointed by the index. 
	Also, after a the next function call is made, we have to move the index one step forward to simulate the progress of our iterator.
     
    Time & space complexity : O(N)
     */


    ArrayList<Integer> nodesSorted; 
    int index;

    // or use Queuwe
    Queue<Integer> queue;  
    
    public BinaryTreeIterator(TreeNode root) {

        // Array containing all the nodes in the sorted order
        nodesSorted = new ArrayList<Integer>(); 
        
        // Pointer to the next smallest element in the BST
        index = -1;
        
        // Call to flatten the input binary search tree
        inorder(root);
        
       // queue = new LinkedList<>();
       // inOrderTraversal(queue, root); no need index when using Queue
    }

    private void inorder(TreeNode root) {

        if (root == null) {
            return;
        }

        inorder(root.left);
        nodesSorted.add(root.val);
        inorder(root.right);
        
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return nodesSorted.get(++index);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return index + 1 < nodesSorted.size();   // return !(queue.isEmpty();
    }
    
    //Below is Approach 2: travel from Left - Root - Right
    private void inOrderTraversal(Queue<Integer> queue, TreeNode root){
        if(root == null){
            return;
        }

        inOrderTraversal(queue,root.left);
        queue.add(root.val);
        inOrderTraversal(queue, root.right);
    }
    
    public int next2(){
        int nextItem = queue.remove();
        return nextItem;
    }
   
    public boolean hasNext2() {
        return !(queue.isEmpty());
    }

	
	public static void main(String[] args) {
    	
		TreeNode root = new TreeNode(7);
		root.left = new TreeNode(3);
		root.right = new TreeNode(15);		
		
		//right subtree
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(20);	
		
		BinaryTreeIterator obj = new BinaryTreeIterator(root);
		int param1 = obj.next();
		System.out.println(param1); //3
		
		System.out.println(obj.next());     //7
		System.out.println(obj.hasNext());  //true
		System.out.println(obj.next());     //9
		System.out.println(obj.hasNext());  //true
		System.out.println(obj.next());    //15    		
    }
}