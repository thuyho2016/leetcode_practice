package binarytree;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import binarytree.TreeNode;

/* 94. Binary Tree Inorder Traversal

Given a binary tree, return the inorder traversal of its nodes' values.  ( Left subtree -> Root -> Right subtre)

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]


* 144. Binary Tree Preorder Traversal

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]

 
* 145. Binary Tree Postorder Traversal
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]


Case 1:  print Binary Tree by DFS in In-order (Left - Root - Right), return List<Integer>  [1,3,2]

Case 2: print Binary Tree by DFS in pre-order ( Root - Left - Right) using Recursive
         return List<String>  ["1->2->5", "1->3"]


Case 3: print Binary Tree by DFS in PostOrder (Left - Right - Root), return List<Integer>


NOTE:
Depth First Search: go deeply children until it reaches the end node

* DFS (Depth First Search) - go deeply children before traversing neighbor

   1. Inorder Traversal:  Left subtree -> Root -> Right subtree

	   inOrder(t) {
		    if(t is not empty) {
		        inOrder( left subtree of t )
		        process t's root element
		        inOrder( right subtree of t )
		    } 
		} 
		1->2->3 -> 4 -> 5->6->7
		
   2. Preorder Traversal:   Root -> Left subtree -> Right subtree
	    preOrder(t) {
		    if(t is not empty) {
		        process t's root element
		        preOrder( left subtree of t )
		        preOrder( right subtree of t )
		    } 
		} 
		4 -> 2->1->3 -> 6->5->7
		
   3. Postorder Traversal:  Left subtree -> Right subtree -> Root
     postOrder(t) {
		    if(t is not empty) {
		        postOrder( left subtree of t )
		        postOrder( right subtree of t )
		        process t's root element
		    } 
	  } 
	  1->3->2 ->5->7-6 ->4


 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * DFS: start from a root and reach all the way down to certain leaf, and then back to root to reach another branch.
 
 Time complexity : O(n)
 The worst case space required is O(n), and in the average case it's O(logn) where n is number of nodes
 */

public class DisplayBT_Inorder_PreOrder_PostOrder_ByDFS {
	
	//Case1: print Binary Tree by DFS inOrder ( Left - Root - Right), return List<Integer>
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs_inorder(root, res);
        return res;
    }
    
    public static void dfs_inorder( TreeNode root, List<Integer> result) {
      
        if (root != null) {
            if (root.left != null) dfs_inorder(root.left, result);  // visit all nodes of left subtree            
            
            result.add(root.val);
            
            if (root.right != null) dfs_inorder(root.right, result); // visit all nodes of right subtree
        }   
       
    }
   
    
  //Case2: print Binary Tree by DFS PreOrder (Root - Left - Right), return List<Integer>
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs_preorder(root, res);
        return res;
    }
    
    public static void dfs_preorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	
		result.add(root.val);
    	        
        if (root.left != null) dfs_preorder(root.left, result); // visit all nodes of left subtree
        
        if (root.right != null) dfs_preorder(root.right, result);  // visit all nodes of right subtree     
       
    }
    
    // Case 2: DFS in preOrder ( Root - Left - Right) return List<String> 	
  	public static List<String> binaryTreePaths_DFS(TreeNode root) {
  		List<String> res = new ArrayList<String>(); //create a ArrayList to hold nodes
  		String path = "";
  		dfs(root, path, res);
  		return res;
  	}
  	
  	//Depth First Search in preOrder using Recursive
  	public static void dfs(TreeNode root, String path, List<String> result) {
  		// root has no child
  		if (root.left == null && root.right ==null) {
  			path += root.val;
  			result.add(path); // add to result to display root  			
  		}
  		
  		path = path + root.val + "->";
  		
  		if (root.left != null)    // visit all nodes of left subtree
  			dfs(root.left, path, result);
  		if (root.right != null)   // visit all nodes of right subtree
  			dfs(root.right, path, result);
  		
  	   //	return result;  // [1->2->5->8, 1->2->5->9, 1->3]
  	}
  	
  	
  	//Case3: print Binary Tree by DFS PostOrder (Left - Right - Root), return List<Integer>
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs_postorder(root, res);
        return res;
    }
    
    public static void dfs_postorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	        
        if (root.left != null) dfs_postorder(root.left, result);
        
        if (root.right != null) dfs_postorder(root.right, result);         
       
        result.add(root.val);
    }
    
   // Case 3: Without using recursive, so I have to use Stack and HashSet
  	public static List<Integer> postorderTraversal_Stack(TreeNode root) {
  		List<Integer> result = new ArrayList<Integer>();
  	    
  		if (root == null) { 
  	      return result; 
  	    }

  	    Stack<TreeNode> stack = new Stack<>();
  	    HashSet<TreeNode> visited = new HashSet<>();
  	    
  	    stack.push(root);
  	    
  	    while (!stack.isEmpty()) {
  	       TreeNode node = stack.peek();
  	       
  	       if (node.left != null && !visited.contains(node.left)) {
  	          stack.push(node.left);
  	          continue;
  	       }
  	       if (node.right != null && !visited.contains(node.right)) {
  	          stack.push(node.right);
  	          continue;
  	       }
  	       
  	       TreeNode n = stack.pop(); //pop node from Stack to Tree, then add to HashSet
  	       visited.add(n);
  	       result.add(n.val); //[3] -->[3, 2] -->[3,2,1]
  	    
  	    }
  	    
  	    return result;
  	}
  	
  	public static void main (String[] agrs) {
  	//	Input: [1,null,2,3]
  		TreeNode root1 = new TreeNode(1);
		root1.right = new TreeNode(2);	
  		root1.right.left = new TreeNode(3);
  	    //Case 1: print Binary Tree Paths in DFS
		//List<Integer> list = inorderTraversal(root1);
		System.out.println("\nBinaryTree in InOrder DFS: " +  inorderTraversal(root1));  //[1, 3, 2]
		
		System.out.println("\nBinaryTree in PreOrder DFS: " +  preorderTraversal(root1));  // [1,2,3]
  			
		System.out.println("\nBinaryTree in PostOrder DFS: " +  postorderTraversal(root1));  // [3,2,1]
		System.out.println("\nBinaryTree in PostOrder By Stack: " +  postorderTraversal_Stack(root1));  // [3,2,1]		
		
		//Tree: 1 -> 2-> 3 , 2-> 5 -> 8 -> 9
	    //Output in String:	[1->2->5->8, 1->2->5->9, 1->3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.right.left = new TreeNode(8);
		root.left.right.right = new TreeNode(9);	

		
		//Case 2: print Binary Tree Paths in DFS
		List<String> paths = binaryTreePaths_DFS(root);
		System.out.println("\nBinaryTree in PreOrder String: " + paths.toString());  //
	  	
  	}
}
	
	
	