package binarytree;

/*
 * 110. Balanced Binary Tree  ( easy level)
https://leetcode.com/problems/balanced-binary-tree/

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
   
Return true. (left subtree has level 1 and right subtree has level 2, so they differ by 1)

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
 
Return false because right subtree has level 1, but left subtree has level 3, so they differ by 2

Absolute |maxDepth(leftSubtree) - maxDepth(rightSubtree| <= 1

It is similar of 104. Maximum Depth of Binary Tree
I have to write a method maxDepth(TreeNode node)
Step 1: maxDepth(root) == null --> return 0
Step 2:    root has left and right , I calculate leftDepth and rightDepth. Then, return bigger number between leftDept or rightDepth
          /   \
        left  right
        
        int leftDepth = maxDepth( root.left)
        int rightDepth = maxDepth(root.right)
     
        return Math.max(leftDepth, rightDepth) + 1;
 Step 3:
        |maxDepth(leftSubtree) - maxDepth(rightSubtree| >  1  
        return null because tree is not balanced
        
        if maxDepth of leftSubtree itself or maxDepth(rightSubtree) is not balanced , return null.
               

Time complexity: O(n) and spaceO(n) 

 */

public class BalancedBT {		
	
	public boolean isBalanced(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
	
	    int leftHeight = height(root.left);
	    int rightHeight = height(root.right);

	    return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) &&  isBalanced(root.right);
	}

	private static int height(TreeNode root) {
	    if (root == null)
	        return 0;
	    return 1 + Math.max(height(root.left), height(root.right));
	}
		
	//Way 2
	public boolean isBalanced2(TreeNode root) {
        return maxDepth(root) != null;  
    }

	public Integer maxDepth(TreeNode root) {
		if (root == null) return 0;
		
		Integer leftDepth = maxDepth(root.left);
		Integer rightDepth = maxDepth(root.right);
		
		if (leftDepth == null || rightDepth == null) { //either leftSubtree of rightSubtree is not balance, then tree is not balanced
			return null;
		}
		
		if (Math.abs(leftDepth - rightDepth) > 1) { // check if height/depth of left and right is greater than 1
			return null;
		}
		
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	public static void main(String[] args) {
    	
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);		
		
		//right subtree
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);	
		
		BalancedBT obj = new BalancedBT();
		System.out.println(obj.isBalanced2(root)); //true
		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(2);		
		
		//left subtree
		root2.left.left = new TreeNode(3);
		root2.left.right = new TreeNode(3);	
		
		root2.left.left.left = new TreeNode(4); //3->4
		root2.left.left.right = new TreeNode(4);	
		
		System.out.println(obj.isBalanced2(root2)); //false
				
    }
}