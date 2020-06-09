package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 450. Delete Node in a BST
https://leetcode.com/problems/delete-node-in-a-bst/
 
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

1. Search for a node to remove. 
2. If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3
   
    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

 
 
Steps:

Recursively find the node that has the same value as the key, while setting the left/right nodes equal to the returned subtree
Once the node is found, have to handle the below 4 cases
1. node doesn't have left or right - return null
2. node only has left subtree - return the left subtree
3. node only has right subtree - return the right subtree
4. node has both left and right - find the minimum value in the right subtree, set that value to the currently found node, 
                                  then recursively delete the minimum value in the right subtree

 Time complexity: : O(logN)
 Space complexity: O(H)  where H is a tree height
 
*/


public class DeleteNodeBST {
 
	public static TreeNode deleteNode(TreeNode root, int key) {
	    if(root == null){
	        return null;
	    }
	    if(key < root.val){     //then delete the node to delete is in the left subtree
	        root.left = deleteNode(root.left, key);
	        
	    } else if(key > root.val){   //then delete the node to delete is in the right subtree
	        root.right = deleteNode(root.right, key);
	        
	    } else {  //key == root.val then the node to delete is right here
	    	 
	        if(root.left == null){   // 3. node has no left subtree and only has right subtree - return the right subtree
	            return root.right;
	        }else if(root.right == null){  //2. node has no right subtree and only has left subtree - return the left subtree
	            return root.left;
	        }
	        
	        TreeNode minNode = findMin(root.right);  //4. node has both left and right, then find the minimum value in the right subtree
	        root.val = minNode.val;                  //set the minimum value to the currently found node
	        root.right = deleteNode(root.right, root.val);
	    }
	    return root;
	}

	private static TreeNode findMin(TreeNode node){
	    while(node.left != null){
	        node = node.left;
	    }
	    return node;
	}
	
    
    public static List<Integer> printTree_preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
       
		System.out.println("After delete: " + root.val);
		dfs_preorder(root, res);
        return res;
    }
    
    public static void dfs_preorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	
		result.add(root.val);
    	        
        if (root.left != null) dfs_preorder(root.left, result); // visit all nodes of left subtree
        
        if (root.right != null) dfs_preorder(root.right, result);  // visit all nodes of right subtree     
       
    }

    public static void main(String[] args) {
    	//root = [5,3,6,2,4,null,7]
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		
		// left subtree
		root.left.right = new TreeNode(2); //3 -> 2
		root.left.left = new TreeNode(4);
		
		//right subtree
		root.right.right = new TreeNode(7); //6 -> 7
		
		int to_delete = 3;
		TreeNode remains = deleteNode(root, to_delete ); 
		
		//Pre-Order : root - left - right
		System.out.println("BinaryTree in DFS PreOrder: " + printTree_preorderTraversal(remains));
		
    }
}