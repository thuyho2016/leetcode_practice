
import java.util.Stack;

/* 
 * 224. Basic Calculator (Hard level)
 * https://leetcode.com/problems/basic-calculator/

     
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Solutions:

 
 */


public class CalculatorI {
	/** Prefer this
Algorithm:

'(' before '+'/'-') = (This context sign);
'(' after '+'/'-') = (This context sign) * (1 or -1);
Algorithm:

Start from +1 sign and scan s from left to right;

if c == digit: This number = Last digit * 10 + This digit;

if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;

if c == '(': Push this context sign to stack;
if c == ')': Pop this context and we come back to last context;

Add the last num. This is because we only add number after '+' / '-'.
 */
	public static int calculate(String s) {
	    if(s == null) return 0;
	        
	    int result = 0;
	    int sign = 1;
	    int num = 0;
	            
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(sign);  //[1]
	            
	    for(int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	                
	        if(c >= '0' && c <= '9') {
	            num = num * 10 + (c - '0');
	                    
	        } else if(c == '+' || c == '-') {
	            result += sign * num;  // 0 + 1 * 1 = 1
	            sign = stack.peek() * (c == '+' ? 1: -1);   // case2: sign = -1
	            num = 0;
	                    
	        } else if(c == '(') {
	            stack.push(sign);  //case 2: stack = [1, 1]
	                    
	        } else if(c == ')') {
	            stack.pop(); //after pop, stack = [1]
	        }
	    }
	            
	    result += sign * num; // = 1 + 1 * 1 = 2
	    return result;
	}
	
	
	public static int calculate2(String s) {
        // all possible case: "+", "-", "(", ")", " ", "1-9"
        // +: change sign to positive +1
        // -: change sign to negative -1
        // (: push current result value and sign onto the stack
        // ): pop the previous result value and sign off the stack and do the addition 
        // " ": skip
        // 1-9: read all digits after current digit 
		
		   Stack<Integer> stack = new Stack<Integer>();
        // initialize result to be 0, sign to be 1
        int result = 0, sign = 1;
        
        int n = s.length();
        
        // iterate through all characters of the input
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            switch (curr) {
                case '+':
                    // make sign become positive to indicate we are adding a value
                    sign = 1;
                    break;
                case '-':
                    // make sign become negative to indicate we are subtracting a value 
                    sign = -1;
                    break;
                case '(':
                    // push current result, then push the sign onto the stack
                    stack.push(result);
                    stack.push(sign);
                    // reset result and sign for the value in the parenthesis
                    result = 0;
                    sign = 1;
                    break;
                case ')': 
                    int prevSign = stack.pop(); //pop the sign before the parenthesis
                    int prevRes = stack.pop(); //pop the result calculated before the parenthesis
                    result = prevRes + prevSign * result;
                case ' ':
                    // skip the empty spaces
                    break;
                default:
                    // in case current char is a digit, read the whole integer 
                //	 if(Character.isDigit(c)){
                   //      number = 10 * number + (int)(c - '0');
                	
                    int startIdx = i;
                    while (i < n && Character.isDigit(s.charAt(i))) {
                        i++;
                    }
                    
                    int val = Integer.parseInt(s.substring(startIdx, i));
                    result += sign * val;
                    i--;
            }
        }
        return result;
    }
	
	/**
	 I reformed the input expression by rules of:

	1. remove all '(', ')', ' ';
	2. reverse the express string;
	3. add '+' or '-' to the end of the express.

	By this approach, the reformed expression will be easy to handled.

	"1 + 1"                             =>   "1+1+"
	" 2-1 + 2 "                        =>   "2+1-2+"
	"(1+(4+5+2)-3)+(6+8)"    =>   "8+6+3-2+5+4+1+"
	"2-(5-6)"                          =>   "6+5-2+"

	 */
	
	public static int calculate3(String s) {
		if(s == null)
	        return 0;
	    s = reform(s);
	    int result = 0, num = 0, base = 1;
	    
	    for(char c: s.toCharArray())
	        switch(c){
	        case '+': 
	        	result += num; 
	        	num = 0; 
	        	base = 1; 
	        	break;
	        case '-': result -= num; num = 0; base = 1; break;
	        default: num += (c - '0') * base; base *= 10;
	        }
	    return result;
	    
    }
	
	private static String reform(String s) {
	    StringBuilder sb = new StringBuilder();
	    Stack<Boolean> stack = new Stack<Boolean>();
	    
	    stack.push(true);
	    boolean add = true;
	    
	    for(char c: s.toCharArray()) {
	    	 switch(c){
	         case ' ': break;
	         case '(': stack.push(add); break;
	         case ')': stack.pop(); break;
	         
	         case '+': 
	             add = stack.peek(); 
	             sb.append(stack.peek() ? '+' : '-'); 
	             break;
	         case '-': 
	             add = !stack.peek(); 
	             sb.append(stack.peek() ? '-' : '+'); 
	             break;
	         default: sb.append(c);
	         }
	    }
	    
	    if(sb.charAt(0) != '+' || sb.charAt(0) != '-')
	        sb.insert(0, '+');
	    return sb.reverse().toString();
	}
	    	
	    	
	    	
	public static void main(String[] args) {		
	/*	String s = "1 + 1";
		System.out.println(calculate(s));
		
		String s1 = "2-1 + 2";
		System.out.println(calculate(s1));
	*/	
		String s2 = "(1-3)+(6+8)";
		System.out.println(calculate(s2));
	}
}
