
package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
	public int val;
	public Node left;
	public Node right;
	  
	public Node(int x) {
		  val = x;
		  left = null;
		  right = null;
	}
}

public class MinimumEachLevelBT {
		
	public static List<Integer> minEachLevel(Node root) {
		List<Integer> result = new ArrayList<>();
	    
	    if (root == null) {
	        return result;
	    }
	    
	    Queue<Node> queue = new LinkedList<>();
	    queue.offer(root); //add the whole tree to queue
	    
	    while (!queue.isEmpty()) {
	
	        int levelSize = queue.size();  
	        int min = Integer.MAX_VALUE; 
	        
	        for (int i = 0; i < levelSize; i++) {
	            Node node = queue.poll(); // remove first node from the queue
	            min = Math.min(min, node.val);
	            
	            if (node.left != null) {
	                queue.offer(node.left); 
	            }								
	            
	            if (node.right != null) { 
	                queue.offer(node.right); 
	            }
	        }
	        result.add(min);
	    }
    
    return result;
	}

	
    public static void main(String[] args)  
    { 
        Node root =  new Node(1); 
        root.left = new Node(3); 
        root.right = new Node(5); 
        
        //left subtree
        root.left.left = new Node(9); 
        root.left.right = new Node(8); 
        
        //right subtree
        root.right.left= new Node(4);
        root.right.right = new Node(7);
        
        System.out.println("Minimum of each level in the tree is : " +  minEachLevel(root)); 
    } 
} 






