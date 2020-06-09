package design;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/* 155. Min Stack
https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
  
 */

public class MinStackByArrayLIst {
	/** initialize your data structure here. */
    int min;
    List<Integer> myList;
    
    public MinStackByArrayLIst() {
        myList = new ArrayList<Integer>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        myList.add(x);
        if(x < min){
            min = x;
        }
    }
    
    public void pop() {
        if(myList.get(myList.size()-1) == min){
            //find new min
            int newMin = Integer.MAX_VALUE; 
            
            for(int i=0;i < myList.size() - 1; i++){
                if(newMin > myList.get(i)){
                    newMin = myList.get(i);
                }
            }
            min = newMin;
        }
        
        myList.remove((int)(myList.size()-1));
        
    }
    
    public int top() {
        return myList.get(myList.size()-1);
    }
    
    public int getMin() {
        return min;
    }
 
	
   public static void main(String args[])
   {
	  MinStackByArrayLIst minStack = new MinStackByArrayLIst();
	  minStack.push(-2);
	  minStack.push(0);
	  minStack.push(3);
	  System.out.println( "Top is " + minStack.top()); //3
	  minStack.getMin();  
	  System.out.println( "GetMin is " + minStack.getMin()); // --> Returns -2.
	  minStack.pop();
	  minStack.top();     
	  System.out.println( "Top is " + minStack.top()); // --> Returns 0.
	  minStack.getMin(); 
	  System.out.println( "GetMin is " + minStack.getMin()); //  --> Returns -2.
      
   }
}