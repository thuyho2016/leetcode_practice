package binarytree;

/* 
* 1008. Construct Binary Search Tree from Preorder Traversal
https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]

Output: [8,5,10,1,7,null,12]
			8
		  /	   \
		 5		10
		/ \       \
	   1   7       12
 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.
   
Time and Space complexity is O(n)
 
 
 Preorder Traversal:   Root -> Left subtree -> Right subtree
	    
	    preOrder(t) {
		    if(t is not empty) {
		        process t's root element
		        preOrder( left subtree of t )
		        preOrder( right subtree of t )
		    } 
		} 
		
		Root->L->R ->Root->L
		8 -> 5->10 -> 1->5->7

Find the left part and right part,
then recursively construct the tree.

		
*/

public class ConstructArrayToBST_PreOrder{
	/**
	  Give the function a bound the maximum number it will handle.
	The left recursion will take the elements smaller than node.val
	The right recursion will take the remaining elements smaller than bound
	
	bstFromPreorder is called exactly N times.
	It's same as a preorder traversal.
	Time: O(N)	

	 */
	
	static int i = 0;
	public static TreeNode bstFromPreorder(int[] preorder)  {
		return bstFromPreorder(preorder, Integer.MAX_VALUE);
	}
	 
	public static TreeNode bstFromPreorder(int[] A, int bound) {
		if ( i == A.length || A[i] > bound) return null;
		
		TreeNode root = new TreeNode(A[i++]);
		
		root.left = bstFromPreorder(A, root.val);
		root.right = bstFromPreorder(A, bound);
		return root;
	}
	
	/**
	Solution 1:
	Binary search
	
	Find the left part and right part,
	then recursively construct the tree.

The input array is the preorder traversal of a BST.
1.preorder[0] is the root of BST
2.The sub array from preorder[0] to the first element that is bigger than preorder[0] is the preorder traversal of the left subtree.
The sub array from element that is bigger than preorder[0] to the end of the array is the preorder traversal of the right subtree.

	 */
	
	
	public static TreeNode bstFromPreorder2(int[] A) {
		return helper(A, 0, A.length);
				
	}
	
	// i is root index
	public static TreeNode helper( int[] A, int i, int j ) {
        if (i == j )return null;
        

       //int value = A[i];
      //  TreeNode root = new TreeNode(value);
        TreeNode root = new TreeNode(A[i]);
        
        int mid = i + 1;
		// find the left subtree of current node
        while(i <= A.length -1  && A[mid] < A[i])
        {
            mid++;
        }
        
        
      //do the same thing for left and right subtree.
        root.left = helper(A, i + 1, mid);
        root.right = helper(A, mid, j);
        
        return root;
	}       
	        		
	        		
	public static void main(String args[])
    {  
         int[] preorder = {8,5,1,7,10,12};
         System.out.println(bstFromPreorder(preorder)); //[8,5,10,1,7,null,12]
         
    }

}
	
	