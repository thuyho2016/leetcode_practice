
package matrix;

/*
 * 64. Minimum Path Sum (Amazon interview)
https://leetcode.com/problems/minimum-path-sum/
 
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.


Top left dp [0][0] = 1
Bottom right dp[2][2] = 1
Two ways to go : from up cell and left cell
 
 Math.min(left cell, up cell)
 add with upcell
 add with left cell
 Take minimum from previous

Similar, UniquePaths_HowManyWays.java
 */

public class MinimumPathSum {
	
	//DP - find minimum path 
	public static int minPathSum(int[][] grid) {
		if (grid == null || grid.length ==0) return 0;
		
		//dp array
		int[][] dp = new int[grid.length][grid[0].length];
		
		for (int i =0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				
				dp[i][j] += grid[i][j];
				
				if ( i > 0 && j > 0) { // get min value of up cell or left cell
					dp[i][j] += Math.min(dp[i-1][j],  dp[i][j - 1] ); 
				} 
				else if (i > 0) { // get value from up cell 
					dp[i][j] += dp[i-1][j];
				}
				else if ( j > 0) { // get value of left cell
					dp[i][j] += dp[i][j-1];
				}
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];   // represent bottom right
	    
    }
	
	
	public static void main (String[] agrs) {
		int[][] grid = {
				{1, 3, 1},
		 		{1, 5, 1},
		 		{4, 2, 1}};
		
		System.out.println(minPathSum(grid));//sum=7
		
	}
}
