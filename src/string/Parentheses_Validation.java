package string;
import java.util.Stack;

/*
 * 20. Valid Parentheses ( easy level)
 * https://leetcode.com/problems/valid-parentheses/
 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

The brackets must close in the correct order, "()" and "()[]{}" are all valid, but "(]" and "([)]" are not.

Time & Space: O(n)
*/
public class Parentheses_Validation{
	
	public static boolean isValid(String str){
        int count = 0;
        
        for(int i = 0; i < str.length(); i++){
            char tmp = str.charAt(i);
            
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
	
	
	// Use stack
	/*
	 * scan the expression {[()]} left to right
	 * if opening symbol, add it to a list - push(exp[i])
	 * if closing symbol, 
	 *    check if (stack is empty || element top does not pair with  exp[i]) , return false
	 *    Else  - pop() to remove last opening symbol in the list - pop()
	 *    
	 * Open terms [ { ( put it in stack and expect next characters are close terms ) } ] should have a match.
	 * 
	 */
	public static boolean isValid2(String s) {
		
		Stack<Character> stack = new Stack<Character>();
	 
		for (char c : s.toCharArray()) { //convert string to new character array
			if (c == '{' || c == '[' || c == '(') { //open symbol
				stack.push(c);
			}
			else if (c == ')' &&  stack.peek() =='(' && !stack.empty()) {
				 stack.pop(); // pop ( 
			} 
			else if (c == ']' &&  stack.peek() =='[' && !stack.empty()) {
				 stack.pop(); // pop [
			}
			else if (c == '}' &&  stack.peek() =='{' && !stack.empty()) {
				 stack.pop(); // pop {
			}
			else  {
				return false;
			}
		}
		return stack.isEmpty(); // valid if true 
	}
	
	
	
	public static void main(String[] args) {
	   String s = "()[]{}";
	   System.out.println(isValid2(s));  //true
	   
	   String s1 = "(]";
	   System.out.println(isValid2(s1));  //false
	   
	   String s2 = "{[()]}";
		System.out.println(isValid(s2)); //true
		
	   
	}

}
	
	