package linkedList;

import java.util.HashMap;

/*
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

Example 2:

 1    		2
 next    -> next -> NULL
 random  -> random <-

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 */


public class CopyLLWithRandomPointer {
	class Node {
	    public int val;
	    public Node next;
	    public Node random;

	    public Node(int v) { 
	    	val = v;
	    	next = null;
	        random = null;
	    }

	}
	
	// Use map - Time & space complexity O(n)
	public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();  // create a map with key is original node, value is a copy of the node
        
        Node dummy = head;
       
        while(dummy != null){
        	Node copyNode = new Node(dummy.val); 
            map.put(dummy, copyNode);
            dummy = dummy.next;
        }
        
        dummy = head;  // move the head to origin position
        while(dummy != null){
        	Node copyNode =  map.get(dummy);
        	copyNode.next = map.get(dummy.next);
        	copyNode.random = map.get(dummy.random);
            dummy = dummy.next;
        }

        return map.get(head);
        
    }
	
	public Node copyRandomList2(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>(); // key is original node, value is a copy of the node
        
        Node dummy = new Node(0); // make a dummy node to point to the head 
        dummy.next = head;
        
        while (head != null) { // go through the list
        	Node copyNode = new Node(head.val);  // make a copy of the head with value and put in the map
        	map.put(head, copyNode);
        	head = head.next;  // move the head to next node
        }
        
        head = dummy.next;  // move the head to origin position
        while (head != null ) { // go through the list again
        	Node copyNode = map.get(head); // get each node based on the origin node
        	copyNode.next = map.get(head.next);  // assign next node to the next node of coypNode
        	copyNode.random = map.get(head.random);
        	head = head.next;
        }
        
        return map.get(dummy.next); // return hash value of the map
        
	}   
	
	
	//better way, but hard to understand  - modify the list to duplicate node  1->1->2->2->3->3->null
	// 1 ->  2 ->  3
	// rand  rand  rand
	// 2  	2  		1
	// Time O(n), Space O(1)
	public Node copyRandomList3(Node head) {
		if (head == null) return head;
		
		// Creating a new weaved list of original and copied nodes.
	    Node copyNode = head;
	    while (copyNode != null) {

	      // Cloned node
	      Node newNode = new Node(copyNode.val);

	      // Inserting the cloned node just next to the original node.
	      // If A->B->C is the original linked list,
	      // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
	      newNode.next = copyNode.next;
	      copyNode.next = newNode;
	      copyNode = newNode.next;
	    }

	    copyNode = head;

	    // Now link the random pointers of the new nodes created.
	    // Iterate the newly created list and use the original nodes' random pointers,
	    // to assign references to random pointers for cloned nodes.
	    while (copyNode != null) {
	      copyNode.next.random = (copyNode.random != null) ? copyNode.random.next : null;
	      copyNode = copyNode.next.next;
	    }

	    // Unweave the linked list to get back the original linked list and the cloned list.
	    // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
	    Node ptr_old_list = head; // A->B->C
	    Node ptr_new_list = head.next; // A'->B'->C'
	    Node head_old = head.next;
	    
	    while (ptr_old_list != null) {
	      ptr_old_list.next = ptr_old_list.next.next;
	      ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
	      ptr_old_list = ptr_old_list.next;
	      ptr_new_list = ptr_new_list.next;
	    }
	    return head_old;
		
	}
	public static void main (String[] agrs) {
		//[[7,null],[13,0],[11,4],[10,2],[1,0]]
		//[[1,1],[2,1]]
		
	}
}
