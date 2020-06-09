package binarytree;

/*
Given a Binary Search Tree(BST), find the second largest element.

Input: root = [4,2,6,null,3], k = 2
        4
       /  \
      2    6
       \
        3
           
Output: 1


Solution:
 - depth first search - in-order traversal:  Left subtree < ROOT < Right subtree
 - find size of nodes in RIGHT subtree
 - if I find the kth Smallest element, return the value of the element.
 
 https://www.geeksforgeeks.org/second-largest-element-in-binary-search-tree-bst/
 
 
 * Problem 2 : 
 * Find the node with maximum value in a Binary Search Tree using recursion

 */

public class KthLargestNodeBT_Recursive {
  
    public static int kthLargest(TreeNode root, int k) {
    	int rightSize = 0;
    	
    	if (root.right != null ) {
    		rightSize = countNodes(root.right);
    		System.out.println("Nodes in RIGHT subtree of " + root.right.val + " is: " + rightSize);
    	}
    	if (k == rightSize + 1) {
    		return root.val; //  return root.val; 
    	}
    	else if (k < rightSize) { // 1 < 2
            return kthLargest(root.right, k);
            
        } else {//(k > rightSize + 1) {
            return kthLargest(root.left, k - rightSize - 1); // find k - (leftSize +1) = k - leftsize - 1
                                               // 1 is counted as current node
        }
    }
    
 
    //count the number of nodes on the RIGHT subtree(left node and right node of RIGHT subtree)
    public static int countNodes(TreeNode n) {
        if (n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
   
	public static void main (String[] agrs) {
		
		//Input: [4,2,6,null,3], k = 2
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		// left subtree
		root.left.right = new TreeNode (3); 
		
		int i = kthLargest(root, 2);
		System.out.println("2nd Largest node: " + i); //4
	
		
	}
}


