package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/

Given a binary tree, return the level order traversal of its nodes’ values. (ie, from left to right, level by level).

For example:

Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

 */

public class LevelOrderTraversal_ByBFS {
	
	public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	
            int levelSize = queue.size();  //each level has different size
            List<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();   //remove 
                level.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);    // add
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }                
            }    
            
            result.add(level);
        }
        
        return result;
    }
	
	public static void main (String[] agrs) {
		TreeNode root3 = new TreeNode(5);
		root3.right = new TreeNode(6); //5 ->6
		root3.right.right = new TreeNode(7); //6 -> 7
		root3.right.left = new TreeNode(3); //6 ->3
		root3.right.left.right = new TreeNode(4); //3->4
		root3.right.left.left = new TreeNode(2); //3->2
		
		System.out.println("\nBinary Tree in BFS - Level Order Traversal");
		List<List<Integer>> list = levelOrder(root3);
		System.out.println(list);
	}
}
