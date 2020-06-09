
package matrix;


/*
 * 741. Cherry Pickup ( hard level)
https://leetcode.com/problems/cherry-pickup/
 
In a N x N grid representing a field of cherries, each cell is one of three possible integers.


0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.
 

Your task is to collect maximum number of cherries possible by following the rules below:
 
Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 
Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]

Output: 5

Explanation: 

The player started at (0, 0) and went down, down, right right to reach (2, 2). 4 cherries were picked up during this single trip, and the matrix becomes 
[[0, 1, -1],
 [0, 0, -1],
 [0, 0, 0]]
 
Then, the player went left, up, up, left to return home, picking up 1 more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
 
 Solution:
 https://leetcode.com/problems/cherry-pickup/discuss/329945/Very-easy-to-follow-%3A-step-by-step-recursive-backtracking-with-memoization-N4.
 
 Instead of having two paths starting from 0,0 and then other path from N,N. 
 We can have two people starting from 0,0 and find two paths that collects maximum cherries.
 - First  person finds the path to collect maximum cherries and mark those cherries collected then
 - Second person finds another path to collect maximum cherries.
 */

public class CherryPickup {
	
	//DFS - prefer this
	public static int cherryPickup(int[][] grid) {
		int n = grid.length;
        return Math.max(0, pickup(grid, 0, 0, 0, 0, new int [n][n][n][n]));
    }
	
	private static int pickup(int[][] grid, int r1, int c1, int r2, int c2, int[][][][] dp) {
		
        if (r1 >= grid.length || r2 >= grid.length || c1 >= grid[0].length || c2 >= grid[0].length
        		|| grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        
        if (dp[r1][c1][r2][c2] != 0) return dp[r1][c1][r2][c2];
        
        // if person 1 reached the bottom right, return what's in there (could be 1 or 0)
        if (r1 == grid.length - 1 && c1 == grid[0].length - 1) return grid[r1][c1];
        
        // if person 2 reached the bottom right,
        if (r2 == grid.length - 1 && c2 == grid[0].length - 1) return grid[r2][c2];
        
        int cherries = 0;
        
        // if both persons standing on the same cell, don't double count and return what's in this cell (could be 1 or 0)
        if (r1 == r2 && c1 == c2) {
            cherries = grid[r1][c1];
        } else {
        	 // otherwise, number of cherries collected by both of them equals the sum of what's on their cells
            cherries = grid[r1][c1] + grid[r2][c2];
        }
        
        // since each person of the 2 person can move only to the bottom or to the right, then the total number of cherries
        // equals the max of the following possibilities:
        //    P1     |      P2
        //   DOWN    |     DOWN
        //   DOWN    |     RIGHT
        //   RIGHT   |     DOWN
        //   RIGHT   |     RIGHT
        
        cherries += Math.max(Math.max(pickup(grid, r1 + 1, c1, r2 + 1, c2, dp),
                                      pickup(grid, r1 + 1, c1, r2, c2 + 1, dp)),
                             Math.max(pickup(grid, r1, c1 + 1, r2 + 1, c2, dp),
                                      pickup(grid, r1, c1 + 1, r2, c2 + 1, dp))
                            );
        
        dp[r1][c1][r2][c2] = cherries;
        return dp[r1][c1][r2][c2];
    }
	

	public static void main (String[] agrs) {
		int[][] grid = {
				{0, 1, -1},
		 		{1, 0, -1},
		 		{1, 1,  1}};
		
		System.out.println(cherryPickup(grid));
		
	}
}
