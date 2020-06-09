package linkedList;

/*
 * 876. Middle of the Linked List
 * https://leetcode.com/problems/middle-of-the-linked-list/
 
Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.

Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])

The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.

Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.

 */

public class FindMiddleNodeOfLinkedList {
	/**
	 * count number of nodes in linked list, middle = length / 2
	 * return a linked list started from middle node 
	 */
	public static Node middleNode(Node head) {
       
        int count = 0;
        Node cur = head;  // is tmp node to point to head
        
		while (cur.next != null) {
			cur = cur.next; // move to next node
			count++;
		}
		
		int middle = count / 2;  // 4/2 = 2 , 5/2 =2
		
		cur = head;
		while ( middle != count) {
			cur = cur.next;
			count--;
		}
		return cur;
		
    }
	
	//link all nodes together
	public static Node append(Node head, int data) {
		if (head == null) {
			head = new Node(data);//create new head node
		} else {
			if (head.next == null)
				head.next = new Node(data); // create new node after head node
			else 
				append(head.next, data); // use recursive
		}
		return head;
	}
	
	public static void display(Node head) {
		if (head == null) return;
		
		Node cur = head;   // tmp node point to head
		while (cur != null) {
			System.out.print( cur.val + "->");  //print cur node
			cur = cur.next;           // move to next node 
		}
		System.out.print(cur);  // last node is null
		
	}
	
	public static void main (String[] agrs) {
		//create a link list [1,2,3,4,5]
		Node head1 = new Node(1);
		Node nextNode = append(head1, 2);
		nextNode = append(head1, 3);	
		nextNode = append(head1, 4);
		nextNode = append(head1, 5);
		//display Linkedlist with all nodes
		display(head1);
		
		Node mid = middleNode(head1);
		System.out.println(" Middle Node " + mid.val); //3
		
		//create a link list [1,2,3,4,5,6]
		Node head2 = new Node(1);
		nextNode = append(head2, 2);
		nextNode = append(head2, 3);	
		nextNode = append(head2, 4);
		nextNode = append(head2, 5);
		nextNode = append(head2, 6);
		//display Linkedlist with all nodes
		display(head2);
		
		mid = middleNode(head2);
		System.out.println(" Middle Node " + mid.val); //should return 4
		
	}	
}
