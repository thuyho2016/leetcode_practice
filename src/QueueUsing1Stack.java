
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*  232. Implement Queue using Stacks
 * https://leetcode.com/problems/implement-queue-using-stacks/
 
 * How to implement a queue simply using ONE stack?

Example:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false

 */

public class QueueUsing1Stack {
	Stack<Integer> s = new Stack<>();
	private Integer first = null;
	
	
	// Push element x to the back of queue.
	public void push(int x) {
	    if(first == null){
	        first = x;
	    }
	    s.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
	    List<Integer> temp = new ArrayList<>();
	    Integer last = null;
	    first = null;
	   
	    do{
	        last = s.pop();
	        if(s.isEmpty()){
	            break;
	        }
	        first = last;
	        temp.add(last);
	        
	    }while (true);
	    
	    for(int i = temp.size()-1; i >=0; i--){
	        s.push(temp.get(i));
	    }
	 
	}

	// Get the front element.
	public int peek() {
	    return first;
	}

	// Return whether the queue is empty.
	public boolean empty() {
	    return s.isEmpty();
	}
	
   public static void main(String[] args) {

	   QueueUsing1Stack q = new QueueUsing1Stack();
	   q.push(1);
	   q.push(2);
	   System.out.println("peek() " + q.peek()); //return 1	  
	   q.pop();
	   System.out.println( q.peek());  // return 2
	   System.out.println("Is queue empty ? " + q.empty());  // return false
	  
	   
   }
}