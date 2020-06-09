package binarytree;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 98. Validate Binary Search Tree
 * Binary Search Tree is a binary tree that has values of all the nodes in left subtree is less than or equal root 
   and values of all nodes in the right subtree greater than the root node value
    left subtree < root < right subtree
    
  check if a binary tree is BST or not?
  - all nodes in the left subtree have values less than the root node 's value.
  - All node in the right subtree have values greater than the root node's value.
  - Both the left and right subtrees must also be binary search trees.

 * 	Height of a node is number of edges in longest path from root to the deepest leaf node
  	Depth of a node is number of edges in path from root to that node.
   	Height of tree is a height of the root.
 	Height of tree with 1 node = 0
 
 	
 	Example: 1 -> 2-> 3 , 2-> 5 -> 8 -> 9 ( 8, 9 is child nodes of 5)
 	For node 2: Depth = 1 , height = 2 


 * How to traverse the tree?
 
 * BFS (Breadth First Search or Level-Order Traversal)
    It is a recursive algorithm that processes the root, followed by the children from left to right
    Root -> Left -> Right
       
       4(level0) ->2->6 (level1) ->1->3->5->7 (level2)
  

 * DFS (Depth First Search) - go deeply children before traversing neighbor
   1. Inorder Traversal:  Left subtree -> Root -> Right subtree		
   2. Preorder Traversal:   Root -> Left subtree -> Right subtree
   3. Postorder Traversal:  Left subtree -> Right subtree -> Root
     


