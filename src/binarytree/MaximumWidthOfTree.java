
package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* 662. Maximum Width of Binary Tree
https://leetcode.com/problems/maximum-width-of-binary-tree/


Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. 
The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, 
where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).


The main idea in this question is to give each node a position value. If we go down the left neighbor, then position -> position * 2; 
and if we go down the right neighbor, then position -> position * 2 + 1. 
This makes it so that when we look at the position values L and R of two nodes with the same depth, the width will be R - L + 1.

Depth-First Search 

We know that a binary tree can be represented by an array (assume the root begins from the position with index 1 in the array). 
If the index of a node is i, the indices of its two children are 2*i and 2*i + 1. 
The idea is to use two arrays (start[] and end[]) to record the the indices of the leftmost node and rightmost node in each level, respectively. 
For each level of the tree, the width is end[level] - start[level] + 1. Then, we just need to find the maximum width.


        root: i
left:i * 2   right: i * 2 + 1
 
Time Complexity: O(N)
space complexity O(N)
 */

public class MaximumWidthOfTree {
    
    /** BFS - Prefer this solution
     * The idea is to keep the index location of each node into the map, and then for each level, 
     * calculate the width by (index of right most node - index of left most node + 1)
    
 	   The childrens' index position is calcuated by 2 * i and 2 * i +1 for node being at i-th position. (Recall binary heap)
     */
	//change the val of node to be the index to save space. The value is useless. All we need is just the index.
	public int widthOfBinaryTree(TreeNode root) {
        if (root == null)  return 0;
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        root.val = 0;  //level of root is 0
        int max = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
                                 //width will be R - L + 1
            max = Math.max(max, queue.peekLast().val - queue.peekFirst().val + 1);
            
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                
                if (root.left != null) {
                    root.left.val = root.val * 2;
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    root.right.val = root.val * 2 + 1;
                    queue.offer(root.right);
                }
            }
        }
        return max;
    }
	
	
	//use HasMap to store index as value
	public int widthOfBinaryTree2(TreeNode root) {
	    if (root == null) return 0;
	
	    LinkedList<TreeNode> queue = new LinkedList<>(); // double ended queue
	    queue.offer(root);
	
	    Map<TreeNode, Integer> map = new HashMap<>(); // value is index
	    map.put(root, 0); // level of root is 0
	
	    int maxWidth = 1;
	
	    while (!queue.isEmpty()) {
	    							//width will be R - L + 1
	        maxWidth = Math.max(1 + map.get(queue.peekLast()) - map.get(queue.peekFirst()), maxWidth);
			
	        for (int i = queue.size(); i > 0; i--) {
	            TreeNode node = queue.poll();
	
	            if (node.left != null) {
	                map.put(node.left, map.get(node) * 2);
	                queue.offer(node.left);
	            }
	
	            if (node.right != null) {
	                map.put(node.right, map.get(node) * 2 + 1);
	                queue.offer(node.right);
	            }
	        }
	    }
	    return maxWidth;
	}

	public int widthOfBinaryTree3(TreeNode root) {
	   if (root == null){
		   return 0;
	   }
	   
	   int wide = 1;

	   LinkedList<TreeNode> queue = new LinkedList<>();
	   queue.offer(root);
	   
	   while(queue.size()!=0){
	        int len   = queue.size();
	        
	        for (int i = 0; i < len; ++ i){
	            TreeNode node = queue.pollFirst();
	            
	            if (node != null){
	                queue.offer(node.left);
	                queue.offer(node.right);
	            } else {
	                queue.offer(null);
	                queue.offer(null);
	            }
	        }
	        
	        while(queue.peekFirst() == null && queue.size()!=0){
	            queue.pollFirst();
	        }
	        
	        while(queue.peekLast() == null && queue.size()!=0){
	            queue.pollLast();
	        }
	        
	        wide = Math.max(wide, queue.size());
	    }
	    return wide;
	}
	
	 //DFS - recursive	
    int max =0; // if use static LC report TC2 is wrong

    public int widthOfBinaryTree_DFS(TreeNode root) {
        helper(root, new ArrayList<Integer>(), 0, 0);
        return max;
    }
    
    private void helper(TreeNode root, List<Integer> lefts, int level, int index){
        if(root == null) return;
        if(level == lefts.size()){
           lefts.add(index); 
        }
        max = Math.max(max, index - lefts.get(level) + 1);
        helper(root.left, lefts, level + 1, index * 2);
        helper(root.right, lefts, level + 1, index * 2 + 1);
    }
    
       
    public static void main(String[] args)  
    { 
    	MaximumWidthOfTree o = new MaximumWidthOfTree();
    	
        TreeNode root =  new TreeNode(1); 
        root.left = new TreeNode(3); 
        root.right = new TreeNode(2); 
        
        root.left.left = new TreeNode(5);  //3->5
        root.left.right = new TreeNode(3); 
        
        root.right.right = new TreeNode(9);
   
        System.out.println("Width of tree is : " +  o.widthOfBinaryTree(root)); //4
        
        //example 2
        TreeNode root2 =  new TreeNode(1); 
        root2.left = new TreeNode(3); 
        
        root2.left.left = new TreeNode(5); 
        root2.left.right = new TreeNode(3); 
        System.out.println("Width of tree is : " +  o.widthOfBinaryTree(root2)); //2
        
        //example 3
        TreeNode root3 =  new TreeNode(1); 
        root3.left = new TreeNode(3); 
        root3.right = new TreeNode(2); 
        
        root3.left.left = new TreeNode(5); 
   
        System.out.println("Width of tree is : " + o.widthOfBinaryTree(root3)); //2
    } 
} 






