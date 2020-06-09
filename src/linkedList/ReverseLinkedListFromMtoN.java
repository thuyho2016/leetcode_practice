package linkedList;
/*
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

The basic idea is to build a sub-list when we hit Node m by adding the subsequent nodes to
the head of the sub-list one by one until we hit Node n.
Then connect the nodes before Node m, the sub-list and the nodes following Node n.
 */


public class ReverseLinkedListFromMtoN {	
	
    public static Node reverseBetween(Node head, int m, int n) {
    	if(head == null || m == n) return head;    
    	
        Node start = new Node(0);
   		start.next = head;
   		
   		Node tail = null;
   		Node beforeHead = start;
   		
   		for (int i = 1; i <= n; i++) {
   			if (i < m) {
   				beforeHead = head;
   				head = head.next;
   			} else if (i == m) {
   				tail = head;
   			} else {
   				beforeHead.next = tail.next;
   				tail.next = tail.next.next;
   				beforeHead.next.next = head;
   				head = beforeHead.next;
   			}
   		}
   		return start.next;
    }
      
    
    public static void printNodes(Node head){
    	if (head == null) {
    		return;
    	}
    	Node cur = head;
    	while ( cur != null) {
    		System.out.print(cur.val + "->");
    		cur = cur.next;
    	}
    	System.out.println(cur); 
    }

    public static void main(String[] args) {
    	// 1->2->3->4->5->NULL, m = 2, n = 4
    	//reverse from node 2 to node 4
    	
    	Node head = new Node(1);
		head.next = new Node(2);	
		head.next.next= new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		printNodes(head);
		
  		Node h = reverseBetween(head, 2, 4);
        printNodes(h);  //2->1->4->3.
    }
}