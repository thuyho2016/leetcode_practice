/*
 * 312. Burst Balloons
 * https://leetcode.com/problems/burst-balloons/
 * 
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 
 * Steps:
1st order to burst: [ 1, 3, 5, 8] 
 If 1 is first balloon burst, Number of coins = 3 * 1 * 5 = 15 
  1 already burst, it is gone, next burst is 3: 1 * 3 * 5 = 15 
  3 is gone, next burst is 5: 1 * 5 * 8 = 40
  5 is gone, next burst is 8: 1 * 8 * 1 = 8
  Total = 15 + 15 + 40 + 8 = 78
  
  
  2nd order to burst:[ 5 1 3 8]
  1st burst is 5: 1 * 5 * 8 = 40
  5 is gone, next burst is 1: 3 * 1 * 8 = 24
  1 is gone, next burst is 3:  1 * 3 * 8 = 24
  next burst is 8, 3 is gone: 1 * 8 * 1 = 8
  total = 40 + 24 + 24 + 8 = 96
   
  
  
  Time complexity : O(N^3) and Space complexity : O(N^2)

nums[left] * nums[i] * nums[right] is the number of coins obtained from adding the ith balloon, 
and dp(left, i) + dp(i, right) are the maximum number of coins obtained from solving the left and right sides of that balloon
 */

public class BrustBalloons {
	
	//Dynamic Programming (Top-Down)
	public static int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
	
        int n = nums.length + 2;
        int[] new_nums = new int[n];

        new_nums[0] = 1;
        new_nums[n - 1] = 1;
        
        for(int i = 0; i < nums.length; i++){
            new_nums[i+1] = nums[i];
        }

        int[][] memo = new int[n][n]; // cache the results of dp

        // find the maximum number of coins obtained from adding all balloons from (0, len(nums) - 1)
        return dp(memo, new_nums, 0, n - 1);
    }

    public static int dp(int[][] memo, int[] nums, int left, int right) {
        // no more balloons can be added
        if (left + 1 == right) return 0;

        // we've already seen this, return from cache
        if (memo[left][right] > 0) return memo[left][right];

        // add each balloon on the interval and return the maximum score
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right] + dp(memo, nums, left, i) + dp(memo, nums, i, right));
        
        // add to the cache
        memo[left][right] = ans;
        return ans;
    }
    
    //way2
    public static int maxCoins2(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
            	
                int right = left + k;
                
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }

        return dp[0][n - 1];
    }
    
    public static void main (String[] args)
    {
		int[] nums = new int[] {3,1,5,8};
		
		System.out.println(maxCoins(nums));
		System.out.println(maxCoins2(nums));
    }
}
