package binarytree;
import java.util.ArrayList;
import java.util.List;

import binarytree.TreeNode;

/*
 617. Merge Two Binary Trees
 Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7

Time complexity : O(m). A total of m nodes need to be traversed. Here, m represents the minimum number of nodes from the two given trees.

Space complexity : The depth of the recursion tree can go upto mm in the case of a skewed tree. 
In average case, depth will be O(logm)
*/

public class MergeTwoBinaryTree_Recursive {
	
   public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
	   if(t1==null && t2==null) return null;
	   
	   if (t1 == null) return t2;
	   else if (t2 == null) return t1;
  	   
	   t1.val = t1.val + t2.val;
	   //TreeNode root = new TreeNode(t1.val);
	   
	   t1.left = mergeTrees(t1.left, t2.left);
	   t1.right = mergeTrees(t1.right, t2.right);
	   
	   return t1;
	  
   }
  
   public static void display(TreeNode n) {
	   if (n == null) {
		      return;
		    }
	   if (n.left == null && n.right == null) {
		   System.out.printf("%d ", n.val);
		   
	   }
	   display(n.left);
	   display(n.right);
   }
	 
   public static void main(String args[])
   {
	 /*Input t1 = [1,3,2,5]
	  t2 = [2,1,3,null,4,null,7]
		Output=	  [3,4,5,5,4,null,7]
		*/
	   TreeNode t1 = new TreeNode(1);
	   t1.left = new TreeNode(3);
	   t1.right = new TreeNode(2);
	   t1.left.left = new TreeNode(5);
	   
	   TreeNode t2 = new TreeNode(2);
	   t2.left = new TreeNode(1);
	   t2.right = new TreeNode(3);
	   t2.left.right = new TreeNode(4);
	   t2.right.right = new TreeNode(7);
	   
	   TreeNode t = mergeTrees( t1, t2);
	   display(t);
   }
}