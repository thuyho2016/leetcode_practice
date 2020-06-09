package binarytree;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
 *  103. Binary Tree Zigzag Level Order Traversal
 
 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

How to approach;

 1. Print all nodes of Binary Tree by Breadth First Search (level-order traversal from left to right) using QUEUE
  
 2. To print all nodes of BT in Zig zag by checking level.
 If level is even number such as 0, 2, 4, append node to list  - list.add(element)
 Else level is odd number 1, 3,  add in the front of list -  list.add(int index, element) 
 
 Example:
               4   ---->   level 0 even number 
    <--- 2             6    level 1 (odd level) : print right to left 
      1    3       5      7   ---> level 3 even number: print left to right
      
      
         /  \
   <---8   null  9  10  11  12

  Output:
  
  [[4],
  [6, 2],
  [1, 3, 5, 7]]


Time complexity: O(n), space complexity: O(n),  N is the number of nodes in the tree
 */

public class DisplayBT_DiagonalZigZag_ByBFS {

	//return list of list <Integer>
	public static List<List<Integer>> printLists_levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //add the whole tree to queue
        
        while (!queue.isEmpty()) {

            List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();  //1, 2, 4
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll(); // remove frist node from the queue
                level.add(node.val);  //list = [4], list =[2], list = [2, 6]
                					  // [1] -> [1, 3] -> [1, 3, 5] ->[1, 3, 5, 7]     
                
                if (node.left != null) {
                    queue.offer(node.left);  //left subtree 2 is added to queue at index [0]. Size of queue is 1
                }								// 1 is added to queue, queue now has node 6, 1.
                
                if (node.right != null) { 
                    queue.offer(node.right); // //left subtree 6 is added to queue at index [1]. Size of queue is 2
                }                           // 3 is added to queue, queue now has node 6, 1, 3. Size of queue is 3
            }
            result.add(level); //[[4]] --> [[4], [2, 6]] --> [[4], [2, 6], [1, 3, 5, 7]]
        }
        
        return result;
    }
		
	public static List<List<Integer>> print_DiagonalTraversal(TreeNode root) {
	    List<List<Integer>> res = new ArrayList<>();
	    
	    if (root == null) return res;
	    
	    Queue<TreeNode> q = new LinkedList<>();
	    q.offer(root);  //add the whole tree to queue
	    int level = 0; // level 0 has root node. Level 1 has 2 & 6 
	    
	    while (!q.isEmpty()) {
	       List<Integer> curList = new ArrayList<>();
	       int size = q.size();
	      
	       for (int i = 0; i < size; i++) {
	         TreeNode cur = q.poll();
	         
	         if(level % 2 == 0)
	        	 curList.add(cur.val);  //append val to list in the end, list = [1] -> [1, 3] -> [1, 3, 5] ->[1, 3, 5, 7]
	         else 
	        	 curList.add(0, cur.val); // insert 2 to list at index = 0, list=[2], then next round: insert 6 to list at index = 0 --> list =[6, 2]
	        
	         System.out.println(curList); //[4]
	           
	         if (cur.left != null) {
	            q.add(cur.left);
	         }
	         
	         if (cur.right != null) {
	             q.add(cur.right);
	         }
	      }  //for
	       
	      level++;
	      res.add(curList); //[[4], [6, 2]]
	    }
	    return res;
	    
	}  

	public static void main (String[] agrs) {
		// create Tree
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		
		root.left.right = new TreeNode(3); //2 ->3
		root.left.left = new TreeNode(1);
			
		root.right.left = new TreeNode(5); //6 ->5
		root.right.right = new TreeNode(7);
				
		System.out.println("Binary Tree in BFS - Level Order Traversal" + printLists_levelOrderTraversal(root));
		
		List<List<Integer>> output = print_DiagonalTraversal(root);
		System.out.println("Print BT in Zigzag level order: " + (output));
	    
		// [3,9,20,null,null,15,7],
    	TreeNode head = new TreeNode(3);
		head.left = new TreeNode(9);	
		head.right= new TreeNode(20);
		
		head.right.left = new TreeNode(15);
		head.right.right = new TreeNode(7);

		System.out.println("Print BT in Zigzag level order: " + print_DiagonalTraversal(head));
		/*
		[3]
		[9]
		[20, 9]
		[15]
		[15, 7]
		*/
		
	}
}

	  
	