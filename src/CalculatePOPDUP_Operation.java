
import java.util.Stack;

/*
 * Given a string "13 DUP 4 POP 5 DUP + DUP + -" , the machine performs the following operations:
 * 
 * operation | comment      | stack
 * ----------------------------
 * 13        | push 13      | 13
 * "DUP"     | duplicate 13 | 13 13
 * "4"       | push 4       | 13, 13, 4
 * "POP"     | pop 4        | 13, 13
 * "5"       | push 5       | 13, 13, 5
 * "DUP"     | duplicate 5  | 13, 13, 5, 5
 * "+"       | add 5 and 5  | 13, 13, 10
 * "DUP"     | dup 10       | 13, 13, 10, 10
 * "+"       | add 10 and 10| 13, 13, 20
 * "-"		 | 20 - 13      | 13, 7
 * 
 * The machine will return 7 . Means return the topmost element from stack
 * 
 * Given, "5 6 + -" , the machine reports an error because after the addition, there is only one number on the stack. 
 * Return -1
 * 
 * Given "3 DUP 5 - -", the machine will reports an error because the second subtraction yields a negative result. 
 * Return 2 - 3 = -1
 * 
 * 
 */
public class CalculatePOPDUP_Operation {
	public static int calculate(String S) {
		
		Stack<String> stack = new Stack<String>();
		
		String[] command = S.split(" ");
		
		for (String s: command) {
			if(s.equals("+") || s.equals("-")) {
				
				if (stack.size()<= 1) {
					return -1; //not enough numbers in a stack
				}
				
				int i = Integer.parseInt(stack.pop());
				int j = Integer.parseInt(stack.pop());
				int cal = calculate(i,j,s);
				stack.push(String.valueOf(cal));
				
			} else if (s.equalsIgnoreCase("DUP")) {
				String topElement = stack.peek();
				stack.push(topElement);
			
			} else if (s.equalsIgnoreCase("POP")) {
				stack.pop();
			}		
			else {
				stack.push(s);
			}
		}
		
		return Integer.parseInt(stack.peek());
		
	}
	
	private static int calculate( int a, int b, String op) {
	    switch(op) {
	        case "+": return a + b;
	        case "-": return a - b;  //20 - 13 = 7
	        default: return 0;
	    }
	}
	
	public static void main(String[] args) {		

		String s = "13 DUP 4 POP 5 DUP + DUP + -";
		System.out.println("Result " +  calculate(s)); // 7
		String s2 = "5 6 + -";
		System.out.println("Result " +  calculate(s2));
		
		String s3 = "3 DUP 5 - -";
		System.out.println("Result " +  calculate(s3));
		
		
	}
}
