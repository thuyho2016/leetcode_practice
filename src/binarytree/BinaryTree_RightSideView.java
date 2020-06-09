package binarytree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

/*
 199. Binary Tree Right Side View
 https://leetcode.com/problems/binary-tree-right-side-view/
 
 Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---


Tree 2: 
  
  
    1          <---
  /   \
 2     3         <---
 \    / \
  5   6  4       <---
     / \
    2  7


Output: [1, 3, 4, 7]

time : O(n) for we traverse the whole tree
space O(log n) the length of longest level of nodes
 */


public class BinaryTree_RightSideView {

   public static List<Integer> rightSideView(TreeNode root) {
        //only the most right
        List<Integer> ans = new ArrayList<>();
        
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root); // add root 
        
        while(!q.isEmpty()) { //1, left subtree, right subtree
             
        	 int size = q.size();
             
             for (int i = 0 ; i< size ; i++) {
                 
            	 TreeNode current = q.remove(); //2, i = 0, size =2
            	 
                 if (i == size - 1){ //i=1, size = 2  need to check if it is last thing of the loop to do
                     ans.add(current.val);  //[1], [1, 3], [1, 3, 4] , At level, add node at the time 
                 }
                 
                 if(current.left != null) 
                	 q.add(current.left);
                 if(current.right != null) 
                	 q.add(current.right);
             }
         }
        return ans;
    }
  
	public static void main(String[] args) {

		//[1,2,3,null,5,null,4]
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(3);
		
		// left subtree
		root2.left.right = new TreeNode(5); //2 -> 5	
		//right subtree
		root2.right.right= new TreeNode(4);
		
		System.out.println(rightSideView(root2)); //[1, 3, 4]
		
		//1,2,3,null,5,6,4,2,7
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
		
		System.out.println(rightSideView(root)); //[1, 3, 4, 7]
		
    }
}