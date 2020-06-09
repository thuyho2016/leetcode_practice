package binarytree;
import java.util.ArrayList;
import java.util.List;

/*
 * 366. Find Leaves of Binary Tree
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []   

Time complexity is O(n), where n is the number of nodes in the tree
If h is the height of the tree, so h∈O(n), the space complexity is O(n)
 
 */


public class LeavesOfBT {
 

	public static List<List<Integer>> findLeaves(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();  //create a ArrayList to hold nodes
        
        while ( root != null) {
        	
        	ArrayList<Integer> leaves = new ArrayList<>(); //[]
        	result.add(leaves); //[[]],[[4, 5, 3]]; [[4, 5, 3], [2]]; [[4, 5, 3], [2], []]
        	
        	boolean collect = collectLeaves(root, leaves); // ...;root = 2, no children; root = 1, not children
        	if (collect == true) root = null;
        }
       
        return result;
	} 
	
	public static boolean collectLeaves(TreeNode root, List<Integer> leaves) {
		if (root == null) return true;
		
		if (root != null & root.left == null && root.right == null ) {  // nodes (4, 5, 3) don't have children, add node to list
			leaves.add(root.val); //leaves = [4];[4,5]; [4,5,3]; [2]; [1]
			return true;
		} 
		// root.left != null && root.right != null) {
		
		//make recursive call to keep going to left childs. 
		if (collectLeaves(root.left, leaves) == true) root.left = null;
		
		//make recursive call to keep going to right childs
		if (collectLeaves(root.right, leaves) == true) root.right = null;  
				
		return false;
	}
          
    public static void main(String[] args) {
    	//Input: []
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// left subtree
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.left = new TreeNode(4); // 2->4
		
		List<List<Integer>> res = findLeaves(root);
		System.out.println(res); //[[4,5,3],[2],[1]]
      

		
    }
}