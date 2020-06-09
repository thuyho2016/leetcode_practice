
package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 * 545. Boundary of Binary Tree
 * https://leetcode.com/problems/boundary-of-binary-tree/

 Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 

Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].


Boundary has 3 parts:
1. Left boundary:  1, 2, 4
2. Right boundary: 1, 3, 6, 10
3. Leaf nodes : 4, 7, 8, 9, 10
So, some nodes are repeated. Just delete the duplicate node. 
- Go to left boundary, check if you have left node. 
- Go to right boundary, check if you have left node. 
- write a function to print leaf

Time complexity: O(n)
 */


public class BoundaryBinaryTree {
	
	
	public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();  //create a ArrayList to hold nodes
       
        if (root == null) return null;
        
       	if (root.left == null && root.right == null ) {
			result.add(root.val);
			return result;
       	}

        //add the root node to list
      	result.add(root.val);
       	
      	//leftBoundary(root.left, result); // //print left boundary
     
        leftBoundaryExceptLeafNode(root.left, result); 
       printLeaf(root.left, result);   //  eaf node in left subtree
       printLeaf(root.right, result); // leaf node in right subtree
      //rightBoundaryExceptLeafNode(root.right, result);
      
      //rightBoundary(root.right, result); // start root.right because i don't want to print root again
        
        
         return result;
	} 
	
	 // how to avoid duplicate node? don't print leaf node of leftBoundary
	private static void leftBoundaryExceptLeafNode(TreeNode root, List<Integer> result) {
		 if (root == null) return;
		 
		 else if (root.left == null && root.right == null ) return;
		
		 else {
			 result.add(root.val); 
			 leftBoundaryExceptLeafNode(root.left, result);
		 }
	}

	private static void rightBoundaryExceptLeafNode(TreeNode root, List<Integer> result) {
		 if (root == null) return;
		 
		 else if (root.left == null && root.right == null ) return;
		
		 else {
			 rightBoundaryExceptLeafNode(root.right, result);
			 rightBoundaryExceptLeafNode(root.left, result);
			 result.add(root.val); 
		 }
	}
	
	//print leaf nodes  -  4,7,8,9,10.
	private static void printLeaf(TreeNode root, List<Integer> result) {
		if (root == null) { 
			return;
		}
		else if (root !=  null && root.left == null && root.right == null) { // node don't have children
			result.add(root.val);    // add node to list
		}
		else if (root != null )// root.left != null && root.right != null) {
		{	
			printLeaf(root.left, result); //make recursive call to keep going to left childs. 		
			printLeaf(root.right, result);
		}
	}	
	
	private static void leftBoundary(TreeNode root, List<Integer> result) {
		
		if (root != null) {  // 2 -  if current node has left child
			result.add(root.val); // add value to list to print it out
			//recursive left child
			if (root.left != null) {
				//result.add(root.left.val);  // print root.left.val is 2
				leftBoundary(root.left , result);   // if current node has left child
			} else if (root.right != null) {
				//result.add(root.right.val);
				leftBoundary(root.right, result);
			}
		}
	}
	
	//start right child -   3, 6, 10
	private static void rightBoundary(TreeNode root,  List<Integer> result) {  
		
		if (root != null) {   // dont't print the root again
			
			//result.add(root.val); don't add to print it here
			if (root.right != null) {   
				rightBoundary(root.right, result);  // if current node has right child, recursive right child 
			} else if (root.left!= null) {  // if current node has left child
				rightBoundary(root.left, result);
			}
			result.add(root.val); // add in the end to have reverse order
		}
	}

	
	public static void main(String[] args) {
    	
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// left subtree
		root.left.left = new TreeNode(4); // 2 -> 4
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.right.left = new TreeNode(7); 
		root.left.right.right = new TreeNode(8); 		
		
		//rightsubtree
		root.right.left = new TreeNode(6);
		root.right.left.left= new TreeNode(9);
		root.right.left.right = new TreeNode(10);		
		
		System.out.println(boundaryOfBinaryTree(root)); //[1,2,4,7,8,9,10,6,3]
      		
    }
}