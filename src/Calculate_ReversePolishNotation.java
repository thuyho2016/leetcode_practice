import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * 150. Evaluate Reverse Polish Notation
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

 */

public class Calculate_ReversePolishNotation {
	
	//if element is operation, pop twice 
	// Calculate based on operation, then push result back to Stack
	// Else element is number, push it into Stack
	 
	public static double rpn (List<String> ops) throws IllegalArgumentException, ArithmeticException
	{
	    //Implementation here
		if(ops==null || ops.size() == 0) return 0;
	    
		Stack<String> stack = new Stack<String>();
	    
		for(String s: ops) {
	        if(checkOperator(s)) {
	            double j = Double.parseDouble(stack.pop());
	            double i = Double.parseDouble(stack.pop());
	            stack.push(String.valueOf(calculate(i, j, s)));
	        }else {
	            stack.push(s);
	        }
	    }
	
		return Double.parseDouble(stack.peek());
	}	
	
	//check string is +, -, *, /
	private static boolean checkOperator(String st){
	    return st.equals("+") || st.equals("-") || st.equals("*")|| st.equals("/");
	}

	private static double calculate( double a, double b, String op) {
	    switch(op) {
	        case "+": return a + b;
	        case "-": return a - b;
	        case "*": return a * b;
	        default: return a / b;
	    }
	}
	
	
	// return int 
  private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*","/"));
	
	public static int evalRPN(String[] ops) throws IllegalArgumentException, ArithmeticException
	{
	    //Implementation here
		if(ops==null || ops.length == 0) return 0;
	    
		Stack<String> stack = new Stack<String>();
	    
		for(String s: ops) {
			if(OPERATORS.contains(s)) {
	      //  if(checkOperator(s)) {
	            int b = Integer.parseInt(stack.pop());
	            int a = Integer.parseInt(stack.pop());
	            stack.push(String.valueOf(calculate2(a, b, s)));
	        }else {   // if number, push to Stack
	            stack.push(s);
	        }
	    }
	
		return Integer.parseInt(stack.pop());
	}	
	

	private static int calculate2( int a, int b, String op) {
	    switch(op) {
	        case "+": return a + b;
	        case "-": return a - b;
	        case "*": return a * b;
	        default: return a / b;
	    }
	}
	

	public static void main(String[] args) {
		
/*		List<String> ops = Arrays.asList("4", "1", "+", "2", "*");
		System.out.println("Result " +  rpn(ops)); 
		
		List<String> ops2 = Arrays.asList("4", "1.00", "2", "+", "*");
		System.out.println("Result " +  rpn(ops2));
		
		List<String> ops3 = Arrays.asList("4.00", "1", "2.00", "+", "*");
		System.out.println("Result " +  rpn(ops3));
		
		List<String> ops4 = Arrays.asList("4.00", "1", "2.00", "+", "/");
		System.out.println("Result " +  String.format("%.2f", rpn(ops4)));
		
		List<String> ops5 = Arrays.asList("0", "0", "+");
		System.out.println("Result " +  rpn(ops5));
	*/	
		String[] ops6 = {"4", "13", "5", "/", "+"};
		System.out.println("Result " +  evalRPN(ops6)); //6
		
	}	
	
}
