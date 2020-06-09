package binarytree;
/* 
 * 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/

Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

For example, minimum height of below Binary Tree is 2.

 		 1
 		/  \	
	   2    3
      / \
     4   5		
 *  
 * Algorithm;
 * 
 * maxDepth() or maxHeight()
 * 1. If tree is empty then return 0
 * 2 Both of root.left == null && root.right ==null, return 1
 * 3. Else
     (a) Get the min depth of left subtree recursively  
             call minDepth( tree->left-subtree)
    
     (a) Get the min depth of right subtree recursively 
            call minDepth( tree->right-subtree)
            
     (c) Get the min depths of left and right subtrees and add 1 to it for the current node.
         
             max_depth = 1 + max(max depth of left subtree, max depth of right subtree) 
                           
     (d) Return min_depth 
     
     Time Complexity: O(n)
      space complexity O(log(N)).
 */

public class MinimumDepthHeighBT {
	
	/* Function to calculate the minimum depth of the tree */
	public static int minDepth(TreeNode root) {
        if (root == null) 
            return 0; 
        
        //base case
        if (root.left == null && root.right == null) 
            return 1; 
   

        // If left subtree is NULL, recursive for right subtree 
        if (root.left == null) 
            return minDepth(root.right) + 1; 
  
        // If right subtree is NULL, recursive for left subtree 
        if (root.right == null) 
            return minDepth(root.left) + 1; 
        
        //case left and right subtree is not NULL
        return 1 + Math.min (minDepth(root.left), minDepth(root.right));
       
    } 
       
    /* Driver program to test above functions */
    public static void main(String[] args)  
    { 
        TreeNode root =  new TreeNode(1); 
        root.left = new TreeNode(2); 
        root.right = new TreeNode(3); 
        root.left.left = new TreeNode(4); 
        root.left.right = new TreeNode(5); 
   
        System.out.println("Height of tree is : " +  minDepth(root)); //2
    } 
} 






