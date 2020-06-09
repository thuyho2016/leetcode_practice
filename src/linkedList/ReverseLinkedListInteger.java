package linkedList;

/* 206. Reverse Linked List (easy level)
 * https://leetcode.com/problems/reverse-linked-list/
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

*/


public class ReverseLinkedListInteger {
	
  //create a LinkedList to link all nodes by insert last
  // a.next = b;  //1->2
  // b.next = c; // 1 -> 2 -> 3
	
  public static Node appendNodes(Node head,  int data) {
	  if (head == null)
		  return new Node(data);  // create a head node with data
	  else {  //heaad !=null
		  if (head.next == null) {
			  head.next = new Node(data); //create next node after head node
		  } else {  
			  //using recursive  
			  appendNodes(head.next, data);  // using recursive  A -> B  (head.next is B)
			  
			  //or using while loop 
		/*	  Node current = head;  // create a temporary node to point to head ( A-> B)
			  while (current.next != null) {    //current.next is B
				  current = current.next;
			  } // when current.next reach out in the end, it will exit while loop
			  
			  current.next = new Node(data); // assign data to the last node   A->B->C
			  */
		  } 
		  return head;
	  }
  }
  
  /** head = 1 -> 2 -> 3 -> null , 
  1st round: 
             nextTmp = head.next = 2 -> 3 -> null
             swap: head.next = prev = null ( head = 1 and pointing to null)
                   prev = head = 1->null 
                   head = nextTmp = 2->3->null
  2nd round:  
             nextTmp = head.next = 3->null
             swap: head.next = prev = 1->null
                   prev = head = 2->1->null
                   head = nextTmp = 3->null
  
  3rd round: 
             nextTmp = head.next = null
             swap: head.next = prev = 2->1->null
                   prev = head =  3->2->1->null
                   head = nextTmp = null
                   
  4th round: head = null , so dont go inside while loop
   */
  
  public static Node reverseList(Node head) {
	    Node prev = null;
	
	    while (head != null) {
	        Node nextTemp = head.next; //nextTemp = 2 -> 3 -> null. 2nd round: nextTemp = C -> null. 3rd round: nextTemp = null	        
	        //swap node
	        head.next = prev; // head.next = null because prev = null, so head = 1 -> null .  2nd round: head.next = 1 -> null. 3rd round: head.next = 2->1->null
	        prev = head;      // prev = 1 -> null . 2nd round: prev = 2-> 1->null.   3rd round: 3->2->1->null
	        head = nextTemp;  // head = 2 -> 3 -> null.  2nd round: head = C-> null. 3rd round: head =null
	    }
	 
		return prev;
	}  

  
  //print all nodes from linkedlist
  public static void print(Node head) {
	  Node p = head;   // create temporary p to point to head node
	  while (p != null) {
	    System.out.print(p.val + " -> ");
	    p = p.next;
	  }
	  System.out.println(p); // this line for print "null"
	}
  
  public static void main (String[] agrs) {
		
	  //create 3 nodes with string data
	  Node head = new Node(1);  //head node is the first node
	  appendNodes(head, 2); //second node
	  appendNodes(head, 3);
	  
	  //display Linkedlist with all nodes
	  print(head);
	  
	  //reverse all nodes
	  Node rev = reverseList(head);
	  System.out.print("After reverse, return prev: ");	
	  print(rev);
	
  	}
}

