package matrix;
/*
62. Unique Paths
  
https://leetcode.com/problems/unique-paths/
 
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

How many possible unique paths are there?

Example 1:

Input: m = 3, n = 2
Output: 3

Explanation:

From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:

1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Matrix 3 X 2

1 -> 1 -> 1
|    |    |
1 -> 2 -> 3 (  formula = up cell + left cell = 1 + 2 = 3)


Matrix 3 X 3,  Output: 6
1 -> 1 -> 1
|    |    |
1 -> 2 -> 3
|    |    |
1 -> 3 -> 6   (3 + 3 = 6)
       
https://www.youtube.com/watch?v=6qMFjFC9YSc

How many ways to go from top-left corner grid[0][0] to bottom-right corner grid[m][n] ? Answer: the bottom right is number of paths

Solutions:
Since we can only go "DOWN" and "RIGHT", therefore adding up their total ways would give the total ways for current block
formula = up cell + left cell 

Time complexity O(n * m)
 */


public class UniquePaths_HowManyWays {
	
	/**Dynamic Programming - answer - the bottom right is number of paths = 6
	   1  1  1 
	   1  2  3
	   1  3  6
	
	*/
	public static int uniquePaths(int m, int n) {
		// create 2 dimension array
		int[][] dp = new int[m][n];
		
		//fill first cell in every row by going down  [[1, 0, 0], [1, 0, 0], [1, 0, 0]]
		for(int i = 0; i < m; i++) {  //i <= m  
			dp[i][0] = 1;        //
		}
		//fill every column, go right  [[1, 1, 1], [1, 0, 0], [1, 0, 0]]
		for (int j = 0; j < n; j++) {  //i <= m
			dp[0][j] = 1;
		}
		
		for(int i = 1;i < m; i++) {   // go to all rows
         for(int j = 1;j < n; j++) {  // go to all columns
        	   //current position =  value of cell above +  value of cell left
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
        
		return dp[m - 1][n-1]; // represent the bottom right. It is number of paths
    }
	
	public static void main(String[] args) {
		//System.out.println("Unique paths: " + uniquePaths(3, 2));
		System.out.println("Unique paths: " + uniquePaths(3, 3));
    }
	
}