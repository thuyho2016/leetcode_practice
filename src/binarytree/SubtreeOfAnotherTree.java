package binarytree;

/*
 * 572. Subtree of Another Tree
 * Check if a binary tree is subtree of another binary tree
 * 

Given tree s:

     3
    / \
   4   5
  / \
 1   2

Given tree t:
   4 
  / \
 1   2

Return true

 space complexity is O(m+n)
 */


public class SubtreeOfAnotherTree {
	
	// Use Recursive
	public static boolean isSubtree(TreeNode m, TreeNode s) {
		 if (m == null) return true;
	     if (m ==null || s == null) return false;
	     
	     if (isSame(m,s)) // check if subtree is same with bigger tree
	     	return true;
	     
	     // recursive to check left subtree and right subtree with the bigger tree
	     return isSubtree(m.left, s) || isSubtree(m.right, s);
    }
	
	// recursive to go/traverse each node
    private static boolean isSame(TreeNode m, TreeNode s) {
    	if (m == null & s == null) return true;
    	
    	// either main tree or subtree null, return false
    	if (m == null || s == null) return false;
    	
    	//recursive to compare root between 2 trees, left node, right node of 2 subtrees
		return (m.val == s.val && isSame(m.left, s.left) && isSame(m.right, s.right));
    	
    }

	//      1
    //  3        6 
    //5   9    8  
	public static void main (String[] agrs) {
		//Input: [1,3,6,5,9,8]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(8);
		// left subtree
		root.left.left = new TreeNode (5); //3 -> 5
		root.left.right = new TreeNode (9); //3 -> 9

		
		//subtree
		TreeNode s = new TreeNode(3);
		s.left = new TreeNode(5);
		s.right = new TreeNode(9);
		
		System.out.println("subtree of another tree?: " + isSubtree(root, s)); //true
				
	}
}


