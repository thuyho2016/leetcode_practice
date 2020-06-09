package linkedList;

/* 143. Reorder List
 * https://leetcode.com/problems/reorder-list/
 * 
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.


Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/


public class ReorderList {
	
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

			  Node current = head;  // create a temporary node to point to head ( 1-> 2)
			  while (current.next != null) {    //current.next is 2
				  current = current.next;
			  } // when current.next reach out in the end, it will exit while loop
			  
			  current.next = new Node(data); // assign data to the last node   1->2->3
		  } 
		  return head;
	  }
  }
  
  /**
   * 3 steps;
   * 1. Find middle node
   * 2. Reverse the second half
   * 3. Merge the firstHalf and secondHalf
   * 
   */
  public static void reorderList(Node head) {
	  if (head == null || head.next == null || head.next.next == null) {
		  return;
	  }
	  
	  Node two = findMid(head);
	  two = reverse(two);
	  print(two);
	  merge(head, two);
  }  
  
  private static Node findMid(Node head) {
	  // only one element would be hard in this case, assume already at least two elements
      Node slow = head;
      Node fast = head;
      
      while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next;
        slow = slow.next;
      }
      
      Node returnNode = slow.next;
      slow.next = null; 
      
      return returnNode;
  }
  
  private static Node reverse(Node head) {
	    Node prev = null;
	 
	    //1st round: head = 1 -> 2 -> 3 -> null , 
	    //In 2nd round: head = 2 -> 3 -> null 
	    //In 3rd round: head = 3- > null
	    //4th round: head = null , so dont go inside while loop
	    while (head != null) {
	        Node nextTemp = head.next; //nextTemp = B -> C -> null. 2nd round: nextTemp = C -> null. 3rd round: nextTemp = null
	        
	        //swap node
	        head.next = prev; // head.next = null because prev = null (so, head = A -> null) .  2nd round: head.next = A -> null. 3rd round: head.next = B->A->null
	        prev = head;      // prev = A -> null above. 2nd round: prev = B-> A->null. 3rd round: C->B->A->null
	        head = nextTemp;  // head = B -> C -> null; 2nd round: head = C-> null. 3rd round: head =null
	    }
	    return prev;
	}  

  private static Node merge(Node first, Node second) {
	  Node dummyHead = new Node(0); 
      Node cur = dummyHead;
      
      while (first != null && second != null) {
        cur.next = first;
        first = first.next;
        
        cur = cur.next;
        cur.next = second;
        second = second.next;
        cur = cur.next;
      }

      if (first != null) {
        cur.next = first;
      }
      return dummyHead.next;
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
		
	  //Given 1->2->3->4,
	  Node head = new Node(1);  //head node is the first node
	  appendNodes(head, 2); //second node
	  appendNodes(head, 3);
	  appendNodes(head, 4);
	  
	  //display Linkedlist with all nodes
	  print(head);
	  
	  //reverse all nodes
	  reorderList(head);
	  System.out.print("After reorder: ");	
	  print(head);
	
  	}
}

