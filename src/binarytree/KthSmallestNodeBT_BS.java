package binarytree;

import java.util.PriorityQueue;

/*
 * 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
the complexity is O(lgn)

Example 1:

Input: root = [3,1,4,null,2], k = 1

   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Input: root = [4,2,6,null,3], k = 1
Output: 2

Best Solution: Using In-order Traversal and binary search to optimize 
We can count how many nodes in a leftsubtree to decide the kth element may sit in left subtree or right subtree.
 * 
 * Assume that the root is having N nodes in its left subtree. If K = N + 1, root is K-th node. 
 * If K < N, we will continue our search (recursion) for the Kth smallest element in the left subtree of root. 
 * If K > N + 1, we continue our search in the right subtree for the (K – N – 1)-th smallest element.
 *  ( Example : K = 5 > Root(1) + N nodes in left-subtree = 5) 
 *  Note that we need the count of elements in left subtree only.

Time complexity: O(k) where k is height of tree.


Solution2: 
     * Depth first search inorder traversal: Left subtree < ROOT < Right subtree
	 * recursive to count the number of nodes in left subtree: 
	 * if number count == kth, return the value of the element.
	
	
Solution 3: use BFS
 */

public class KthSmallestNodeBT_BS {
	
	// Prefer this - Using In-order Traversal and binary search to optimize
    public static int kthSmallest(TreeNode root, int k) {
    	int count = 0; //left size
    	
        if(root==null)
            return 0;
        

		count = countNodes(root.left); ////count the number of nodes on the left subtree
		System.out.println("Count number of nodes in leftsubtree " + count );
    
    	if (k == count + 1) { // smallest is root node
    		System.out.println("K Smallest Nodes " + root.val ); 
    		return root.val; //  2 
    	}
    	else if (k < count + 1) { // 1 < 2
            return kthSmallest(root.left, k);
            
        } else { //(k > count + 1) {
            return kthSmallest(root.right, k - count - 1); // find k - (count +1) = k - count - 1
                                               // 1 is counted as current node
        }
    }
    
 
    //count the number of nodes on the left subtree (left node and right node of left subtree)
    public static int countNodes(TreeNode root) {
        if (root == null) 
        	return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    
    /** Prefer this -
     * Depth first search inorder traversal: Left subtree < ROOT < Right subtree
	 * recursive to count the number of nodes in left subtree: 
	 * if number count == kth, return the value of the element.
     */
    static int count;
    static int kth;

    public static int kthSmallest2(TreeNode root, int k) {
        inorder(root, k);
        return kth;
    }

    public static void inorder(TreeNode root, int k) {
        if ((root == null) || (count == k)) {
            return;            
        } 
        inorder(root.left, k);
        count++;
        
        if (count == k) {
            kth = root.val;
            return;
        }
        inorder(root.right, k);
    }
    
    
    //Breadth First Search (BFS) - Using Heap
    public int kthSmallest_BFS(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b - a);
        traverse(root, queue, k);
        return queue.poll();
    }
    
    public void traverse(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if(root == null) return;
        queue.add(root.val);
        if(queue.size() > k) queue.poll();
        traverse(root.left, queue, k);
        traverse(root.right, queue, k);
    }
   
	public static void main (String[] agrs) {
		
		//Input: [3,1,4,null,2]  k = 1
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		// left subtree
		root.left.right = new TreeNode (2); 
		
		int i = kthSmallest(root, 2);
		System.out.println("Smallest node returned: " + i); //smallest 1
		
		//Input: //root = [5,3,6,2,4,null,null,1], k = 3
		TreeNode root2 = new TreeNode(5);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(6);
		
		root2.left.left = new TreeNode (2); 
		root2.left.right = new TreeNode (4); 
		
		root2.left.left.left = new TreeNode (1);
		
		i = kthSmallest(root2, 3);
		System.out.println("Smallest node returned: " + i); //3
		
		 //      1
	    //  3       6 
	    //5   9   8
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(3);
		root3.right = new TreeNode(6);
		root3.right.left = new TreeNode(8);
		// left subtree
		root3.left.left = new TreeNode (5); //3 -> 5
		root3.left.right = new TreeNode (9); //3 -> 9
		
		//Input: [1,3,6,5,9,8,null]
		i = kthSmallest(root3, 3);
		System.out.println("Smallest node returned: " + i); //9
	
		
	}
}


