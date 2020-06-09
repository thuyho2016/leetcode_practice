package binarytree;

import binarytree.TreeNode;

/* 
 * 404. Sum of Left Leaves
 * https://leetcode.com/problems/sum-of-left-leaves/
 * Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

* 1.1 Time Complexity is O(logn)
*  1.2 Space Complexity is O(1)
*  preorder traversal
*/

public class SumLeftLeaves{
	
	public static int sumOfLeftLeaves(TreeNode root) {
		int sum=0;  
		 // if root is the only node
		if(root==null) 
			return 0;
		
		//check if it is the left node is not null and check if the left or right is null
		if(root.left!= null && root.left.left == null && root.left.right == null)
			sum += root.left.val; 
		
		// if left node has right child and left child
       sum += sumOfLeftLeaves(root.left);
       
       // if right node has right child and left child
       sum += sumOfLeftLeaves(root.right);
       
       return sum;
    }   
	
	public static void main(String args[])
    {  
		TreeNode  root = new TreeNode(3); 
         root.left = new TreeNode(9); 
         root.right = new TreeNode(20); 
         root.right.left = new TreeNode(15); 
         root.right.right = new TreeNode(7); 
         
         System.out.println("Sum Left leaves: " + sumOfLeftLeaves(root)); //24
    }

}
	
	