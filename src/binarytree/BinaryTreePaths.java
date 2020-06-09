

package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 * 257. Binary Tree Paths (easy level)
 * https://leetcode.com/problems/binary-tree-paths/
 
 Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

Time complexity is O(n), where n is the number of nodes in the tree
If h is the height of the tree, so h∈O(n), the space complexity is O(n)
 
 */


public class BinaryTreePaths {
 
	//Prefer this solution - DFS in preOrder  - see PrintBT_ByDFS.java
	public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();  //create a ArrayList to hold nodes
        String path = "";
        pathHelper(root, path, result); //dfs
        return result;
	} 
	
	private static void pathHelper(TreeNode root, String path, List<String> result) {
		
		if (root == null) return;
		
		if (root.left == null && root.right == null ) {
			path += root.val;
			result.add(path);
			return;
		}
		
		//recursive left subtree and right subtree
		pathHelper(root.left, path + root.val + "->", result);
		pathHelper(root.right, path + root.val + "->", result);
	}
        
    //Solution 2: use recursive and StringBuilder    
	public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        
        helper(root, s, result);
        return result;
    }

    public static void helper(TreeNode root, StringBuilder sb, List<String> result)
    {
        if(root == null) return;
        
        if(root.left == null && root.right==null)
        {   
        	sb.append(root.val);
            result.add(sb.toString());
            return;
        }
        
        sb.append(root.val + "->"); //sb.append(root.val); sb.append("->");
        
        helper(root.left, new StringBuilder(sb) , result);
        helper(root.right, new StringBuilder(sb) ,result);
    } 
    

    public static void main(String[] args) {
    	//Input: []
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// left subtree
		root.left.right = new TreeNode(5); //2 -> 5
		
		
		System.out.println("List strings: " +  binaryTreePaths(root)); //[1->2->5, 1->3]
      

		System.out.println("List strings: " +  binaryTreePaths2(root)); //[1->2->5, 1->3]
		
    }
}