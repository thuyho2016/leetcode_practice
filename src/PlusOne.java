/*
 * 66. Plus One (easy level)
 * https://leetcode.com/problems/plus-one/
 * 
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

 */
public class PlusOne{

	public static int[] plusOne(int[] digits) {
        
	    int n = digits.length;
	    
	    for(int i = n-1 ; i >= 0; i--) { //start from last element
	        if(digits[i] < 9) { //7 < 9
	            digits[i]++; //8
	            return digits;
	        }
	        
	        digits[i] = 0;  // set 0 because 9 + 1 = 10
	    }
	    
	    int[] newNumber = new int [n+1];  //new array with length = 4
	    newNumber[0] = 1; // put number 1 in front
	    
	    return newNumber;
	}
	
	public static void main(String[] args) {
    	int[] digits = {99, 9, 10, 9}; 
    	
    	int[] res = plusOne(digits);
	    for (int d: res) {
	    	 System.out.print(d + " "); //99, 9 , 10, 8
		}
	
	}

}
	
	