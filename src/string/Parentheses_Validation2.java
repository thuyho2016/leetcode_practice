package string;
import java.util.Stack;

/*
 * 678. Valid Parenthesis String
https://leetcode.com/problems/valid-parenthesis-string/
 
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.

'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Time & Space: O(n)
*/
public class Parentheses_Validation2{
	
	 public static boolean checkValidString(String s) {
		 int count = 0;
	        
        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            
            if(tmp == '('){
                count++;
            } else if(tmp == ')'){
                count--;
            }
            
            if(count < 0)
                return false;
        }
        return count == 0; //Indicates left parenthesis and right parenthesis are balanced.
    } 
	
	public static void main(String[] args) {
	   String s = "()";
	   System.out.println(checkValidString(s));  //true
	   
	   String s1 = "(*)";
	   System.out.println(checkValidString(s1));  //true
	   
	   String s2 = "(*))";
		System.out.println(checkValidString(s2)); //true
		
	   
	}

}
	
	