package binarytree;
	
/*
235. Lowest Common Ancestor of a Binary Search Tree
 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 
     		 1
 		2		  3
 	 4	  5   6		 7
 	 			8
 	 			
 Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.

 
LCA is lowest common ancestor of n1 and n2. 
 (Example, lowest common ancestor of node 7 and 8 is node 3
           lowest common ancestor of node 2 and 5 is node 2 itself )
 
 To find LCA, 
 4 cases: 	right of node is null, 
 			left of node is null, 
 			both left & right of node are null
		 	both left & right of node are not null
 
 Time Complexity: O(N)
 */


public class LowestCommonAncestor {
	public static class Node { 
        int val; 
        Node left; 
        Node right; 
  
        public Node(int value) { 
            this.val = value; 
        } 
    } 
 		
	
 	public static Node findLCA(Node root, int n1, int n2)  
    { 
        if (root == null) 
            return root; 
        if (root.val == n1 || root.val == n2) 
            return root; 
  
        Node left = findLCA(root.left, n1, n2); 
        Node right = findLCA(root.right, n1, n2); 
  
        if (left != null && right != null) //both left & right of node are not null
            return root;      
        return (left != null ? left : right);		
 
     /* if (left != null) 
            return findLCA(root.left, n1, n2); 
        else
            return findLCA(root.right, n1, n2); */
    } 
    
	//Way 2: passing two nodes p, q instead of values
	public static Node lowestCommonAncestor(Node root, Node p, Node q) {
		if (root == null) {
			return null;
		} 
		if ( root == p || root ==q) {
			return root;
		}
		
		Node left = lowestCommonAncestor(root.left, p , q);
		Node right = lowestCommonAncestor(root.right, p , q);
		
		// 4 cases: right of node is null, left of node is null, both left & right of node are null
		// both left & right of node are not null
		if (left != null && right != null)
			return root; // lowest common ancestor
	
		return (left != null ? left : right);				
	}	
	 
    public static void main(String args[])
    {  
    	 Node  root = new Node(1); 
         root.left = new Node(2); 
         root.right = new Node(3); 
         root.left.left = new Node(4); 
         root.left.right = new Node(5); 
         root.right.left = new Node(6); 
         root.right.right = new Node(7); 
         root.right.left.right = new Node(8);  
          
         Node lca = findLCA(root, 4, 6); //1
         System.out.println("LCA(4, 6) = " + lca.val);
         
         lca = findLCA(root, 7, 8); //3
         System.out.println("LCA(7, 8) = " + lca.val);
        		
         lca = lowestCommonAncestor(root, root.right.right, root.right.left.right ); 	
          System.out.println("lca(7, 8) = " + lca.val);
    	
    }
}