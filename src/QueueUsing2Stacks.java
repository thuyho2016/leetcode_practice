import java.util.Stack;

/*  232. Implement Queue using Stacks
 * https://leetcode.com/problems/implement-queue-using-stacks/
 * 
 * given stacks be named as stack1 and stack2.
 * How to implement a queue simply using two stacks?

Example:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false

Queue is FIFO (first in - first out) data structure
Stack is LIFO (last in - first out) data structure

Steps of enqueue & dequeue:

 Enqueue : Push elements to stack1. ( E.g push a, b, c to stack1 )
 Dequeue : If stack2 is not empty, pop elements from stack2.
           Otherwise, pop elements from stack1 and push them to stack2 first.  
           		E.g pop c, b, a from stack 1 and push c, b, a to stack 2
           Then, pop elements from stack2.
   
   
Push - Time & Space complexity : O(n)
Pop - Time & Space complexity : O(1)
Peek - Time & Space complexity : O(1)

Solution: Two Stacks:  Push - O(n) per operation, Pop - O(1) per operation
             
Note:
push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.             
 */

public class QueueUsing2Stacks {
	private int front;
	private Stack<Integer> s1 = new Stack<Integer>();
	private Stack<Integer> s2 = new Stack<Integer>();
	
	//Push element x to the back of queue. Time O(1), Space O(n)
	public void push(int x) {
		if (s1.isEmpty()) {
			front = x;
		}
		s1.push(x);
	}
	
	 // Removes the element from in front of queue and returns that element. 
    public int pop() {
    	if (s2.isEmpty()) {
    		while (!s1.isEmpty()) {
    			s2.push(s1.pop());
    		}
    	}
        return s2.pop();
    }
    
    // Get the front element - Time and Space is O(1)
    //When s2 is not empty, front element is positioned on the top of s2
    public int peek() {
    	if (!s2.isEmpty()) {
    		return s2.peek();
    	}
        return front;
    }
    
    // Returns whether the queue is empty. - Time & Space complexity : O(1)
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
     
   
   public static void main(String[] args) {

	   QueueUsing2Stacks q = new QueueUsing2Stacks();
	   q.push(1);
	   q.push(2);
	   System.out.println("peek() " + q.peek()); //return 1	   
	   System.out.println("poped " + q.pop());  // return 1
	   System.out.println("Is queue empty ? " + q.empty());  // return false
	  
	   
   }
}