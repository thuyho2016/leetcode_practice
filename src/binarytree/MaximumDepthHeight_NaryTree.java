package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 559. Maximum Depth of N-ary Tree(easy level)
 * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 
 Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples). 

Example 1:

			1
		 /	|  \
		3	2	4
	  /   \	
	 5     6
	 
Input: root = [1,null,3,2,4,null,5,6]
Output: 3


 *  
 * Algorithm of Binary Tree;
 * 
 * maxDepth() or maxHeight()
 * 1. If tree is empty then return 0
 * 2. Both of root.left == null && root.right ==null, return 1
 * 3. Else
     (a) Get the max depth of left subtree recursively  
             call maxDepth( tree->left-subtree)
    
     (a) Get the max depth of right subtree recursively 
            call maxDepth( tree->right-subtree)
            
     (c) Get the max of max depths of left and right subtrees and add 1 to it for the current node.
         
             max_depth = 1 + Math.max(max depth of left subtree, max depth of right subtree) 
                           
     (d) Return max_depth 
     
  
     
     Time Complexity: O(n)
     space complexity O(log(N)), the height of the tree would be log(N).
 */

public class MaximumDepthHeight_NaryTree {
   
	//recursive - DFS
	public static int maxDepth(NaryTree root)  
    { 
        if (root == null) 
            return 0; 
        
        int max = 0;
        //base case - children is empty, return 1 for root level
        if (root.children.isEmpty()) 
            return 1; 
        else {
	
	       for (NaryTree child: root.children) {
	    	   max = Math.max(max, maxDepth(child));
	       }
	       return max + 1; 
        }
    } 
   
   //use DFS - count level
   public static int maxDepth_BFS(NaryTree root) {
        if(root==null)
            return 0;
        
        Queue<NaryTree> q= new LinkedList<>();
        int level=0;
        q.add(root);
        while(!q.isEmpty())
        {
            int size=q.size();
            level++;
            for(int i=0;i<size;i++)
            {
            	NaryTree cur = q.poll();
                for(NaryTree node: cur.children)
                {
                    if(node != null)
                       q.add(node);
                }
            }
        }
        return level;
    }

    public static void main(String[] args)  
    { 
    	//create a tree
    	NaryTree root = new NaryTree(1);
		NaryTree n3 = new NaryTree(3);
		NaryTree n2 = new NaryTree(2);
		NaryTree n4 = new NaryTree(4);
		
		List<NaryTree> children = new ArrayList<NaryTree>();
		children.add(n3);
		children.add(n2);
		children.add(n4);
   
		root.setChildren(children);
		
		NaryTree n5 = new NaryTree(5);
		NaryTree n6 = new NaryTree(6);
		List<NaryTree> children2 = new ArrayList<NaryTree>();
		children2.add(n5);
		children2.add(n6);
		
		n3.setChildren(children2);
		
        System.out.println("Height of tree is : " +  maxDepth(root)); //3
        System.out.println("Height of tree is : " +  maxDepth_BFS(root)); //3
    } 
} 






