package binarytree;

import java.util.LinkedList;
import java.util.Stack;

/* 230. Kth Smallest Element in a BST
* https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Using stack
*/

public class KthSmallestNodeBT_Stack {
    
    public static int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            
        	while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            
            if (--k == 0) return root.val;
            root = root.right;
        }
        
        return -1;
    }
	
    //      1
    //  3       6 
    //5   9   8  
	public static void main (String[] agrs) {
		//Input: [1,3,6,5,9,8]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(8);
		// left subtree
		root.left.left = new TreeNode (5); //3 -> 5
		root.left.right = new TreeNode (9); //3 -> 9
		
		System.out.println("Smallest node by stack1: " + kthSmallest(root, 1)); //5
		
		
		//Input: root = [5,3,6,2,4,null,null,1]
		TreeNode root2 = new TreeNode(5);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(6);
		
		root2.left.left = new TreeNode (2); 
		root2.left.right = new TreeNode (4); 
		root2.left.left.left = new TreeNode (1);
		
		System.out.println("Smallest node by stack2: " + kthSmallest(root2, 1)); //1
				
	}
}


