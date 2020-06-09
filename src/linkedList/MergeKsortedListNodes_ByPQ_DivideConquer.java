package linkedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 23. Merge k Sorted Lists 
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Merge k sorted linked lists and return it as one sorted list.
 * 
 Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6


 */

public class MergeKsortedListNodes_ByPQ_DivideConquer {
	
  /*  private class ListNode {
    	int val;
    	ListNode next;
    	
    	ListNode (int x) {
    		val = x;
    	}
    	
    	ListNode( int x, ListNode next) {
    		val = x;
    		this.next = next;
    	}
    }	*/
    
   //use Priority Queue - Time complexity :)O(Nlogk) , k is the number of linked list. Priority cost O(k) space
    public ListNode mergeKLists_PQ(ListNode[] lists) {
    	if (lists == null || lists.length ==0) return null;
    	
  
    	// Java 7
    /*	Comparator<ListNode> cmp = new Comparator<ListNode>() {
	    	@Override
	    	public int compare(ListNode l1, ListNode l2) {
	    		return l1.val - l2.val;
	    	}
    	};  
    	   		
    	Queue<ListNode> q = new PriorityQueue<ListNode> (cmp); //or PriorityQueue<ListNode>(lists.length, comparator)
    */	
    	
    	//Java 8 - sort with a Lamdra expression
    	PriorityQueue<ListNode> q = new PriorityQueue<ListNode>((a,b) -> a.val - b.val);
    	 
    	 
    	//add ListNode to queue
    	for (ListNode l: lists) {
    		if (l != null) 
    			q.add(l); // or q.offer(l);
    	}
    	
    	ListNode dummy = new ListNode(0); // head
    	ListNode pre = dummy; // user 2 pointers to 
    	
    	while (!q.isEmpty()) {
    		
    		ListNode front = q.poll(); //remove head of the queue which is first node
    		pre.next = front;   // then assign the head node to pre.next
    		
    		if (front.next != null) {
    			q.add(front.next) ;// or q.offer(...);
    		}
    		pre = pre.next; // important
    		
    	}
    	return dummy.next;
    	
    }
 	  
	/* Way 2: Merge K ListNodes by Divide and Conquer - passed LC  
    To Merge K ListNodes,  (use recursive)
    Steps: 
    * Divide the unsorted array into 2 parts - find middle 
    * sorts the left side of the array by recursive:  mergeSort(arr, l, m)
    * sorts the right side of the array by recursive  mergeSort(arr, m+1, r)
    * merge both sides together merge(arr, l, m, r) .It is based on merge two sorted
      
    
     Time complexity : O(Nlogk) where k is the number of linked lists.
     Space complexity : O(1)
      
     */
    
	  public ListNode mergeKLists(ListNode[] lists) {
		  
		  if (lists == null || lists.length ==0) return null;
	
		  ListNode head = merge (lists, 0, lists.length - 1); 
		  return head;
		//return merge(lists, 0, lists.length - 1); 
	  }
	  
	
	  public ListNode merge(ListNode[] lists, int low, int high) {
		  
		  if (low ==  high) return lists[low];
	      
		  if (low < high) {
			  int mid = low + (high - low) / 2;
			  
	    	  ListNode l1 = merge(lists, low, mid);
	    	  ListNode l2  = merge(lists, mid + 1, high);
	    	   
	    	  return mergeTwoLists(l1, l2);
		        
		  } else {
	    	   return null;
		  }
	     
	  }
	  		
	  public ListNode mergeTwoLists(ListNode n1, ListNode n2)
	  {
		if (n1 == null) return n2;
		if (n2 == null) return n1;
		
		//compare value of node 1 and node 2
		if (n1.val < n2.val)
		{ 
			n1.next = mergeTwoLists(n1.next, n2);
			return n1;
		}
		else
		{
			n2.next = mergeTwoLists(n1, n2.next);
			return n2;
		}
	  }
		
	  public void display(ListNode head){
			
		  while(head != null){
			  System.out.print(head.val + " -> ");
			  head = head.next;
		  }
		  System.out.println(head); //null
		 // System.out.println(" "); 
	  }
	  
	  public static void main(String args[])
	  {
		 
		  MergeKsortedListNodes_ByPQ_DivideConquer obj = new MergeKsortedListNodes_ByPQ_DivideConquer();
		  
		  //create List1: 1 -> 4 - > 5

		  ListNode head1 = new ListNode(1); 
		  ListNode n4 = new ListNode(4);
		  ListNode n5 = new ListNode(5);
		  head1.next = n4;
		  head1.next.next = n5;	
		  obj.display(head1);
				  
		  ListNode head2 = new ListNode(1);
		  ListNode n3 = new ListNode(3);
		  ListNode n4b = new ListNode(4);
		  
		  // create ListNode2: 1 -> 3 -> 4
		  head2.next = n3;
		  head2.next.next = n4b;
		  obj.display(head2);
		  
		  // create ListNode3: 2 -> 6
		  ListNode n6 = new ListNode(6);
		  ListNode head3 = new ListNode(1);	
		  head3.next = n6;
		  obj.display(head3);
		  
		  List<ListNode> lists = new ArrayList<ListNode>(); // [[1,4,5],[1,3,4],[2,6]]
		  lists.add(head1);
		  lists.add(head2);
		  lists.add(head3);
		 
	     // obj.display(obj.mergeKLists(lists));  //1->1->2->3->4->4->5->6
	  
		  
		  /* Input:[1->3->5, 
		   * 2->4->6, 
		   * 5->6  ]
		     Output: 1->2->3->4->5->5->6->6
		    */
		  
	  }
}