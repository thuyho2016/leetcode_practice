
/*
 * 268. Missing Number
 * https://leetcode.com/problems/missing-number/

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
             
 0 + 1 + 2 + 3 =  n(n + 1) / 2 = --> expected sum
    
E.g: n = 3, sum of 3 numbers from 1 to 3 =  3(3 + 1) / 2 = 6
 
  Time complexity : O(n)
  Space complexity : O(1). This algorithm allocates only constant additional space.
 */



public class FindMissingNumber 
{
	public static int missingNumber(int[] nums) {
		  int sum = 0;
		    for (int i = 0; i < nums.length; ++i) {
		        sum += nums[i];
		    }
		    return nums.length * (nums.length + 1) / 2 - sum;  // 6 - 4 = 2
	}
	
	public static void main(String[] args)
	{
		int[] nums = {3,0,1};
		System.out.println(missingNumber(nums));
	}
}
