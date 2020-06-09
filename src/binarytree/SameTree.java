
package binarytree;

/*
 * 100. Same Tree
 https://leetcode.com/problems/same-tree/
 
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false

Time complexity : O(N), where N is a number of nodes in the tree, since one visits each node exactly once.
Space complexity :O(log(N)) in the best case of completely balanced tree and O(N) in the worst case of completely unbalanced tree 
 */


public class SameTree {
	
	// recursive to go/traverse each node
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		// p and q are both null
		if (p == null && q == null) return true;
		
		// one of p and q is null
		if (p == null || q == null) return false;
		
		//compare root between 2 trees
		 if (p.val != q.val) return false;
		 
		 //compare left node, right node of 2 trees
		 return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
	     
    }
	
	//     1
    //  2     3 
     
	public static void main (String[] agrs) {
		//tree1: [1,2,3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
			
		//tree 2
		TreeNode s = new TreeNode(1);
		s.left = new TreeNode(2);
		s.right = new TreeNode(3);
		
		System.out.println("same tree?: " + isSameTree(root, s)); //true
				
	}
}


