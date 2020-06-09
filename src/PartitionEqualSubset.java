import java.util.Arrays;
import java.util.HashMap;

/*
 * 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100. The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

Similar with SumCombination2.java

Solutions:
Add numbers together. Check if sum % 2 is 0 or not. Sum of all number % k must be 0 If 0, return false
s
if two subsets has equal amount, then good.


*/


public class PartitionEqualSubset {
	
	public static boolean canPartitionKSubsets(int[] nums) {
        int total = 0;
        for ( int i: nums) {
        	total += i;
        }
        System.out.println("Total division: " + total % 2);
        if (total % 2 != 0) return false;  // 22 % 2 = 0, return true
        
        return canPartition(nums, 0, 0, total, new HashMap<String, Boolean>());
        
        
    }
	
	public static boolean canPartition( int[] nums, int index, int sum, int total, HashMap<String, Boolean> state) {
		String current = index + "" + sum;
		
		if (state.containsKey(current)) {
			return state.get(current);
		}
		if (sum * 2 == total) {  // two subsets
			return true;
		}
		
		if (sum > total / 2 || index >= nums.length) {
			return false;
		}
				
		 boolean foundPartition = canPartition( nums, index + 1, sum, total, state) 
				 || canPartition( nums, index + 1, sum + nums[index], total, state);   //nums[index] is current number
		
		 state.put(current, foundPartition);
		 
		 return foundPartition;
	}
	
	
	
	public static void main(String[] args) {
		int[] x = {1, 5, 11, 5};
		System.out.println(canPartitionKSubsets( x));
		
	}
}
