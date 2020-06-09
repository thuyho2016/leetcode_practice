package binarytree;

/* 
124. Binary Tree Maximum Path Sum ( hard level)
https://leetcode.com/problems/binary-tree-maximum-path-sum/

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 

The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6


Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20  
    /  \
   15   7

Output: 42  ( 15 + 20 + 7 = 42)

					   -10 (max_gain at (-10) = 25 ( -10 + max(9,35) = -10 + 35)
					   / \
 max_gain(9) = 9	  9  20  (max_gain(20) = 35
					    /  \
 max_gain(15) =15	  15   7  (max_gain(7) = 7 since 7 has no child nodes
 
    
For each node the maximum path involving that node is either:
        just the node itself,
        the node + a path to its left, 
        the node + a path to its right,
        the node + a path to its left + a path to its right

so, steps:
1. Find maximum sum from leaf to current node  in left subtree of X	
2. Find maximum sum from leaf to current node  in right subtree of X
3. Add current node.data with the above values from step 1 & 2
	  int total = curNode.val + left + right; ( if ( left > 0, right > 0)
	   
4. find max between sum and total
		sum = Math.max(sum, total); 
		
5. Return current curNode.val + max(right side,left side)
		

For the recursion, return the max gain =  currentNode.val + max(left_gain, right_gain)
		sum = Math.max(sum, currentNode + left + right);
		
Time complexity : O(N) where N is number of nodes, since we visit each node not more than 2 times.
Space complexity :O(log(N)). We have to keep a recursion stack of the size of the tree height

        
Steps:
1.   root = -10, root.left = 9, root.right = 20
root = -10

2.Go to root.left = 9  
Recursive Node 9:  left = 0 and right = 0 as node 9 has no children. total = currentNode = 9
  because left and right = 0, so go directly sum = Math.max(sum, total) =  max(0,9) = 9.
  return 9 + max(0,0) = 9
  left = 9  // 9 is returned at line 127

3.Go to root.right = 20: 
  Node 20 has root.left = 15 and root.right = 7 

  Recursive node 15: left = 0, right = 0 as node 15 has no children
                     total = currentNode = 15  
                     sum = max(sum, total) = max(9,15) = 15
                     return 15 + max(0,0) = 15
                    
     root 20: left is returned 15: left = 15;
  
  Recursive node 7: left = 0, right = 0 as node 7 has no children
                    total = currentNode = 7
                    sum = max(sum, total)= max(15,7) = 15
                    return 7 + max(0,0) = 7
                    
      root 20:  right is returned 7: right = 7;     
                   total =  currentNode = 20. 
                   Because left = 15, so total = total + left = 20 + 15 = 35 and right = 7 > 0, total = total + right = 35 + 7 = 42. 
                   sum = max(sum, total) = max(15, 42) = 42. 
                   return 20 + max(15,7) = 20 + 15 = 35.
  
  root = -10: right is return 35: right = 35, left = 9 ( from above )
              total = currentNode.val = -10
              Because left = 9 > 0, total = total + left = -10 + 9 = -1 and , right =35 > 0, total = total + right = -1 + 35 = 34 
              sum = max(sum, total) = max(42, 34) = 42 
              
              return -10 + max(9, 35) = -10 + 35 = 25
  
https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
        
 */

public class MaximumPathSum  {
	
	private static int  sum = Integer.MIN_VALUE;
	
	// Version 1: easy to understand - go with it (use recursion)
	//Time complexity : O(N) where N is number of nodes, since we visit each node not more than 2 times.
	//Space complexity : O(log(N)).
	
	public static int maxPathSum(TreeNode root) {
		maxGain(root);
	    return sum;
	}
	
	private static int maxGain(TreeNode currentNode) { //currentNode
		
		if (currentNode == null) return 0;//  return Integer.MIN_VALUE;
		  // Go to left node and right node of root = -10, root.left = 9, root.right = 20
		
		//Find maximum sum from leaf to current node  in left subtree of X	
		int left_gain = maxGain(currentNode.left); //Node 9 has no child,so left = 0 for node 9; 0 for node 15, 0 for node 7 , 15 for node 20, 9 of root node -10
		
		//Find maximum sum from leaf to current node  in right subtree of X
		int right_gain = maxGain(currentNode.right); //Node 9 has no child, so right = 0 for node 9; right = 0 of node 15, 0 of node 7, 7 of node 20, 35 ( because 20 + max(15,7) = 20 + 15) for root node -10
     
		//Add the above values and current node.data    // update max_sum if it's better to start a new path
	    int total = currentNode.val + left_gain + right_gain; 

		//find max between sum and total
		sum = Math.max(sum, total); //sum = max(0,9) = 9; max(15,7)=15;  max(15, 42)= 42, max(42,34) = 42
     
		//max right or left side plus current node
		return currentNode.val + Math.max(left_gain, right_gain); // 9 + max(0,0)= 9; 20 + max(15,7) = 35
		
	}
	 	
	public static void main (String[] args)
    {
	/*	TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);  
		root.right = new TreeNode(3);
		
		System.out.println(maxPathSum(root));  */
				
		TreeNode root2 = new TreeNode(-10);
		root2.left = new TreeNode(9);
		root2.right = new TreeNode(20);
		root2.right.left = new TreeNode(15);
		root2.right.right = new TreeNode(7);
		
		System.out.println(maxPathSum(root2)); //42
						 
    }	
}