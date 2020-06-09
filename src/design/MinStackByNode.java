package design;
/* 155. Min Stack
https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element
.
getMin() -- Retrieve the minimum element in the stack.

 */

public class MinStackByNode {
	
	private Node top;
	
	class Node {
		private int value;
		private int min;
		private Node next;
	}
		
	
	public void push(int x) {
		Node node = new Node(); // create new node that is going to push
		node.value = x;
		
		if (top == null) {
			node.min = x;
			top = node;

		} else {
			
			if (top.min > x) {
				node.min = x;
			} else {
				node.min = top.min;
			}

			node.next = top;
			top = node;

		}
		System.out.println(x + " is pushed!");
	}

	public void pop() {
		if (top != null) {
			if (top.next == null) top = null;
			else {
				System.out.println(top.value + " is popped!");
				top = top.next;
			}
		} else {
			System.out.println("empty stack!");
		}
	}

	public int top() {
		if (top != null) {
			System.out.println(top.value + " is on top!");
			return top.value;
		} else
			throw new NullPointerException();
	}

	public int getMin() {
		if (top != null) {
			System.out.println(top.min + " is on min!");
			return top.min;
		} else
			throw new NullPointerException();
	}
	
	
   public static void main(String args[])
   {
	  MinStackByNode minStack = new MinStackByNode();
	  minStack.push(-2);
	  minStack.push(0);
	  minStack.push(3);
	  System.out.println( "Top is " + minStack.top()); //3

	  System.out.println( "GetMin is " + minStack.getMin()); // --> Returns -2.
	 
	  minStack.pop();    
	  System.out.println( "Top is " + minStack.top()); // --> Returns 0.

	  System.out.println( "GetMin is " + minStack.getMin()); //  --> Returns -2.
      
   }
}