package binarytree;

import binarytree.TreeNode;

/* 863. All Nodes Distance K in Binary Tree
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

          3
        /  \
       5    1
      / \   | \
     6   2  0  8
    	/ \        
   	   7   4        

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 
 */

public class DistanceK_FindAllNodes {
	
		
	public static int findDistance(TreeNode root, int n1, int n2) {
		int x = distance(root, n1) - 1;
		int y = distance(root, n2) - 1;
		
		TreeNode lca = findLCA(root, n1, n2);
		int lcaVal = lca.val;
		
		int lcaDistance = distance(root, lcaVal) - 1;  // distance from root to LCA
		return (x + y) - 2 * lcaDistance;
	}

	public static int distance(TreeNode root, int n1) {
		if (root != null) {
			int x = 0;
			if ((root.val == n1) || (x = distance(root.left, n1)) > 0 || (x = distance(root.right, n1)) > 0) {
				// System.out.println(root.val);
				return x + 1;
			}
			return 0;
		}
		return 0;
	}

	public static TreeNode findLCA(TreeNode root, int n1, int n2) {
		if (root != null) {
			if (root.val == n1 || root.val == n2) {
				return root;
			}
			
			TreeNode left = findLCA(root.left, n1, n2);
			TreeNode right = findLCA(root.right, n1, n2);

			// 4 cases
			if (left != null && right != null) {
				return root;
			}
			if (left != null) {
				return left;
			}
			if (right != null) {
				return right;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(25);
		root.right.left = new TreeNode(30);
		root.right.right = new TreeNode(35);
		root.left.right.right = new TreeNode(45);
		
		System.out.println("Distance between (45 , 20) = " + findDistance(root, 45, 20));
	}
	       
	

}

	
	