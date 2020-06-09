import java.util.Arrays;

/*
 * 1099. Two Sum Less Than K  (easy level)
 * https://leetcode.com/problems/two-sum-less-than-k/
 * 
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1. 

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58

Explanation: 
We can use 34 and 24 to sum 58 which is less than 60.

Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: 
In this case it's not possible to get a pair sum less that 15.
 * 
 */

public class TwoSumLessK {

	// this is similar with https://leetcode.com/problems/two-sum-less-than-k/discuss/433561/Java-Sort-and-two-pointers
	public static int twoSumLessThanK (int[] nums, int k ) {
		 if ( nums == null || nums.length == 0) return 0;
		 
		 Arrays.sort(nums);
		 
		 int maxLen = Integer.MIN_VALUE; 
		 // two pointers
		 int i = 0, j = nums.length - 1;
		 
		 while ( i < j ) {
			 if (nums[i] + nums[j] < k) {
				 maxLen = Math.max(maxLen, nums[i] + nums[j]);
				 i++;
			 } else {
				 j--;
			 }
			 
		 }
		 return maxLen == Integer.MIN_VALUE ? - 1: maxLen;		 
	}
	
	public static void main(String[] args) {
		int[] nums = {34,23,1,24,75,33,54,8};
		
		System.out.println(twoSumLessThanK (nums, 60)); //58
	}

}
