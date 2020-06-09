
/*
 * 55. Jump Game
 * https://leetcode.com/problems/jump-game/
 * 

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */


public class JumpGame 
{
	// Prefer this - Time complexity : O(n) and Space: O(1) - Runtime: 1 ms,
	public static boolean canJump(int[] nums) {
		int lastIndex = nums.length - 1;
        int max = 0;
        
        for(int i = 0; i <= max; i++) {
            max = Math.max(max, i + nums[i]); //maxJump = (2, 1 + 3) = (2,4) = 4, 
            
            if(max >= lastIndex)  //4 >= 4
            	return true;
        }
        return false;
	}
	
	/**
    Approach: Greedy
    
    nums = [ 2, 3, 1, 1, 4]
    index    0  1  2  3  4
    
    Time complexity : O(n). We are doing a single pass through the nums array, hence n steps, where n is the length of array nums.

	Space complexity : O(1). We are not using any extra memory.

 */

	public static boolean canJump2(int[] nums) {
		int lastIndex = nums.length - 1;  //4
	    
	    for(int i = nums.length - 1; i >= 0; i--) {
	        if(i + nums[i] >= lastIndex)  //4 >= 4
	        	lastIndex = i;
	    }
	    return lastIndex == 0;
	}

	/** Approach 2: Dynamic Programming Top-down
	nums = [2, 4, 2, 1, 0, 2, 0] 
	
	Index	0	1	2	3	4	5	6
   	nums	2	4	2	1	0	2	0
	memo	G	G	B	B	B	G	G
	
	We can see that we cannot start from indices 2, 3 or 4 and eventually reach last index (6), 
	but we can do that from indices 0, 1, 5 and (trivially) 
	
	Steps:

	- Initially, all elements of the memo table are UNKNOWN, except for the last one, which is GOOD (it can reach itself)
	-  checks if the index is known (GOOD / BAD)
	- If it is known then return True / False
	- Otherwise perform the backtracking steps as before
	- Once we determine the value of the current index, we store it in the memo table
	*/
	
	static enum Index {
	    GOOD, BAD, UNKNOWN
	}
	
	static Index[] memo;
	
	public static boolean canJump3(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        
        memo[memo.length - 1] = Index.GOOD; //[UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, GOOD]
        return canJumpFromPosition(0, nums);
    }
	
	 private static boolean canJumpFromPosition(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1); //2 , 4, 3 + 1 = 4
        
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) { //nextPos starts from 1
            if (canJumpFromPosition(nextPosition, nums)) {
                memo[position] = Index.GOOD; //[UNKNOWN, UNKNOWN, UNKNOWN, GOOD, GOOD]
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

	public static void main(String[] args)
	{
		int[] nums = {2,3,1,1,6};
		System.out.println(canJump(nums));
		
		System.out.println(canJump2(nums));
		System.out.println(canJump3(nums));
	}
}