Write the following methods:

 * 1. Validate Binary Tree by recursive (left subtree < node's key < right subtree)
 * 		Binary tree [2,1,3], return true
 * 		Binary tree [1,2,3], return false
 * 
 * 2a. Find height of a Binary Tree (is a height of the root)
 * 2b. Find Depth of a node 
 * 
 * 3. Insert node to Binary tree

 * 4. Delete node
      if node is not in a tree
	  if node is a leaf (no child)
	  if node has only one child
	  if node has two children. 
	  
	  https://leetcode.com/problems/delete-node-in-a-bst/
	  
   5. Print all nodes of Binary Tree by Breath First Search (level-order traversal) using QUEUE
 
 */

public class ValidateBT_InsertDeleteNode_Print_ByBFS {
	
	//1. Validate Binary Tree by recursive
	public static boolean isValidBST(TreeNode root , int min, int max){
	    if(root == null) 
	        return true;
	    // p is a binary tree if p. val > min && p.val < max , return true
	    if(root.val <= min|| root.val >= max)
	        return false;
	    
	     //left subtree < root's value   & root's value < right subtree
	    return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
		
	}
	
	//2a. Find height of a Binary Tree by recursive call
	// Height of a node = is number of edges in longest path from root to a leaf node
	public static int getHeight(TreeNode root) {

		int heightLeft = 0;
		int heightRight = 0;
		
		if(root == null) {
           return -1;
		}
		
		if(root.left != null) {
			heightLeft = getHeight(root.left);
		}
		if(root.right != null) {
			heightRight = getHeight(root.right);
		}
		
		return (heightLeft > heightRight ? heightLeft + 1 : heightRight + 1); //return Math.max(heightLeft, heightRight) + 1
		
	}
	
	//2b. Depth of a node 
	//Depth of a node is the number of edges in path from root to that node.
	public static int maxDepth(TreeNode node) 
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }
	
	
	//3. Insert node to binary tree by recursive
	public static TreeNode insert(TreeNode root, int data) { // passing treenode , data to be added
		if (root ==null) {
			return new TreeNode(data) ; // return 1 node that is a root
		}
		else {
			//TreeNode current;
			if (root.val >= data) {  //compare data and root's data. If data is small, go to left sub tree
				root.left = insert(root.left, data); // if yes, insert it as the node's left 
			} else {                   
				root.right = insert(root.right, data);  //insert to right subtree
			}
		}
		return root;
	}	

	
	
	/* 4. Delete node:
	 * Case 1: Node to be removed has no children
	 * Case 2: Node to be removed has one child
	 *	       - Node is cut from the tree and single child is directly to the parent of the removed node.
	 * 
	 * Case 3: Node to be removed has two children
	    	- find a minimum value in the right subtree.
			- replace value of the node to be removed with found the minimum value. 
			   Now, right subtree contains a duplicate! ( two nodes has the same value)
			- remove a duplicate from the right sub-tree.
	 */
	
	 public static TreeNode deleteNode2(TreeNode root, int key) {
        if(root == null) return root;
        
        if(key == root.val) {
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            
            TreeNode deleted = root;
            
            //Case 3: Node to be removed has 2 children. find a minimum value in the right subtree
            root = min(root.right); 
            root.right = deleteMinHelper(deleted.right);
            root.left = deleted.left;
            
        } else if(key < root.val) {
            root.left = deleteNode2(root.left, key); //go to left subtree
        } else {
            root.right = deleteNode2(root.right, key); //go to right subtree
        }
        return root;
	 }
	    
    private static TreeNode min(TreeNode root) {
        while(root.left != null) root = root.left;    
        return root;
    }
    
    //find a minimum value in the right subtree;
    private static TreeNode deleteMinHelper(TreeNode curr) {
        if(curr.left == null) return curr.right;
        curr.left = deleteMinHelper(curr.left);
        return curr;
    }
	  
    
	//4. Delete Node = Prefer way2 :find the node with value k and delete it.
	public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (root.val > key) {   // go to left subtree
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key); //go to right subtree
        } else { //key == root.val
        	
            // case 1: where node is last node - no child
            if (root.left == null && root.right == null) {
                root = null;
            } 
            
            // case 2: node has only one child -  where it has either left node or right node
            else if (root.right == null) {
                root = root.left;
            } else if (root.left == null) {
                root = root.right;
                
            } else { //case 3: node has 2 subtrees
                /* Steps: 1. find max value of node in left subtree
            	      2. copy the value to targeted node ( the node being deleted)
            	      3. delete duplicate max (largest) node from left-subtree
            	      
            	*/
                // 1. get data from the rightmost node in the left subtree
                root.val = retrieveData(root.left);
                // 2. delete the rightmost node in the left subtree
                root.left =  deleteNode(root.left, root.val) ;

            }
        }
        return root; // for 3 cases
    }
	
	// get data from the rightmost node in the left subtree
	private static int retrieveData(TreeNode p)
	{
      while (p.right != null) p = p.right;

      return p.val;
	}
	
	
	/** 5. Print Binary Tree Paths by BFS using QUEUE (FIFO)
	print value of each node in the tree's level-order traversal (BFS) is level by level
	We traverse each level of the tree from the root downward and we process the nodes at each level from left to right.
	
	Level-order traversal is 3 (level 0) -> 2 -> 5 (level 1) -> 1-> 4 -> 7 (level 2)
	Time complexity = O(n)
	Space complexity = O(1) for best case, worst case is O(n)
	*/
	
	public static void print_levelOrderTraversal(TreeNode root) {
		if (root == null) return;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
	
		while(!queue.isEmpty()){ // the queue should has at least one node
				        
			TreeNode current = queue.poll(); // or queue.remove();  remove() throws an exception if queue is empty
			System.out.print(current.val + " "); //Retrieves and removes the head of this queue. Returns null if this queue is empty.
	
			// put children to the queue
			if (current.left!=null)
				queue.add(current.left);
			
			if (current.right!=null) 
				queue.add(current.right);
		}

	}	
	
	//return list of list
	public static List<List<Integer>> returnLists_levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {

            List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left); //left subtree is added to queue at index [0]
                }
                
                if (node.right != null) {
                    queue.offer(node.right);  //right subtree is added to queue at index [1]. Size of queue is 2
                }                
            }
            result.add(level);
        }
        
        return result;
    }

	public static void main (String[] agrs) {
		TreeNode t = new TreeNode(2);
		boolean isValid = isValidBST(t, 1, 3); 
		System.out.println("Is Binary Tree? " + isValid);  //true
		
		isValid = isValidBST(t, 4, 3);  
		System.out.println("IsBTree? " + isValid);  //false
		
		System.out.println("Height of Binary Tree : " + getHeight(t));
		System.out.println("Depth of tree: " + maxDepth(t));
		
		
		//insert new node with data 7
		insert(t, 1);
		insert(t, 3);
		insert(t, 6);
		insert(t, 7);
		System.out.println("After insert, Height of Binary Tree : " + getHeight(t));
		System.out.println("After insert, Depth of node: " + maxDepth(t));
		

		//Tree: 1 -> 2-> 3 , 2-> 5 -> 8 -> 9
	    //Path:	[1->2->5->8, 1->2->5->9, 1->3]
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5); //2 -> 5
		root.left.right.left = new TreeNode(8);
		root.left.right.right = new TreeNode(9);	

		System.out.println("Height of Binary Tree1 : " + getHeight(root));
		System.out.println("Depth of node: " + maxDepth(root));
				
		System.out.println("Binary Tree1 in BFS - Level Order Traversal");
		print_levelOrderTraversal(root);
		
		
		TreeNode root2 = new TreeNode(1);
		root2.right = new TreeNode(2); //1 -> 2
		root2.right.right = new TreeNode(5); //2 -> 5
		root2.right.right.left = new TreeNode(3); //5 ->3
		root2.right.right.right = new TreeNode(6); //5->6
		root2.right.right.left.right = new TreeNode(4); //3->4
		System.out.println("\nBinary Tree2 in BFS - Level Order Traversal");
		print_levelOrderTraversal(root2); //1 -> 2 -> 5 -> 3 -> 6 -> 4.
		
		TreeNode newTree1 = deleteNode(root2, 3);
		System.out.println("\nBinary Tree2 after delete 3");
		print_levelOrderTraversal(newTree1);
			
		/*
    	root = [5,3,6,2,4,null,7]
		delete key = 3

		    5
		   / \
		  3   6
		 / \   \
		2   4   7
		
		 */
		TreeNode root3 = new TreeNode(5);
		root3.right = new TreeNode(6); //5 ->6
		root3.left = new TreeNode(3);
				
		root3.right.right = new TreeNode(7); //6 -> 7
		
		root3.left.left = new TreeNode(2); //6 ->3
		root3.left.right = new TreeNode(4); //3->4
	
		System.out.println("\nBinary Tree3 in BFS - Level Order Traversal");
		print_levelOrderTraversal(root3); 
		
		TreeNode newTree2 = deleteNode(root3, 3); 
		System.out.println("\nBinary Tree3 after delete 3");
		print_levelOrderTraversal(newTree2);   //5 2 6 4 7
		
		List<List<Integer>> list = returnLists_levelOrder(root3);
		System.out.println(list);  // [[5], [2, 6], [4, 7]]
		
	}
}

	  
	