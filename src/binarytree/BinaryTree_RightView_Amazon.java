
package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

/*
 * Amazon Interview
 * Print Right boundary tree
 * 
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4, 5]

Explanation:

   1            <---
 /   \
2     3         <---
 \    / \
  5   6  4       <---
     / \
     2  7


[1, 3, 4, 5, 7]

Time complexity: O(n)

- need to print 5 ??
 */


public class BinaryTree_RightView_Amazon {
	
	
	public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();  //create a ArrayList to hold nodes
       
        if (root == null) return null;
        
       	if (root.left == null && root.right == null ) {
			result.add(root.val);
			return result;
       	}

        //add the root node to list
      	result.add(root.val);      	
      	
        rightBoundary(root.right, result); // start root.right because i don't want to print root again
        rightBoundaryLeftSide(root.left, result); // call this method if left child
        return result;
	} 
			
	//start right child
	private static void rightBoundary(TreeNode root,  List<Integer> result) {  //  3, 4, 10
		
		if (root != null) {   // root.right 3 must not null 
			
			result.add(root.val); // print 3  
			if (root.right != null) { 
				rightBoundary(root.right, result);  // if current node has right child, recursive to check children
				
			} else if (root.left!= null) {  //if left child is null, recursive to check right child
				rightBoundary(root.left, result);
			}
		}
	}
	
	private static void rightBoundaryLeftSide(TreeNode root, List<Integer> result) {
		
		if (root != null) {  // 2 is  current node has left child
			//result.add(root.val); // uncomment since dont print root.left  2
			
			//recursive right child
			 if (root.right != null) {
				result.add(root.right.val);  // print right child
				rightBoundaryLeftSide(root.right, result);
			}
		}
	}

   
	public static void main(String[] args) {
    	
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// left subtree
		root.left.right = new TreeNode(5); //2 -> 5		
		
		//rightsubtree
		root.right.right= new TreeNode(4);
		root.right.left = new TreeNode(6);	
		root.right.left.right = new TreeNode(7);
		root.right.left.left = new TreeNode(2);
		
		System.out.println(boundaryOfBinaryTree(root)); //[1,3,4,5]
      		
    }
}