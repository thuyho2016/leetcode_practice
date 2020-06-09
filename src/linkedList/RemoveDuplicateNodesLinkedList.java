package linkedList;

import java.util.HashSet;
import java.util.Set;

/* 
 * 83. Remove Duplicates from Sorted List ( easy level)
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * 
 * Remove duplicate nodes in LinkedList. (Apple interview)
 * 1->2->2->3->3->4
 * Output: 1->2->3->4
 * 
 * Solution1: HashSet
 * 1. Put LinkedList in HashSet - use add method 
 *  - use prevNode point to head node
 *  if hashset doesn't contain current node, then add it, move prevNode to current node and current node moves to next node.
 * 2. write a method to display LinkedList by HashSet
 * 
 * 
 * Solution 2: use val 
 */

public class RemoveDuplicateNodesLinkedList {
	/*  public class Node {
		    int val;
		    Node next;
		     
		     Node(int x) 
		     { val = x; }
		 }
		 */

	
	 //Straight-Forward Approach - Time complexity : O(n) , Space complexity : O(1), No additional space is used.
	 public static Node removeDuplicates(Node head) {
		 if(head == null)
			  return null;
		 
		 Node cur = head;
		 
		 while(cur.next != null){
			 if(cur.val == cur.next.val)
				 cur.next = cur.next.next; // skip node that duplicate next node
			 else
				 cur = cur.next;
		 } 
		 return head;
	 }
	 
	//Use recursive  -- accepted
	 public static Node removeDuplicates2(Node head) {
		 if (head == null || head.next ==null)
			 return head;
		 
		 if (head.val == head.next.val) {
			 head.next = head.next.next; // skip the node which is duplicated
			 removeDuplicates2(head);
		 } else { //not duplicate
			 removeDuplicates2(head.next); //then move to next Node and check
		 }
		 return head;
	 } 
		 
	public static Node removeDuplicates_HashSet(Node head) {
    	Set<Integer> set =new HashSet<Integer>();
  
    	if (head == null)
    		return null;
	   
    	Node prev = head;       // point to 1->2->2->3->3->4
    	Node cur = prev.next;   // point to 2->2->3->3->4
	   
    	if(cur == null)   
    		return head;
    	
    	set.add(prev.val);  // add node 1 to HashSet: set = [1]

	    while(cur != null)  // exit while loop when hitting last node
	    {
	        if(set.contains(cur.val)) // cur is pointing 2->3->3->4, so set already contains [1,2], 2 duplicate
	        {
	        	prev.next= cur.next; // skip the cur node, move to next node: prev = 2->3->3->4
	        	cur = cur.next;      //3->3->4
	        }
	        else{                   // set doesn't have node 2, then add 
	        	set.add(cur.val);   // add cur.val = 2 to HashSet, so set = [1, 2]
	        	prev = cur;         // move from prev to cur  2->2->3->3->4
	        	cur  = cur.next;    // move cur to next node 2->3->3->4
	        }
	        // line 97 and 102 can write here
	    }
	    return head;
	}
	
	
	//create a Tree - insert/append node in the end by recursive
	public static  Node insert(Node head, int data)
    {	if (head == null) { 
    		return new Node(data); //create a new node with data. it is a head node /first node
    	}
        else if (head.next == null) {
    		head.next = new Node(data);
    	} else {  // head.next is not null - using recursive to move to next node 
    		insert(head.next, data);
    	}
		return head;
    }
		
	public static Node display(Node a) {
		if (a == null) {
			return a;
		}
	    while(a != null) {
        	System.out.print(a.val + "->");
        	a = a.next;
	    }
        System.out.print(a); // null	    
	    
	    return a;
	}
	
	public static void main (String[] agrs) {
		
		// Add nodes together  {1,2,2,3,3,4}
    	Node head = new Node(1);
    	head = insert(head, 2);
    	head = insert(head, 2);
    	head = insert(head, 3);
    	head = insert(head, 3);
    	head = insert(head, 4);
		
		System.out.print("Display a linklist: ");
		//display all nodes
		display(head);
		
		System.out.print("\nAfter remove duplicates: ");
		removeDuplicates(head);
		//removeDuplicates2(head);	
		//removeDuplicates_HashSet(head);
		
		display(head);
	}
}


