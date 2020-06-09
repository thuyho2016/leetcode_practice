
import java.util.Stack;

/* 
 * 772. Basic Calculator III (Hard level)
 * https://leetcode.com/problems/basic-calculator-iii/

     
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

if operator now is '-', push -digit
 
Similar with CalculaterII with extra step - Recursive to calculate express inside ( )  
*/


public class CalculatorIII {
	//Use One Stack and Recursive to calculate express inside ( )

	
   public static int calculate(String s) {
	    Stack<Integer> stack = new Stack<Integer>();    
        int len = s.length();
        
        int res = 0;
        int num = 0;
        char opr = '+';  //we need to initialize opr to '+' to handle the first number:
        
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i); 
            
            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if(s.charAt(i) == '(') {
                int count = countValid(s.substring(i)); //index 6
                num = calculate(s.substring(i + 1, i + count)); // index i + 1 = 3, substring(3,6) = 5+5*2 
                i += count; //7
            }
            //i == len - 1 || c == '*' || c == '/' || c == '+' || c == '-'
            if(i >= len - 1 || (c != ' ' && !Character.isDigit(c))) {
                if(opr == '+')  
                	stack.push(num);
                else if(opr == '-') 
                	stack.push(-num);
                else if(opr == '*') 
                	stack.push(stack.pop() * num);
                else if(opr == '/') 
                	stack.push(stack.pop() / num);
                num = 0;
                opr = c;
            }
        } //for
        
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
	  
	private static int countValid(String s){
        int counter = 0;
        int i = 0;
        while (i < s.length()) {
            if(s.charAt(i) =='(') counter ++;
            else if(s.charAt(i) ==')') counter --;
            
            if(counter == 0) break;
            i++;
        } 
        return i; //6
    }
	    	
	public static void main(String[] args) {		
	//	String s = " 6-4 / 2 ";
	//	System.out.println(calculate(s)); //4
		
		String s = "2*(5+5*2)/3";
		System.out.println(calculate(s)); //10
		
		String s1 = "2*(5+5*2)/3+(6/2+8)";
		System.out.println(calculate(s1)); //21
		
		String s2 = "(2+6* 3+5- (3*14/7+2)*5)+3";
		System.out.println(calculate(s2)); //-12
		
	}
}
