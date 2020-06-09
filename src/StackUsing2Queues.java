import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*  225. Implement Stack using Queues
 * https://leetcode.com/problems/implement-stack-using-queues/
 * 
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Example:

MyStack stack = new MyStack();

stack.push(1);
stack.push(2);  
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false             
 */

public class StackUsing2Queues {
	private int top;
	private Queue<Integer> q1 = new LinkedList<>();
	private Queue<Integer> q2 = new LinkedList<>();
	
	 // Push element x onto stack.  O(1)
	public void push(int x) {
		q1.add(x);
		top = x;
	}
	
	// Removes the element on top of the stack and returns that element. O(n) 
    //Time O(n), Space O(1)
	public void pop() {
    	while (q1.size() > 1) {
    		top = q1.remove();
    		q2.add(top);
    	}
    	
    	q1.remove();
    	Queue<Integer> tmp = q1;
    	q1 = q2;
    	q2 = tmp;
    }
    
    // Get the top element. Time & Space O(1)
    public int top() {
    	return top;
    }
    
    // Returns whether the stack is empty. - Time & Space complexity : O(1)
    public boolean empty() {
        return q1.isEmpty();
    }
     
   
   public static void main(String[] args) {

	   StackUsing2Queues s = new StackUsing2Queues();
	   s.push(1);
	   s.push(2);
	   System.out.println("peek() " + s.top()); //return 2	
	   s.pop();
	   System.out.println("poped " + s.top());  // return 1
	   System.out.println("Is queue empty ? " + s.empty());  // return false
	  
	   
   }
}