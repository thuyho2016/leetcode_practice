package linkedList;


/*
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

 */

public class ReverseNodesInKGroup {
	//prefer this
	/**
	For example:

	list = 1 -> 2 -> 3 -> 4 -> 5, k = 3
	
	1. Use a dummy head to simplify operations.
	   Dummy -> 1 -> 2 -> 3 -> 4 -> 5
	
	2. Use three pointers. The operation is similar to Leetcode#92 Reverse Linked List II.
	
	 - The pointer n will go k steps further.
	(If there are no k nodes further, it means we don't have to reverse these k nodes. ==> Stop. )
	 - The pointer p is always at the fixed position in this for-loop.
	 - The pointer c = p.next, which means the current node we want to move.
	 - The pointer start means the starting node for the next loop.
	
	Dummy -> 1 -> 2 -> 3 -> 4 -> 5
	   p     c         n
	         start
	
	Dummy -> 2 -> 3 -> 1 -> 4 -> 5
	   p     c    n    start
	
	Dummy -> 3 -> 2 -> 1 -> 4 -> 5
	   p     c         start
	         n
	
	 */
	
	public static Node reverseKGroup(Node head, int k) {
        Node dummy = new Node(0);
        Node start = dummy;
        dummy.next = head;
        
        while(true) {
            Node p = start, c, n = p;
            start = p.next;
            
            for(int i = 0; i < k && n != null; i++) 
            	n = n.next;
            
            if(n == null) break;
            
            for(int i = 0; i < k-1; i++) {
                c = p.next;  //swap
                p.next = c.next;
                c.next = n.next;
                n.next = c;
            }
        }
        return dummy.next;
    }
	

	//link all nodes together
	public static Node append(Node head, int data) {
		if (head == null)
			return head = new Node(data);  // create a head node
		else {
			if (head.next == null) {   
				return head.next = new Node(data);  // create next node after head
			} else {
				//use recursive
				return append(head.next, data);  
			}
		}
	}


    // print all nodes	
	//loop each element till end of the list, cur.next to move to next node
    //last node point to null
	public static void display(Node head) {
		if (head ==null) {
			return;
		}
		Node current = head;   // create temporary current node to point to head node
		
		while (current !=null) {
			System.out.print(current.val + " -> ");  //print current node with data
			current = current.next;      // move to next node
		}
		System.out.println(current); //last node is null 
	}
	
	public static void main (String[] agrs) {
		//create a link list 1->2->3->4->5
		Node head = new Node(1);
		Node nextNode = append(head , 2);
		nextNode = append(head , 3);	
		nextNode = append(head , 4);
		nextNode = append(head, 5);
		//display Linkedlist with all nodes
		display(head);
		
		Node ans = reverseKGroup(head, 2) ;
		//display Linkedlist with all nodes
		System.out.print("After reverse: ");
		display(ans); //2->1->4->3->5
	}	
}
