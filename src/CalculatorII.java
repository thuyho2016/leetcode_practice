
import java.util.Stack;

/*
 * 227. Basic Calculator II
 * https://leetcode.com/problems/basic-calculator-ii/
 
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7

Example 2:

Input: " 3/2 "
Output: 1

Example 3:

Input: " 3+5 / 2 "
Output: 5


   switch(op) {
        case '+':
            stack.push(num); //stack = [3], when op= +, c = *, num = 2, stack = [3,2]
            break;
        case '-':
            stack.push(-num);
            break;
        case '*':
        	stack.push(stack.pop() * num); //pop 2 from stack * num = 2. So, stack will [3,4]
            break;
        case '/':
        	stack.push(stack.pop() / num);
			break;
        default:
            return -1;
    }

 */

public class CalculatorII {
	
	/** Prefer this solution
	Use a global op to remember the latest operator we have met and 
 a global variable num for current number and a stack to accommodate the numbers.
	
	Go through the string by characters, and the rule is:
	
	- If current character is digit, multiply num with 10 and add current value to num
	- If current character is opr + or - , push current number to Stack
	- If current character is opr * or / , pop number from Stack and * or / with current number, then push a result back to Stack.
	
	we need to initialize opr to '+' to handle the first number.
	 */
	public static int calculate_Stack(String s) {
        int num = 0;
        char op = '+';  //we need to initialize opr to '+' to handle the first number
        
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i = 0; i < s.length(); i++) {
        	
			char c = s.charAt(i);       	
            
            if(Character.isDigit(c)) { //if ci is digit, then convert to int
                num =  num * 10 + (c - '0');  // num * 10  to prevent error when / by rezo , c - '0' to convert char to int, So, 0 * 3 + 3 = 3            
                                     // Forming operand, since it could be more than one digit
            } 
            // c == '*' || c == '/' || c == '+' || c == '-'
            if (i == s.length() - 1 || c != ' '&& !Character.isDigit(c) ) { //  
            	if(op == '+')  
            		stack.push(num);  //stack = [3], when op= +, c = *, num = 2, stack = [3,2]
                else if(op == '-') 
                	stack.push(-num);
                else if(op == '*') 
                	stack.push(stack.pop() * num);  //pop 2 from stack * num = 2. So, stack will [3,4]
                else if(op == '/') 
                	stack.push(stack.pop() / num);
            
                num = 0; // reset num
                op = c; // +, * , 2
            }
        }
        
        int res = 0;
        while(!stack.isEmpty()) {
        	res += stack.pop(); //[3,4] , res = 3 + 4 = 7
        }
        
        return res;
    }
	
	/** Without Stack
	 * all the operating of '*' and '/' are performed immediately (hisger priority), so all the numbers in the stack are just to be sumed up. 
	 * we can always sum up all the number of the stack except the latest one, without impacting the result. 
	 * So we just need two vars instead of the stack:

       tmp for the sum of all the number except the latest one;
       num for the latest number we are handling.
       
       we need to initialize opr to '+' to handle the first number.
       If current character is digit, multiply num with 10 and add current value to num:
       			num = num * 10 + (chr - '0');
	*/
	
	public static int calculate(String s) {
        int res = 0; 
        
        int num = 0;
        int tmp = 0; //use global tm[ to calculate instead of Stack
        
        char opr = '+';  //we need to initialize opr to '+' to handle the first number:
        
        for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);        
			//if(c  == ' ')continue;
			
            if(Character.isDigit(c)) {  //If current character is digit, multiply num with 10 and add current value to num
            	
                num = num * 10 + (c - '0');  //c - '0' = 3 -'0' = 3
            } 
            
            if (i == s.length() - 1 || c != ' ' && !Character.isDigit(c)) {    // is operation * / - +    
                
            	if(opr == '+') {  
            		res += tmp;  //0
                	tmp = num; //3  
            	} else if(opr == '-') {
            		res += tmp;
            		tmp = -num;
            	} else if(opr == '*') {
            		tmp *= num;   
        		} else if(opr == '/') {
        			tmp /= num;
        		}
            	
                num = 0;
                opr = c;  //+
            }
        }
        res += tmp;
        
        return res;
    }
	
	public static void main(String[] args) {		

		String s = "3+2*2";
		System.out.println("Result " +  calculate(s)); //7
		System.out.println("Result " +  calculate_Stack(s));
		
		String s2 = "3/2 ";
		System.out.println("Result " +  calculate(s2));
		System.out.println("Result " +  calculate_Stack(s2)); //1
		
		//String s3 = "1*2-3/4+5*6-7*8+9/10";
		String s3 = " 3+5 / 2 ";
		System.out.println("Result " +  calculate_Stack(s3)); 
		System.out.println("Result " +  calculate(s3));
		
	}	
	
}
