
package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 938. Range Sum of BST  ( easy level)
https://leetcode.com/problems/range-sum-of-bst/

Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32


     10
   	/  \
   5    15
 /  \     \
3    7     18
   
Add 10, 15, 7 because they are in range Left bound = 7 and Right bound = 15, so sum = 10 + 15 + 7 = 32   

Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23


Time Complexity: O(N), where N is the number of nodes in the tree.

Space Complexity: O(H), where H is the height of the tree.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 */

public class RangeSumBST {		
	int sum = 0;
	//DFS - recursive to traverse tree
	public int rangeSumBST_DFS(TreeNode root, int L, int R) {		
		if (root == null) {
			return 0;
		}
		dfs(root, L, R);
	    return sum;
	}
	
	private void dfs(TreeNode node, int L, int R) { 
		if (node !=null) {
			if ( node.val >= L && node.val <= R) {
				sum += node.val;
			}
			if (node.left !=null && L < node.val) {  // left subtree
				dfs (node.left, L, R);
			} 
			if (node.right != null && node.val < R) {
				dfs(node.right, L, R) ;
			}
		}
	}
		
	//BFS to traverse tree
	public static int rangeSumBST_BFS(TreeNode root, int L, int R) {
		int sum = 0;
		if (root == null) {
			return sum;
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		
		while (!q.isEmpty()) {
			//take first node from queue
			TreeNode curNode = q.remove();
			if ( curNode.val >= L && curNode.val <= R) {
				sum += curNode.val;
			}
			if (curNode.left != null && curNode.val > L) {  // left subtree
				q.add(curNode.left);
			} 
			if (curNode.right != null && curNode.val < R) {
				q.add(curNode.right);
			} 
		}
		return sum;
	    
	}
	
	public static void main(String[] args) {
		RangeSumBST obj = new RangeSumBST();
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);		
		
		//left subtree
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);	
		
		//right subtree
		root.right.right = new TreeNode(18); // 15->18
		
		System.out.println(obj.rangeSumBST_DFS(root, 7, 15)); //23
		
		System.out.println(rangeSumBST_BFS(root, 7, 15)); //23
				
    }
}