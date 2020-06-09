package design;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

public class MaxStack {
	
	List<Integer> max;
    List<Integer> stack;
    
    /** initialize your data structure here. */
    public MaxStack() {
       max = new ArrayList<>();
       stack = new ArrayList<>();
    }
    
    public void push(int x) {
        stack.add(x);
        if(max.size() == 0) {
        	max.add(x);
        } else {
        	max.add(Math.max(max.get(max.size() - 1), x));
        }
    }
    
    public int pop() {
    	max.remove(max.size() - 1);
    	return stack.remove(stack.size() - 1);
    }
    
    public int top() {
        return stack.get(stack.size() - 1);
    }
    
    public int peekMax() {
        return max.get(max.size() - 1);
    }
    
    public int popMax() {
    	
    	int toReturn = peekMax();
    	
    	int location = stack.size() - 1;
    	while (stack.get(location) != toReturn) {
    		location--;
    		max.remove(max.size() - 1);
    	}
    	
    	stack.remove(location);
    	max.remove(max.size() - 1);
    	
    	for ( int i = location; i < stack.size(); i++) {
    		if (max.size() == 0) {
    			max.add(stack.get(i));
    		} else {
    			max.add(Math.max(stack.get(i),  max.get(max.size() - 1)));
    		}
    	}
    	
    	return toReturn;
    	
    }
 
	
   public static void main(String args[])
   {      
	  MaxStack stack = new MaxStack();
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