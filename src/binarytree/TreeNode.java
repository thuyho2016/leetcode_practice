package binarytree;

/*
 * Definition for a binary tree node.
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key
 */
public class TreeNode
{
	  public int val;
	  public TreeNode left;
	  public TreeNode right;
	  
	  public TreeNode(int x) {
		  val = x;
		  left = null;
		  right = null;
	  }
}