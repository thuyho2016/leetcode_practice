
/*
 * 53. Maximum Subarray ( Easy Level)
https://leetcode.com/problems/maximum-subarray/

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6

Explanation: [4,-1,2,1] has the largest sum = 6.


 */
public class ContinuousSubArray_FindLargestSum {
 
	/*  Dynamic Programming  -  This solution - Similar Best Buy Stock I
	    Optimizing Space Complexity
	  
	  Time complexity : O(N) since it's one pass along the array.
	  Space complexity : O(1)
	 
	 * dp[i] only depends on dp[i-1]. So instead of storing all the results in the dp array, we can just save the previous value. 
	 * 
	 * Save result in the previous value.
	 * Example
	 * -2 1 2 
	 * -2 1 --> if -2 + 1 = -1 negative number < 0 , so I need to start from 1 , and 1 + 2 = 3
	 * 
	 * -2 3 -1 4
	 * If Add -2 + 3 = 1, so it is better to start 3 , 3 + (-1) = 2, then 2 + 4 = 6
	 * 
	 )
	 */

	//prefer this solution
	public static int maxSubArray(int[] nums) {
		if (nums.length == 0) return 0;
		
		int curSum = nums[0]; // previous or j pointer
		int maxSum = nums[0];   
		
		// start from 1
		for(int i = 1; i < nums.length; i++) {  //{-2,1,-3,4,-1,2,1,-5,4};
			
			if (curSum > 0) {
				curSum = nums[i] + curSum;
			} else {
				curSum = nums[i]; //if curSum < 0, shift curSum to cur number    
			}
			
			maxSum = Math.max(maxSum, curSum);
		}
		return maxSum;
	}
	
	// Dynamic Solution -- change element in array
	public static int maxSubArray2(int[] nums) {
        int maxSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {					 //i = 2, nums[2] = -2, nums = [-2, 1, -2, 4, -1, 2, 1, -5, 4]
            nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]); //i = 5, nums[5] = 5 , nums = [-2, 1, -2, 4, 3, 5, 1, -5, 4]
            													//i = 6, nums[6] = 6, nums = [-2, 1, -2, 4, 3, 5, 6, -5, 4]
            maxSum = Math.max(nums[i], maxSum);					//i = 7, nums[7] = 1, nums = [-2, 1, -2, 4, 3, 5, 6, 1, 4]
        }
        
        return maxSum;
    }
	
	//same with solution 1
	public static int maxSubArray3(int[] nums) {
	     
        int maxSum = nums[0];
        int currSum = 0;
        
        for (int num : nums) {
            
            if (currSum + num > num) {
                currSum += num;
            }
            else {
                currSum = num;
            }
            
            maxSum = Math.max(maxSum, currSum);
           
        }
        return maxSum;
    }
	
    
	
    public static void main(String[] args) {
  
    	int[] input = {-2,1,-3,4,-1,2,1,-5,4};
    	System.out.println("Max Sum " +  maxSubArray(input)); //6
    	System.out.println("Max Sum " +  maxSubArray2(input)); //6
		System.out.println("Max Sum " +  maxSubArray3(input)); //6
		
    }
}