package design;

import java.util.LinkedList;
import java.util.TreeMap;

/* 716. Max Stack
https://leetcode.com/problems/max-stack/

Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
 */

public class MaxStackByTreeMap {
	
	class Node {
		//doubly-linked list implementation
		Node next;
		Node prev;
		int val;

		public Node(Node prev, Node next, int val) {
			this.prev = prev;
			this.next = next;
			this.val = val;
		}
	}

	TreeMap<Integer, LinkedList<Node>> treeMap;
	Node head;

	/** initialize your data structure here. */
	public MaxStackByTreeMap() {
		treeMap = new TreeMap<>(); //(val, node references)
		head = new Node(null, null, Integer.MIN_VALUE); //dummy
	}

	public void push(int x) {
		head.next = new Node(head, null, x); //push top of stack
		head = head.next; //update top of stack

		//update red-black tree, insert costs O(logn) b/c rebalancing
		if(!treeMap.containsKey(x)) treeMap.put(x, new LinkedList<Node>());

		//get costs O(logn) b/c traverse balanced BST
		treeMap.get(x).add(head);
	}

	public int pop() {
		int popped = head.val;
		delete(head); //O(1), also updates head

		//update red-black tree
		treeMap.get(popped).removeLast(); //topmost node will be last in linkedlist since linkedlist.add() appends nodes to end of list

		//remove takes O(logn) b/c rebalance
		if(treeMap.get(popped).isEmpty()) treeMap.remove(popped);
		return popped;
	}

	public int top() {
		return head.val;
	}

	public int peekMax() {
		return treeMap.lastKey(); //calls .get() which takes O(logn)
	}

	public int popMax() {
		Node max = treeMap.lastEntry().getValue().pollLast(); //will get and remove top-most max element

		if(treeMap.get(max.val).isEmpty()) treeMap.remove(max.val);

		delete(max);

		return max.val;
	}
    	

	//removes node n in O(1), also updates head
	void delete(Node n) {
		if(n==head) head = head.prev;

		n.prev.next = n.next; //n.prev shouldn't be null b/c pop is never called on empty stack
		if(n.next!=null) n.next.prev = n.prev; //n.next is null when n is head

		//optional
		n.prev=null; 
		n.next=null;
	}
	
   public static void main(String args[])
   {      
	  MaxStackByTreeMap stack = new MaxStackByTreeMap();
	  stack.push(5); 
	  stack.push(1);
	  stack.push(5);
	  System.out.println("top is " +stack.top());   // -> 5
	  
	  System.out.println("popMax() is " + stack.popMax());  //-> 5
	  System.out.println("top is  " + stack.top());       // -> 1
	  System.out.println("peekMax() " + stack.peekMax()); //-> 5
	  System.out.println("pop() is " + stack.pop());     //-> 1
	  System.out.println("top is " + stack.top());    //-> 5
	  
   }
}