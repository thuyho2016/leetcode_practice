import java.util.Arrays;

/* 
 * 238. Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 * 
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

 input:  arr = [8, 10, 2]
 output: [20, 16, 80] # by calculating: [10*2, 8*2, 8*10]
 
 
Solution:
assume we have two integer arrays with the same length of nums, 
int[] leftProd = new int[nums.length]; 
int[] rightProd = new int[nums.length], 

we store the product of all the left elements in leftProd and the product of all the right elements in rightProd, 
then the product of leftProd[i] and rightProd[i] will be the value we want to put into the result.
 
 The idea is simple: one pass to calculate an array where array[i] contains the product of all elements appearing after i. 
 Then take a second pass to calculate the product of all elements appearing before i
 
Time complexity : O(N) where N represents the number of elements in the input array. We use one iteration to construct the array L, one to update the array answer.
Space complexity : O(1) since don't use any additional array for our computations.
  
 */

public class ArrayProductExceptSelf {
	 /** without division and in O(n) - Similar prefix sums" approach.
	
	O(N) time complexity in two passes, O(1) extra space
	
	the basic idea in both remains the same - iterate over the array twice from left to right and right to left and compute the product of all integers.

	Store them in arrays lefts, rights respectively.

	Afterwards put as result[i] the product of lefts[i - 1] * rights[i + 1]

	*/
	public static int[] arrayOfArrayProducts(int[] nums) {
		if(nums == null || nums.length == 0) {
            return new int[0];
        }
		 
		int[] result = new int[nums.length];
		
		//left side - // One pass to create the products of all elements after i
		// for the element at index '0', there are no elements to the left,
        // so the result[0] would be 1
		result[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			result[i] = result[i-1] * nums[i-1];
			 // result[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiply it with nums[i - 1] would give the product of all elements to the left of index 'i'
		}  //[1, 8, 80]
		
		//right side - // Second pass to multiply by the product of all elements before i
		 
		// Right contains the product of all the elements to the right
		 // for the element at index 'length - 1', there are no elements to the right,
        // so the Right would be 1
		
		int right = 1;
		for(int i= nums.length - 1; i >= 0 ;i--){  //[1, 16, 80] ,[20, 16, 80] 
            result[i] = result[i] * right;  //result[1] = 8 * 2 =16
            right *= nums[i];  // 10 * 2, 
        }
		
		return result;
	}
	
	//use one pass
	public static int[] arrayOfArrayProducts1(int[] nums) {
		if(nums == null)    
	        return null;
		
		int[] result = new int[nums.length];
		int left = 1, right = 1;
		int len = nums.length;
		
		for (int i = 0; i < len; i++) {
			result[i] = result[i] * left;
			result[len - 1 - i] *= right;
			
			left *= nums[i];
			right *= nums[len - 1 -i];
		}
		
		return result;
	}
	
	// use Division - whole product divided by the the number in current index
	public static int[] arrayOfArrayProducts2(int[] arr) {
		
		int [] result = new int[arr.length];
		if ( arr.length < 1)
			return result;
		
		
		int multiply = 1;
		for (int i = 0; i < arr.length; i ++) {
			multiply = multiply * arr[i];  // 1 * 8 , 8 * 10 = 80, 8 * 10 * 2 = 160
		}
		
		// division
		for (int i = arr.length - 1; i >= 0; i--) {
			result[i] = multiply / arr[i]; // ( 8 * 10 * 2) / 2 = 160 / 2 = 80 , 160/10 = 16, 160/8 = 20
		}
		
		return result;
		
	}
	
	public static void main (String[] agrs) {
		int[] arr = {8, 10, 2}; 
		System.out.println(Arrays.toString(arrayOfArrayProducts(arr))); //[20, 16, 80]
		
		int[] arr1 = {8, 10, 2}; 
		System.out.println(Arrays.toString(arrayOfArrayProducts1(arr1))); //[20, 16, 80]
	}
}
