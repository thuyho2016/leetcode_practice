
/*
 * 45. Jump Game II ( hard level)
 * https://leetcode.com/problems/jump-game/


Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
    
 */


public class JumpGameII_NumberOfJumps 
{
	/** Approach: Greedy
	    From the last Index to the start index, 
	    Find the leftmost position that can reach the current position.
	    2, 3, 1, 1, 4  <--cur position points to 4
	      LM point to 3
	        
	    2, 3, 1, 1, 4
	    LM point to 2 , cur position points to 3
	    
	      
	    Time complexity : O(n). We are doing a single pass through the nums array, hence n steps, where n is the length of array nums.

		Space complexity : O(1). We are not using any extra memory.

	 */
	public static int jump(int[] nums) { 
		int lastIndex = nums.length - 1;  //4
        int count = 0;
        
        while (lastIndex > 0) { // != 0, 4 > 0, 1 > 0
        	
	        for(int i = 0; i < lastIndex; i++) {
	            if(i + nums[i] >= lastIndex)  { //i + nums[i] = 1 + 2 = 3 > 4, 1 + 3 >=4; 0 + 2 = 2 > 1
	            	lastIndex = i;  //lastIndex = i = 1, so count jumps; lastIndex = 0
	            	 count++;  
	            	 break;
	            }
	        }
        }
        return count;
	}
	
	
	public static void main(String[] args)
	{
		int[] nums = {2,3,1,1,6};
		System.out.println("number of jumps: " + jump(nums));
	}
}
