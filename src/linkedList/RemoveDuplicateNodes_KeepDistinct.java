package linkedList;
	
/* 
 *  82. Remove Duplicates from Sorted List II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5

Example 2:

Input: 1->1->1->2->3
Output: 2->3
 
 
 */

public class RemoveDuplicateNodes_KeepDistinct
{
	// Use recursive -prefer this
    public static Node deleteDuplicates2(int n, Node head) {
        if (head == null || head.next == null) return head;
        
        if (head.val != n) {
            head.next = deleteDuplicates2(n, head.next);
            return head;
        }
        
        // When the current heads are followed with a bunch of duplicates;
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates2(n, head.next);
    }
    
    public static Node deleteDuplicates(Node head) {
        if (head == null || head.next == null) return head;
        
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        
        // When the current heads are followed with a bunch of duplicates;
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    }
    
    
	 //Iterative approach
	 public static Node removeDuplicates(Node head) {
		 if(head == null || head.next ==null)
			return head;
		 
		 Node newhead = new Node(0);
		 newhead.next = head;   //newhead is 0->1->1->1->2->3->null
		 
		 Node cur = newhead;	//0->1->1->1->2->3->null	 
		 
		 while(cur.next != null && cur.next.next != null){ //cur.next = 1 == cur.next.next = 1
			 //continue when no duplicate
			 if(cur.next.val != cur.next.next.val) { //cur.next.val = 2
				cur = cur.next;  
				continue;
			 }
			 
			 Node prev = cur;  //0->1->1->1->2->3->null
			 cur = cur.next;   //1->1->1->2->3->null
			 
			 while (cur.next != null && cur.val == cur.next.val) { //1 == 1 found duplicate, then move to next node 
				 cur = cur.next;  
			 }
			 //now, cur is 1->2->3->null
			 prev.next = cur.next; // cur.next points to 2->3-> null, and assign 2->3->null to prev.next, so pre become 0->2->3->null
			 cur = prev;  // then assign 0->2->3->null  to cur, but newhead is cur
			 
		 } 
		 return newhead.next;
	 }
	 

 
	//create a Tree - insert node in the end by recursive
	public static  Node insert(Node head, int data)
    {	if (head == null) 
    		return new Node(data); //create a new node with data. it is a head node /first node
    	else if (head.next == null) {
    		head.next = new Node(data);
    	} else {  // head.next is not null - using recursive to move to next node 
    		insert(head.next, data);
    	}
		return head;
    }
		
	 // print linked list
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
	 
    public static void main(String args[])
    {  
    	// Add nodes together  {1,2,3,3,4,4,5}
    	Node head = new Node(1);
 
    	head = insert(head, 3);
    	head = insert(head, 2);
    	head = insert(head, 3);
    	head = insert(head, 3);
    	//head = insert(head, 5);
		
    	System.out.print("Display a linklist: ");
    	display(head);
    	
    	System.out.println("\nRemove all duplicates and keep distinct "); 
    	head = removeDuplicates(head);    // 1->2->5
    	//head = deleteDuplicates(head);
        display(head);
        
       // 1->1->1->2->3
        Node head2 = new Node(1);
    	head2 = insert(head2, 1);
    	head2 = insert(head2, 1);
    	head2 = insert(head2, 2);
    	head2 = insert(head2, 3);
		
    	System.out.print("\nDisplay a linklist: ");
    	display(head2);
    	
    	System.out.println("\nRemove all duplicates and keep distinct "); 
    	head2 = removeDuplicates(head2);  //2->3
    	//head2 = deleteDuplicates(head2);
    	display(head2);
    }

	
}