package binarytree;
	
/*
 Find distance between two nodes of a Binary Tree (Shortest distance)
     		 1
 		2		  3
 	 4	  5   6		 7
 	 			8
 	 			
 Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.

 
LCA is lowest common ancestor of n1 and n2. 
 (Example, lowest common ancestor of node 7 and 8 is node 3
           lowest common ancestor of node 2 and 5 is node 2 itself )
 
 Steps:  first find LCA of BST, and then find the distance from LCA node to both n1 and n2, and return the sum of its distance
 
 1. find LCA of two nodes.
 2. Then I find distance from LCA to two nodes (d1, d2).
 3. distance = d1 + d2
 
 To find LCA, 
 4 cases: 	right of node is null, 
 			left of node is null, 
 			both left & right of node are null
		 	both left & right of node are not null
 */


public class ShortestDistanceTwoNodesInBT {
	public static class Node { 
        int value; 
        Node left; 
        Node right; 
  
        public Node(int value) { 
            this.value = value; 
        } 
    } 
 		
 	public static int distanceTwoNodes(Node root, int a, int b) 
	{ 
	     Node lca = findLCA(root, a, b); 
	
	     int d1 = findLevel(lca, a, 0);   //d1 - distance of n1 to LCA
	     int d2 = findLevel(lca, b, 0);   //d2 - distance of n2 to LCA
	
	     return d1 + d2;   //dist - distance between n1 and n2 
	} 
 	
 	// find distance from node 1 to LCA and from node 2 to LCA
 	// then add, 2 distances
    // Returns level of key k if it is present in tree, otherwise returns -1 
    public static int findLevel(Node root, int a, int level) 
    { 
        if (root == null) 
            return -1; 
        if (root.value == a) 
            return level;  //0
        
        int left = findLevel(root.left, a, level + 1); 
        if (left == -1) 
            return findLevel(root.right, a, level + 1); 
        return left; 
    } 
 
 	public static Node findLCA(Node root, int n1, int n2)  
    { 
        if (root == null) 
            return root; 
        if (root.value == n1 || root.value == n2) 
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
    
	//Way 2: find LCA of two nodes.
	public static Node findLCA2(Node root, Node p, Node q) {
		if (root == null) {
			return null;
		} 
		if ( root == p || root ==q) {
			return root;
		}
		
		Node left = findLCA2(root.left, p , q);
		Node right = findLCA2(root.right, p , q);
		
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
         
         System.out.println("Dist(4, 5) = " + distanceTwoNodes(root, 4, 5)); //2
         System.out.println("Dist(4, 6) = " + distanceTwoNodes(root, 4, 6)); //4
         System.out.println("Dist(3, 4) = " + distanceTwoNodes(root, 3, 4)); //3
         System.out.println("Dist(2, 4) = " + distanceTwoNodes(root, 2, 4)); //1
         System.out.println("Dist(8, 5) = " + distanceTwoNodes(root, 8, 5)); //5    	
    	
    }
}