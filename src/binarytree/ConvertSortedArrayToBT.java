package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * 108. Convert Sorted Array to Binary Search Tree ( Easy level)
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5


Solution:

given node of the binary search tree, it's value must be ≥ the value of all the nodes in the left subtree 
and =< the value of all the nodes in the right subtree.

the middle element of the given list would form the root of the binary search tree. 
All the elements to the left of the middle element would form the left subtree recursively. 
Similarly, all the elements to the right of the middle element will form the right subtree of the binary search tree. 
This would ensure the height balance required in the resulting binary search tree.

[-10,-3,0,5,9] , 0 is root , -10, -3 are in left subtree , 5,9 are in right subtree

Algorithm

Convert the given linked list into an array. Let's call the beginning and the end of the array as left and right
Find the middle element as (left + right) / 2. Let's call this element as mid. This is a O(1) time operation and is the only major improvement over the previous algorithm.
The middle element forms the root of the BST.
Recursively form binary search trees on the two halves of the array represented by (left, mid - 1) and (mid + 1, right) respectively.
   
 */
public class ConvertSortedArrayToBT {
	
    
	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) return null;		
        
        return constructBT(nums, 0, nums.length - 1);
    }
     
	public static TreeNode constructBT(int[] nums, int left, int right) {
		if (left > right) return null;
		
		int mid = left + (right - left) / 2;
		
		TreeNode newNode = new TreeNode(nums[mid]);
		
		//left subtree and make left child as root
		newNode.left = constructBT(nums, left, mid -1);
		//right subtree and make right child as root
		newNode.right = constructBT(nums, mid + 1, right);
		
		return newNode;
	}
	
	//display BT using DFS inorder, left subtree -> root -> right subtree.   
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs_inorder(root, res);
        return res;
    }
    
    public static void dfs_inorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	        
        if (root.left != null) dfs_inorder(root.left, result); // visit all nodes of left subtree
        
        result.add(root.val);
        
        if (root.right != null) dfs_inorder(root.right, result);  // visit all nodes of right subtree     
       
    }
    
    public static void main (String[] agrs) {
  	  int[] input = {4,2,3,1,5};
  	 // int[] input2 = {-10,-3,0,5,9};

  	  TreeNode tree =  sortedArrayToBST(input);
  	  System.out.println("deserialize array to tree: " +  tree);  
  	  System.out.println("print BinaryTree in DFS PreOrder: " +  preorderTraversal(tree));  // [-10, -3, 0, 5, 9]
   
    }
}
