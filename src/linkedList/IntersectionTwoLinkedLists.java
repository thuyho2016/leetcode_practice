package linkedList;

/*
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

List A: 4 -> 1 -> 8 ->4 -> 5
              
List B: 5 -> 0 -> 1 -> 8 -> 4 -> 5
begin to intersect at node 8.



Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8

Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). 
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. 
There are 2 nodes (4,1) before the intersected node in A; 
There are 3 nodes (5,0,1) before the intersected node in B.

Time complexity : O(m+n).

Space complexity : O(1).
 */

public class IntersectionTwoLinkedLists {
	
	public static Node getIntersectionNode(Node headA, Node headB) {
		if(headA == null || headB == null)
            return null;
        
        Node a = headA;
        Node b = headB;
        
        while(a != b){
        	
            if(a == null){
                a = headB;
            } else {
                a = a.next;
            }
            
            if(b == null){
                b = headA;
            } else {
                b = b.next;
            }
        }
        return a;
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
		if (head == null) {
			return;
		}
		
		Node cur = head;   // tmp node point to head
	    while(cur != null) {
        	System.out.print(cur.val + "->"); //print cur node
        	cur = cur.next;
	    }	    
		System.out.print(cur);  // last node is null
	}
	
// passed in Leetcode
	 public static void main(String args[])
	 { 
	
		  Node headA = new Node(4); //ListA: 4 -> 1 -> 8 ->4 -> 5
		/*  headA.next = new Node(1);	
		  headA.next.next = new Node(8);
		  headA.next.next.next = new Node(4);
		  headA.next.next.next.next = new Node(5);
		*/  
		  Node nextNode = append(headA, 1);
		  nextNode = append(headA, 8);
		  nextNode = append(headA, 4);
		  nextNode = append(headA, 5);
		  
		  Node headB = new Node(5); //List B: 5 -> 0 -> 1 -> 8 -> 4 -> 5
		/*  headB.next = new Node(0);
		  headB.next.next = new Node(1);
		  headB.next.next.next = new Node(8);
		  headB.next.next.next.next = new Node(4);
		  headB.next.next.next.next.next = new Node(5);
		 */
		  
		  nextNode = append(headB, 0);
		  nextNode = append(headB, 1);
		  nextNode = append(headB, 8);
		  nextNode = append(headB, 4);
		  nextNode = append(headB, 5);
		  
		  display(headA);
		  display(headB);
		  
		  Node intersection =  getIntersectionNode(headA,headB);
		  display(intersection);
	  
	 }
}
