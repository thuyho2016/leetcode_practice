/*
 * 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6 = 2 * 3
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


The idea is to keep max and min product of current and previous values for next iteration
and check if current max value is the best result.

Why do we need min value ? Because min value multiplies another min value will yield a possibly max value. 
That's why we need to keep track of min value as well. 
Otherwise, it will be no different than maximum subarray problem which we keep track of max value only.


   [ 2, 3, -2, 4]
    [--]Product =2
    [----] Product = 2 * 3 = 6
    [---------] Product = 6 *-2 = -12
    [------------] = -12 * 4 = -48
    
 */

public class ContinuousSubArray_FindMaxProduct {
	 /**
	 * When iterating the array, each element has two possibilities: positive number or negative number. 
	 * We need to track a minimum value, so that when a negative number is given, it can also find the maximum value. 
	 * We define two local variables, one tracks the maximum and the other tracks the minimum.
	 
	 * Time Complexity - O(n)
	 * Space Complexity - O(n)
     */
	
	
	public static int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
	   
		if(nums.length == 1)
            return nums[0];
        
       int min = 0;
       int max = 0;
       int totalMax = 0;
       
       for(int num : nums){
           
           int prevMin = min*num;
           int prevMax = max*num;
           
           min = Math.min(num, Math.min(prevMin, prevMax));
           max = Math.max(num, Math.max(prevMin, prevMax));
           totalMax = Math.max(totalMax, max);
       }
 
       return totalMax;
       
	}
	
	public static void main(String[] args) {
		  
    	int[] input = {2,3,-2,4};
    	System.out.println("Max " + maxProduct(input)); //6
      
    }
}
