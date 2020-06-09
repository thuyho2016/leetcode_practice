package linkedList;

/*
 21. Merge Two Sorted Lists ( Easy level)
https://leetcode.com/problems/merge-two-sorted-lists/
 
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4

Time and Space complexity : O(n + m)

Steps:

 1. Merge two sorted linked lists by recursive
 2. return it as a new list.
 

*/

public class MergeTwoSortedListNodes {
	
	// Prefer solution: use recursive
	//Space complexity : O(n + m)
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
	
	//Way 2: Space complexity : O(1)
	public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

	    if(l1 == null) return l2;
	    if(l2 == null) return l1;
	    
	    ListNode pointer = new ListNode(-1);  
	    ListNode merge = pointer;
	    
	    while(l1 != null  && l2 != null  ){
	    	
	        if( l1.val <= l2.val){
	            pointer.next = new ListNode(l1.val);
	            l1 = l1.next;
	        } else { 
	           pointer.next = new ListNode(l2.val);
	           l2 = l2.next;
	        }
	        pointer = pointer.next;
	    }
	    
	    if(l1 != null) pointer.next = l1;
	    if(l2 != null) pointer.next = l2;
	    
	    return merge.next;
	} 
	
	public static ListNode display(ListNode a) {
		if (a == null) {
			return a;
		}
	    while(a != null) {
        	System.out.print(a.val + "->");
        	a = a.next;
	    }
        System.out.println(a); // null	    
	    
	    return a;
	}
	
	 public static void main(String args[])
	 { 
	
		  ListNode head1 = new ListNode(1); //List1: 1 -> 2 - > 4
		  head1.next = new ListNode(2);	
		  head1.next.next = new ListNode(4);	
		  
		  ListNode head2 = new ListNode(1); //ListNode2: 1 -> 3 -> 4
		  head2.next = new ListNode(3);
		  head2.next.next = new ListNode(4);
		  
		  display(head1);	
		  display(head2);
		  ListNode merged = mergeTwoLists(head1,head2);
		  display(merged);
	  
	 }
}
