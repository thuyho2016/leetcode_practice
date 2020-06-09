import java.util.Arrays;

/* 
 * 561. Array Partition I ( Level = easy)
https://leetcode.com/problems/array-partition-i/

Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) 
which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].

Time complexity : O(nlog(n)). Sorting takes O(nlog(n)) time. We iterate over the array only once.

Space complexity : O(1). Constant extra space is used

 */

public class ArrayPairSum
{
	 public static int arrayPairSum(int[] nums) {
         Arrays.sort (nums);
         int s=0;
 
		 for(int i=0; i< nums.length-1; i += 2)
		     s += Math.min (nums[i], nums[i+1]); // add sum of the minimum of 2 numbers
		     
		 return s;  
	 }
	
	public static void main(String args[])
	{
		int[] input = {1,4,3,2};
		System.out.println("pairs of sum: " + arrayPairSum(input));
	}
	
	
}