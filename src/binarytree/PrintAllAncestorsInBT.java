package binarytree;

/*
Given a Binary Tree and a key, write a function that prints all the ancestors of the key.

For example, if the given tree is following Binary Tree and key is 7, then your function should print 4, 2 and 1.


              1
            /   \
          2      3
        /  \
       4    5
      /
     7

 */


public class PrintAllAncestorsInBT {
	
	// Use Recursive
	
	public static boolean printAncestors(TreeNode root, int k)  
    { 
         /* base cases */
		if (root == null) return false;
		
		if (root.val == k) return true; 
   
        /* If target is present in either left or right subtree  
           of this node, then print this node */
        if (printAncestors(root.left, k) 
                || printAncestors(root.right, k))  
        { 
            System.out.print(root.val + " "); 
            return true; 
        } 
   
        /* Else return false */
        return false; 
    } 
	 
	public static void main (String[] agrs) {
		//Input: [1,2,3,4,5,null,null,7]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		// left subtree
		root.left.left = new TreeNode(4); //2 -> 4
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.left.left = new TreeNode(7);
		
		System.out.println("all ancestors: " + printAncestors(root, 7)); //true
				
	}
	
	
}


