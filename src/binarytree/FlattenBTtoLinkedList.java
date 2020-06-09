package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/* 114. Flatten Binary Tree to Linked List
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

Draw ----

    1
   / \
  2   5
 / \   \
3   4   6
-----------        
pre = 5
cur = 4

    1
   / 
  2   
 / \   
3   4
     \
      5
       \
        6
-----------        
pre = 4
cur = 3

    1
   / 
  2   
 /   
3 
 \
  4
   \
    5
     \
      6
-----------        
cur = 2
pre = 3

    1
   / 
  2   
   \
    3 
     \
      4
       \
        5
         \
          6
-----------        
cur = 1
pre = 2

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

          
Solution: recursive
          
Basically, the traversing order after flattening is pre-order traversal in (root, left, right)

I need to find the current node's next node. 
- use TreeNode prev to hold the next node 
 
 After flatten(root.right), we have processed the right branch of the current node, and the current prev is the head of root of the right branch
 
 O(n) time, O(1) space.

 */

public class FlattenBTtoLinkedList {
	private static TreeNode prev = null;  //uses a global variable to maintain the next node.
    //recursive -  the traversal order of the code is right->left->cur,
	public static void flatten(TreeNode root) {
	    if (root == null)
	        return;
	    flatten(root.right);//processed the right branch of the current node, and the current prev is the head of root of the right branch
	    flatten(root.left);
	    
	    root.right = prev; // set rightof the current node as prev
	    root.left = null;
	    
	    prev = root; //Set current node to be the next ‘previous’
	}
	
	/**accepted - non-recursive - Morris Traversal
	Flattening of a tree basically involves three steps:

	1.Set present right sub-tree as the right sub-tree of the last visited node (prev) on the left.
	2.Set present left sub-tree as the new right sub-tree.
	3.Set left sub-tree to NULL.
	*/
	public static void flatten2(TreeNode root) {
        if(root == null) return;
     
        while(root != null){
        	if (root.left != null) {
        		
  	            TreeNode temp = root.left;
	            while(temp.right != null) {
	            	temp = temp.right; 
	            }
	            
	            temp.right = root.right;
	            root.right = root.left;  // current node's left subtree to replace its right subtree
	            root.left = null;
	        }
        	root = root.right;   // dont forget this!! 
        }
    }
	
	public static void print_levelOrderTraversal(TreeNode root) {
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
	
		while(!queue.isEmpty()){ // the queue should has at least one node
	
			//Retrieves and removes the head of this queue. Returns null if this queue is empty.	        
			TreeNode current = queue.poll(); // or queue.remove();  remove() throws an exception if queue is empty
			System.out.print(current.val + " ");
	
			// put children to the queue
			if (current.left!=null)
				queue.add(current.left);
			if (current.right!=null) 
				queue.add(current.right);  //2->3->...
		}

	}	
	
	public static void main(String[] args) {
	    	
		//1,2,5,3,4, null,6
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		
		// left subtree
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4); //2 -> 4		
	
		
		//right subtree	
		root.right.right= new TreeNode(6);
		
		flatten2(root);
		
		print_levelOrderTraversal(root);

	}
}
