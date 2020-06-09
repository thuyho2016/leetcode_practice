
package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 1110. Delete Nodes And Return Forest
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 
Example 1:   
        1
     2	   3
   4  5   6  7

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

 Time complexity: O(n)
 Space cpmplexity: O(n)
 */


public class DeleteNodesAndReturnForest {
 
	//recursive - 
	public static List<TreeNode> deleteNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> result = new ArrayList<>(); // nodes remaining to be returned
		
		Set<Integer> toDelete = new HashSet<>(); // to determinge node should remove or not
		
		for (int i: to_delete ) {  // add elements of to_delete array to  HashSet
			toDelete.add(i);
		}
		
		//call recursive function - need 3 things - reference of tree, where node to be deleted, remaining nodes
		removeNodes(root, toDelete, result);
		
		if (!toDelete.contains(root.val)) {
			result.add(root);
		}
		return result;
		
	}
	
	//bottom - up
	public static TreeNode removeNodes(TreeNode root, Set<Integer> toDelete, List<TreeNode> remainning) {
		if (root == null) return null;
		
		root.left = removeNodes(root.left, toDelete, remainning);
		root.right = removeNodes(root.right, toDelete, remainning);
		
		if (toDelete.contains(root.val)) {
			if (root.left != null) {
				remainning.add(root.left);
			}
			if (root.right != null) {
				remainning.add(root.right);
			}
			return null;
		}
		return root;
	}
 
    
    public static List<Integer> printTree_preorderTraversal(List<TreeNode> list) {
        List<Integer> res = new ArrayList<Integer>();
        for (TreeNode root: list) {
			System.out.println("After delete: " + root.val);
			 dfs_preorder(root, res);
		}
       
        return res;
    }
    
    public static void dfs_preorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	
		result.add(root.val);
    	        
        if (root.left != null) dfs_preorder(root.left, result); // visit all nodes of left subtree
        
        if (root.right != null) dfs_preorder(root.right, result);  // visit all nodes of right subtree     
       
    }

    public static void main(String[] args) {
    	//Input: []
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// left subtree
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.left = new TreeNode(4);
		
		//right subtree
		root.right.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		
		int[] to_delete = {3,5};
		List<TreeNode> remains = deleteNodes(root, to_delete ); //[[1,2,null,4],[6],[7]]
		System.out.println("BinaryTree in DFS PreOrder: " + printTree_preorderTraversal(remains));
		
    }
}