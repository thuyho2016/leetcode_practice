
package binarytree;

import binarytree.TreeNode;

/* 
* 101. Symmetric Tree
* https://leetcode.com/problems/symmetric-tree/

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
   
   Time complexity is O(n)
*/

public class SymmetricTree{
	
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) return false;
      
		//when root is not null, I need to check left subtree and right subtree by making a recursive call.
		return isSystemtricTree(root.left, root.right);
    }
	
	//check left subtree and right subtree
	private static boolean isSystemtricTree(TreeNode left, TreeNode right) {
		if (left == null || right == null) return left ==right; // either left subtree or right subtree is null
		
		//compare value of child root in the left and right. If they are NOT same value, return false , not symmetric
		if (left.val != right.val) return false;
		
		//I need recursive call to check left subtree and right subtree
		//and check if left subtree's right child is the samw with rightsubtree 's left child
		// check if left subtree's left child is the same with rightsubtree 's right child
		return (isSystemtricTree(left.left, right.right) 
				&& isSystemtricTree(left.right, right.left));
	}
	
	public static void main(String args[])
    {  
		TreeNode  root = new TreeNode(1); 
         root.left = new TreeNode(2); 
         root.right = new TreeNode(2); 
         
         //left subtree
         root.left.left = new TreeNode(3); 
         root.left.right = new TreeNode(4); 
         
         //right subtree
         root.right.left = new TreeNode(4); 
         root.right.right = new TreeNode(3); 
         
         System.out.println(isSymmetric(root)); //true
         
         TreeNode  root2 = new TreeNode(1); 
         root2.left = new TreeNode(2); 
         root2.right = new TreeNode(2); 
         
         root.left.right = new TreeNode(3);
         root.right.right = new TreeNode(3); 
         System.out.println(isSymmetric(root)); //false
    }

}
	
	