package binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/* 430. Flatten a Multilevel Doubly Linked List
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/ 
 
 You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in A SINGLE-LEVEL , doubly linked list. You are given the head of the first level of the list.

 

Example 1:

Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]

Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]

Explanation:

The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL
  

How multilevel linked list is represented in test case:

We use the multilevel linked list from Example 1 above:

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
             
The serialization of each level is as follows:

[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]

To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:

[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]

Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]


 */

public class FlattenMultiLevelDoublyLinkedList {
	class Node {
	    public int val;
	    public Node prev;
	    public Node next;
	    public Node child;
	}
	
	 public Node flatten(Node head) {
	      if (head == null) return head;
	      
	      Deque<Node> stack = new ArrayDeque<>();
	      Node cur = head;
	      
	      while (cur != null) {
	    	  if (cur.child != null) {
	    		  //push to stack
	    		  if (cur.next != null) {
	    			  stack.push(cur.next);
	    		  }
	    		  
	    		  cur.next = cur.child;
	    		  if (cur.next != null) {
	    			  cur.next.prev = cur;
	    		  }
	    		  cur.child = null;
	    		  
	    	  } else if (cur.next ==null && !stack.isEmpty()) {
	    		  cur.next = stack.pop();
	    		  if (cur.next != null) {
	    			  cur.next.prev = cur;
	    		  }
	    	  }
	    	  cur = cur.next;
	      }
	      return head;
    }
	 
}
