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

public class StackUsing1Queue {
	private int top;
	private Queue<Integer> q = new LinkedList<>();
	
	 // Push element x onto stack.  O(1)
	public void push(int x) {
		q.add(x);
		int size = q.size();
		
		while (size > 1) {
			q.add(q.remove());
			size--;
		}
	}
	
	// Removes the element on top of the stack and returns that element. O(n) 
    //Time O(n), Space O(1)
	public void pop() {
		if(q.isEmpty()){
		   System.out.println("No element present");
		}else{
			System.out.println(q.poll());
		}
    }
    
    // Get the top element. Time & Space O(1)
    public int top() {
    	return top;
    }
    
    // Returns whether the stack is empty. - Time & Space complexity : O(1)
    public boolean empty() {
        return q.isEmpty();
    }
     
   
   public static void main(String[] args) {
//wrong output
	   StackUsing1Queue s = new StackUsing1Queue();
	   s.push(1);
	   s.push(2);
	   System.out.println("p.top() " + s.top()); //return 2	
	   s.pop();
	   System.out.println("top " + s.top());  // return 1
	   System.out.println("Is queue empty ? " + s.empty());  // return false
	  
	   
   }
}