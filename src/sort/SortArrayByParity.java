package sort;

/*
 * 905. Sort Array By Parity
 * https://leetcode.com/problems/sort-array-by-parity/

Given an array A of non-negative integers, return an array consisting of all the even elements of A followed by all the odd elements 

Input: [3,1,2,4]
Output: [2,4,3,1]    // even numbers follow by odd numbers

The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Steps:
- I made a evenPointer starting at 0 and an oddPointer starting at the of the array. 
- Creating a res Array for storing result.
- Using two pointers pointing to the Two extreme end of res Array
- Filling Even numbers from the Left side and Odd numbers from the Right side.
 */

public class SortArrayByParity{

	public static int[] sortArrayByParity(int[] digits) {
        
	    int len  = digits.length;
	    int leftPointer = 0;
	    int rightPointer = len - 1;
	    
	    int[] res =  new int[len];
	    
	    for(int i = 0; i < len ; i++) {
	    	
	        if(digits[i] % 2 == 0 ) { // even number
	        	//res[leftPointer--] = digits[i];
	        	res[leftPointer] = digits[i];
	        	leftPointer++;
	        	
	        } else {
	        	res[rightPointer] = digits[i];
	        	rightPointer--;
	        }
	    }
	    return res;
	}
	
	
	public static void main(String[] args) {
	     int[] a = {3,1,2,4};  
	     int[] result = sortArrayByParity(a);
	     for (int d: result) {
	    	 System.out.print(d + " "); //2 4 1 3 
		}
	
	}

}
	
	