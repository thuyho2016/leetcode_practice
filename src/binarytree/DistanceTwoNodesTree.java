package binarytree;

import binarytree.TreeNode;


/*
Problem 1:
Write a function that given a BST, it will return the distance (number of edges) between 2 nodes.

For example, given this tree

         A
        / \
       B   C
      / \   \
     D   E   F
    /         \
   G           H
The distance between G and E is 3: [G -> D -> B -> E]

The distance between G and H is 6: [G -> D -> B -> A -> C -> F -> H]
 
        5
    10          15
 20   25     30   35
          45
 
 Find distance between 45 and 20 is 3
 
Solution 1: See ShortestDistanceTwoNodesInBT.java
 My solution is to first find LCA of BST, and then find the distance from LCA node to both n1 and n2, and return the sum of its distance
 
 Solution 2: 
  Dist(a,b) = Dist(root, x) + Dist(root,y) - 2 * Dist(root to lca( x,y)
  
Distance(root, 20) = 2
Distance(root, 45) = 3
LCA(20, 45) = 10
Distance(root, 10) = 1
Distance(20,45) = 2 + 3 – 2*1 = 3

 
 */

public class DistanceTwoNodesTree {
	
		
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

	
	