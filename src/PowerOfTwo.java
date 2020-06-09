
/* 231. Power of Two
 * https://leetcode.com/problems/power-of-two/solution/
 Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1
Example 2:

Input: 16
Output: true
Explanation: 24 = 16 
 

Steps:
 while  16 % 2 = 0, then  16/ 2 = 8
        8 % 2 = 0, then 8 /2 = 4
        4 % 2 = 0, then 4 / 2 = 2
        2 % 2 = 0, then 2 /2 = 1
        1 % 2 = 1, not in while loop
        
Input 1: 2^1 = 0
        
*/

public class PowerOfTwo{

	
	public static boolean isPowerOfTwo(int number) {
		
        if ( number < 1) { // number == 0
        	return false;
        } else {
        	while (number % 2 == 0) {
        		number = number / 2;  
        	}
        }
        
		return number == 1;
    }
	
	public boolean isPowerOfTwo2(int n) {
	    if (n == 0) return false;
	    long x = (long) n;
	    return (x & (x - 1)) == 0;
	  }
	
	public static void main(String[] args) {
		int n = 16;
		System.out.println(isPowerOfTwo(n)); //true
		int n2 = 9;
		System.out.println(isPowerOfTwo(n2)); //false
	}

}
	
	