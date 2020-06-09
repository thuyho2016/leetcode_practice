package binarytree;

/* 104. Maximum Depth of Binary Tree (easy level)
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 
 Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

For example, max height of below Binary Tree is 3.
Example Tree

          1
 		/  \	
	   2    3
      / \
     4   5	
     
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
   

 *  
 * Algorithm;
 * 
 * maxDepth() or maxHeight()
 * 1. If tree is empty then return 0
 * 2. Both of root.left == null && root.right ==null, return 1
 * 3. Else
     (a) Get the max depth of left subtree recursively  
             call maxDepth( tree->left-subtree)
    
     (a) Get the max depth of right subtree recursively 
            call maxDepth( tree->right-subtree)
            
     (c) Get the max of max depths of left and right subtrees and add 1 to it for the current node.
         
             max_depth = 1 + Math.max(max depth of left subtree, max depth of right subtree) 
                           
     (d) Return max_depth 
     
     Time Complexity: O(n)
     space complexity O(log(N)).
 */

public class MaximumDepthHeightBT {
   
	//recursive
	public static int maxDepth(TreeNode root)  
    { 
        if (root == null) 
            return 0; 
        
        //base case - tree only has root node, return 1
        if (root.left == null && root.right == null) 
            return 1; 
   

        // If left subtree is NULL, recursive for right subtree 
        if (root.left == null) 
            return maxDepth(root.right) + 1; 
  
        // If right subtree is NULL, recursive for left subtree 
        if (root.right == null) 
            return maxDepth(root.left) + 1; 
        
        //case left and right subtree is not NULL
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
       
    } 
       

    public static void main(String[] args)  
    { 
    	//create a tree
        TreeNode root =  new TreeNode(1); 
        root.left = new TreeNode(2); 
        root.right = new TreeNode(3); 
        root.left.left = new TreeNode(4); 
        root.left.right = new TreeNode(5); 
   
        System.out.println("Height of tree is : " +  maxDepth(root)); //3
        
        TreeNode root2 =  new TreeNode(3); 
        root2.left = new TreeNode(9); 
        root2.right = new TreeNode(20); 
        root2.right.left = new TreeNode(15); 
        root2.right.right = new TreeNode(7); 
   
        System.out.println("Height of tree is : " +  maxDepth(root)); //3
    } 
} 






