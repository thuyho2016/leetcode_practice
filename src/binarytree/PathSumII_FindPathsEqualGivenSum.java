


package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
113. Path Sum II
https://leetcode.com/problems/path-sum-ii/

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

 */


public class PathSumII_FindPathsEqualGivenSum {
 
	//Prefer this solution - DFS in preOrder  - see PrintBT_ByDFS.java
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();  //create a ArrayList to represent the paths

        findPaths(root, sum, new ArrayList<Integer>(), paths);// new ArrayList<Integer>() to keep track what current path is
        return paths;
	}
	
	// Every time I find a curent path, I need to add to List of List path.
	// Recursive for left-subtree and right-subtree
	
	private static void findPaths(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> paths) {
		
		if (root == null) return;
			
		currentPath.add(root.val); // add current value to currentPath
		
		//check if value of sum equals sum and node has no children. I mean node is leaf
		if (root.val == sum && root.left == null && root.right == null ) {
			paths.add(currentPath);
		}
		
		//I need to continue traverse the tree , so I do recursive call with remaining value in left subtree and right subtree
		findPaths(root.left, sum - root.val, new ArrayList<Integer>(currentPath), paths);
		findPaths(root.right, sum - root.val, new ArrayList<Integer>(currentPath), paths);
	}
        
   

    public static void main(String[] args) {

		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		
		// left subtree
		root.left.left = new TreeNode(11); 
		root.left.left.left = new TreeNode(7); 
		root.left.left.right = new TreeNode(2); 
		
		//right subtree
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		
		
		System.out.println("Sum paths: " +  pathSum(root, 22)); // [[5,4,11,2], [5,8,4,5]]
		
    }
}