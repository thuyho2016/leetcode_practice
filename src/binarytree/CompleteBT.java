package binarytree;

import java.util.LinkedList;
import java.util.Queue;
import binarytree.TreeNode;

/* 
* 958. Check Completeness of a Binary Tree
https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

 
Example 1:
			1
		  /	   \
		 2		3
		/ \     /
	   4   5   6			

Input: [1,2,3,4,5,6]

Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), 
and all nodes in the last level ({4, 5, 6}) are as far left as possible.


Example 2:

Input: [1,2,3,4,5,null,7]
	 		1
		  /	   \
		 2		3
		/ \      \
	   4   5	  7
	   
Output: false
Explanation: The node with value 7 isn't as far left as possible.
   
Time and Space complexity is O(n)
 
*/

public class CompleteBT{
	/**
	  BFS to do level order travesral) -Full Node: node that has left and right child
	  As according to complete binary tree there should be no null nodes in between any level. so what we really will do is to check the first appearance of that node 
	  any child of whose is null, and then after that node if any node occurs whose child is non null then we will return false.
	*/
	public static boolean isCompleteTree(TreeNode root) {
	
		if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        
        // set Flag to be true when a non-full treenode is seen
        boolean isNullFound = false;
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //remove
            
            if (node.left != null) {
                if (isNullFound) 
                	return false;
                else 
                	queue.add(node.left);
            }
            else { //node.left == null
            	isNullFound = true;
            }
            if (node.right != null) {
                if (isNullFound) 
                	return false;
                else 
                	queue.add(node.right);
            } else  //temp.right == null
            	isNullFound = true;
        }
        
        return true;
	}
	
	/** DFS - the sum of all indexes should be equal to 1 + 2 + .... + num of nodes
	Algorithm

	1. count number of nodes in the tree.
	2. calculate value of node with max value where value of a node is defined as follows -
		a. if node is root, it's value is 1.
		b. if node's value is x, then left child has value 2x and right child will have value 2x + 1
		c. calculate value of each node recursively and calculate max value in the tree.
		d. if max value of tree is equal to number of nodes in the tree then tree is complete else not.
	 */
	
	public static boolean isCompleteTree2(TreeNode root) {
		return (count(root) == calculateMax(root, 1) ); //if max value of tree is equal to number of nodes , then tree is completed		
	}
	
	//count number of nodes
	private static int count(TreeNode root) {
		return root == null ? 0 : 1 + count(root.left) + count(root.right);
	}
	
	private static int calculateMax(TreeNode root, int value) {
	//	return root == null ? 0 : Math.max(value,Math.max(calculateMax(root.left, 2 * value),calculateMax(root.right, 2 * value + 1)));
		
		if (root == null) {
            return 0;
        } else {
        	//calculate value of left node and right node recursively
        	int left_max = calculateMax(root.left, 2 * value);
        	int right_max = calculateMax(root.right, 2 * value + 1);
        	
        	return Math.max(value, Math.max(left_max, right_max)); //calculate max value
        }
	}
	 
    
	public static void main(String args[])
    {  
		TreeNode  root = new TreeNode(1); 
         root.left = new TreeNode(2); 
         root.right = new TreeNode(3); 
         
         //left subtree
         root.left.left = new TreeNode(4); //2 ->4
         root.left.right = new TreeNode(5); 
         
         //right subtree
         root.right.left = new TreeNode(6); //3->6
         
         System.out.println(isCompleteTree(root)); //true
         System.out.println(isCompleteTree2(root)); //true
         
         TreeNode  root2 = new TreeNode(1); 
         root2.left = new TreeNode(2); 
         root2.right = new TreeNode(3); 
         
         //left subtree
         root2.left.left = new TreeNode(4); //2 ->4
         root2.left.right = new TreeNode(5); 
         
         //right subtree
         root2.right.right = new TreeNode(7); //3->7
         
         System.out.println(isCompleteTree(root2)); //false
         System.out.println(isCompleteTree2(root2)); //false
    }

}
	
	