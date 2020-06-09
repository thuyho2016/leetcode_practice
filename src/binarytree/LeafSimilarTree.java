
package binarytree;
import java.util.ArrayList;
import java.util.List;

/*
 * 872. Leaf-Similar Trees  (easy level)
 https://leetcode.com/problems/leaf-similar-trees/
 
Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.

			3
		  /   \	
         5     1
       / |     | \
     6    2	   9  8
         / \
        7   4
        
        
For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.


Time Complexity: O(T1 + T2),  T1, T2 are the lengths of the given trees.

Space Complexity: O(T1 + T2),  the space used in storing the leaf values.
 
 */

public class LeafSimilarTree {
 
	//To find the leaf value sequence of a tree, we use a depth first search. 
	public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        
		List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        addLeaf(root1,list1); // store the path
        addLeaf(root2,list2);
        
        if(list1.size() != list2.size()) { // if size is different, return false
            return false;
        }
        
        for(int i=0; i < list1.size(); ++i) {
            if(list1.get(i) != list2.get(i))  // check if value is not the same, then return false
                return false;
        }
        
        return true;
	} 
	
	//dfs 
	private static void addLeaf(TreeNode root,List<Integer> leaves){
        if(root == null)
            return;
        
        if(root.left == null && root.right == null){ // if left node and child node == null,  push root node to the list. 
            leaves.add(root.val);
        }
        
        //call addLeaf method recursively to keep going children of left node  
        if(root.left != null)
            addLeaf(root.left,leaves);
        
      //call addLeaf method recursively to keep going children of right node  
        if(root.right != null)
            addLeaf(root.right,leaves);
    }
	
	
          
    public static void main(String[] args) {
    	//Input: [[3,5,1,6,2,9,8,null,null,7,4]
    	//[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]]
    	
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		
		// left subtree
		root.left.left = new TreeNode(6); // 5->6
		root.left.right = new TreeNode(2); 
		
		TreeNode root2 = new TreeNode(3);
		root2.left = new TreeNode(5);
		root2.right = new TreeNode(1);
		
		// left subtree
		root2.left.left = new TreeNode(6); // 5->6
		root2.left.right = new TreeNode(2);
		
		boolean res = leafSimilar(root, root2);
		System.out.println(res); //true
      

		
    }
}