package string;
import java.util.Stack;
 /* 32. Longest Valid Parentheses
https://leetcode.com/problems/longest-valid-parentheses/

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"


{[()]}  --> YES
{[(])}   ---> NO

() open & close parenthesis
{} open & close curly bracket
[] open & close square bracket


Solution: 2 pointers 
//Time complexity : O(n). Two traversals of the string.
Space complexity : O(1). Only two extra variables left and right are needed.
*/

public class ParenthesesOrBrackets_Longest {
	
	//find the length of the longest valid
	//Time complexity : O(n). n is the length of the given string..

	//Space complexity : O(n). The size of stack can go up to n
	
	// store the index into stack
	public static int longestValidParentheses(String s) {
	    Stack<Integer> stack = new Stack<>();
	    int max = 0;
	    int left = -1;
	    for(int i=0; i < s.length(); i++){
	        char c = s.charAt(i);
	        
	        if(c == '('){
	        	stack.push(i);  //[0, 1]
	        } else {
	        	stack.pop();   //pop 1 so stack=[0]
	        	if (stack.isEmpty()) {
	        		max = Math.max(max,  i - left); //for case (()) max = (2,3+1) = 4 
	        	}
	        	else {
	        		max = Math.max(max,  i - stack.peek()); //max(0,2-0) = 2
	        	}
	        }
	    }
	 
	    return max;
	}
	

	public static void main (String[] args)
    {		
	    
		String s4 = "(())";
		System.out.println("longest valid parentheses substring " + longestValidParentheses(s4));	//2	
		
    }
	
}