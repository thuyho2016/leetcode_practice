package linkedList;

import java.util.HashMap;
import java.util.Map;
/*
 * Clone a linked list with next and random pointers:  5->4->3->2->1
 * 1. Traverse the original linked list and make a copy in terms of data.
 * 2. Make a hash map of key value pair with original linked list Node2 and copied linked list Node2.
 * 3. Traverse the original linked list again and using the hash map adjust the next 
 *    and random reference of cloned linked list Node2s.
 */

public class LinkedListClone
{
	//Linked List Node2 class
	static class Node
	{
		 int data;//Node2 data
		 Node next, random;//Next and random reference
		
		 //Node2 constructor
		 public Node(int data)
		 {
		     this.data = data;
		     this.next = this.random = null;
		 }
	}
	
	 Node head;//Linked list head reference
	
	 // Linked list constructor
	 public LinkedListClone(Node head)
	 {
	     this.head = head;
	 }
	
	 public LinkedListClone() {
		// TODO Auto-generated constructor stub
	}

	// push method to put data always at the head in the linked list.
	 public void push(int data)
	 {
	     Node node = new Node(data);
	     node.next = this.head;
	     this.head = node;
	 }
	
	 // Method to print the list.
	 void print()
	 {
		 while (head !=null) {
	    	 Node random = head.random;
	    	 int randomData = (random != null)? random.data: -1;
	    	 System.out.println("Data = " + head.data +
                     ", Random data = "+ randomData);
	    	 head = head.next;
	     }
	 }
	
	 /* Actual clone method which returns head reference of cloned linked list.
	  * Steps:
	  * 1. Initialize two references, one points to original list 's head
	  * 2. Hash map which contains Node2 to Node2 mapping - map.put( key, value) method to store orig and cloned LL
	  * 3. Traverse the original list and make a copy of that in the clone linked list
	  * 4. Traversal of original list again to adjust the next and random references of clone list using hash map.
	  */
	 public LinkedListClone clone()
	 {
	     //1. Initialize two references, one with original list's head.
	     Node origCurr = this.head;
	     Node cloneCurr = null;
	
	     //2. Hash map which contains Node2 to Node2 mapping of original and clone linked list.
	     Map<Node, Node> map = new HashMap<Node, Node>();
	
	     //3. Traverse the original list and make a copy of original list in the clone linked list.
	     while (origCurr != null)
	     {
	         cloneCurr = new Node(origCurr.data); //make a copy - create a new Node2 that data is copied from origin element
	         map.put(origCurr, cloneCurr);
	         origCurr = origCurr.next;  //move to next element
	     }
	
	     // Adjusting the original list reference again.
	     origCurr = this.head;
	
	     //4. Traversal of original list again to adjust the next
	     // and random references of clone list using hash map.
	     while (origCurr != null)
	     {
	         cloneCurr = map.get(origCurr);
	         cloneCurr.next = map.get(origCurr.next);   //assign next element in origCurr to next element of cloneCurr
	         cloneCurr.random = map.get(origCurr.random);
	         origCurr = origCurr.next;
	     }
	
	     //return the head reference of the clone list.
	     return new LinkedListClone(map.get(this.head));
	 }

	 // Main method.
	 public static void main(String[] args)
	 {
		 // Original Linkedlist: 5->4->3->2->1
		 
	     // Pushing data in the linked list.
	     LinkedListClone list = new LinkedListClone(new Node(5));
	     list.push(4);
	     list.push(3);
	     list.push(2);
	     list.push(1);
	
	     // Setting up random references.
	     list.head.random = list.head.next.next;
	     list.head.next.random = list.head.next.next.next;
	     list.head.next.next.random = list.head.next.next.next.next;
	     list.head.next.next.next.random = list.head.next.next.next.next.next;
	     list.head.next.next.next.next.random = list.head.next;
	
	     // Making a clone of the original linked list.
	     LinkedListClone clone = list.clone();
	
	     // Print the original and cloned linked list.
	     System.out.println("Original linked list");
	     list.print();
	     System.out.println("\nCloned linked list");
	     clone.print();
	 }
}

	  
	