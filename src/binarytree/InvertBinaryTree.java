package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 * 226. Invert Binary Tree  ( easy level)
 * Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Solution 1 : using recursive

time complexity is O(n)O(n), where n is the number of nodes in the tree
 if h is the height of the tree, so h∈O(n), the space complexity is O(n)
 
 */
public class InvertBinaryTree {
 
	public static TreeNode invertTree(TreeNode root) {
        
		if(root == null) return null;
		
		TreeNode right = invertTree(root.right);
		TreeNode left = invertTree(root.left);
		
		root.left = right; //revert
		root.right = left;
		return root;
    }

    public static void main(String[] args) {
    	//Input: [[4,2,7,1,3,6,9]
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		// left subtree
		root.left.left = new TreeNode(1); 
		root.left.right = new TreeNode(3); //2 -> 5
		//right subtree
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		System.out.println("List strings: " +  invertTree(root));
      
    }
}