package binarytree;

import java.util.ArrayList;
import java.util.List;

/* 700. Search in a Binary Search Tree
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * 
 * Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. 
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example, 

Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the value to search: 2

You should return this subtree: [2, 1,3]

      2     
     / \   
    1   3
    
 */

public class SearchNode_ReturnSubtreeOfNodeInBT {
	
	//use Reecursive
	public static TreeNode searchBST(TreeNode root, int val) {
	
		TreeNode found = null;
		
		if (root != null) {
			// check to see value of root match
			if (root.val == val) {
				return root;
			}
			
			if ( val < root.val) { // search node in left subtree
				found = searchBST(root.left,  val);
			} else {
				found = searchBST(root.right, val);  // search node in right subtree
			}
			
			//can write TreeNode found = (val < root.val) ? searchBST(root.left,val) : searchBST(root.right,val);
		}
		return found;
	}
	
	 public static List<Integer> printTree_preorder(TreeNode root) {
		 List<Integer> res = new  ArrayList<Integer>();
		 dfs_preorder(root, res);
		 return res;
	 }
	 
	 //print pre-order Root - Left - Right 
	 public static void dfs_preorder(TreeNode root, List<Integer> result) {
		 if (root != null) {
			 result.add(root.val);
			 if (root.left != null) {
				 dfs_preorder(root.left, result); // visit all nodes of left subtree
			 }
			 if (root.right != null) {
				 dfs_preorder(root.right, result); // visit all nodes of right subtree
			 }
		 }
	 }
	
	public static void main (String[] agrs) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1); //2 -> 1
		root.left.right = new TreeNode(3);

		System.out.println(printTree_preorder(root));
		
		TreeNode subtree = searchBST(root, 2);
		
		// print Inorder Root - Left - Right 
		System.out.println("Subtree of node 2: " + printTree_preorder(subtree));
	}
}
