/* 
 * 7. Reverse Integer ( Level = easy)
 * 
 * Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321

Example 3:
Input: 120
Output: 21

 * Hint
 *  123 % 10 = 3 
 *  123 / 10 = 12 
 *  
 *  output =  0 * 10 + 3 = 3
 *   3 * 10 + 2 = 32
 *   32 * 10 + 1 = 320 + 1 = 321

 */

public class ReverseNumber
{
	public static int reverse(int num) {
		int output = 0;
		 
		 while (num != 0) { // input is negative number -123
			 int remain = num % 10;  // 123 % 10 = 3, 12 % 10 = 2, 1 % 10 = 1
			 num = num / 10; // 123 / 10 = 12 , 12 / 10 = 1, 1 / 10 = 0.1
			 
			 output = output * 10 + remain;  // 3, 3 * 10 + 2 = 32, 32 * 10 + 1 = 321
			 
			 if (output > Integer.MAX_VALUE || output < Integer.MIN_VALUE) {//-2147483648.00
    			return 0;
			 }
		 }		 

		 System.out.println("Reversed: " + output);
		 return output;
	}
	
	public static void main(String args[])
	{
		int num = 123;
		reverse(num);
		
		int num2 = -123;
		reverse(num2);
		
		int num3 = 120;
		reverse(num3);
	}
	
	
}