package matrix;
/*
63. Unique Paths II
  
https://leetcode.com/problems/unique-paths-ii/
 
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?


Input: 3x3 grid , obstacle is 1
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output:  2

Explanation:

There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right


M rows and N columns, Time complexity O(n*m)
Space complexity is 0(m *n ) because i need to make dp matrix

Steps:

1. make a dp matrix same dimensions
2. fill first position (0,0) with 1 if matrix[0][0] = 0

3. iterate elements of matrix
   if element is 0
       sum left and sum top from dp
   
4. In the end, return the end position of dp: dp[row - 1][col -1] = 2
 

grid = [[0, 0, 0], [0, 1, 0], [0, 0, 0]]

Steps: 4 cases
 1.  if(i == 0 && j == 0) 
     Mark 1 to indicate already visited [[1, 0, 0], [0, 1, 0], [0, 0, 0]]
 
 2. row i = 0 , col j = 1, 2   go to the right and mark 1 by using value of left cell to current cell
    
 	j = 1: [[1, *1, 0], [0, 1, 0], [0, 0, 0]]
 	j = 2: [[1, 1, *1], [0, 1, 0], [0, 0, 0]]
 	
 3. col j = 0, i = 1,2: go down and mark 1 by using value of up cell 
  
    i = 1: [[1, 1, 1], [*1, 1, 0], [0, 0, 0]]
    i = 2: [[1, 1, 1], [1, 0, 1], [*1, 0, 0]]
    
 4. else 
 case i = 1, j = 1: set obstacle to 0. [[1, 1, 1], [1, *0, 0], [0, 0, 0]]  
 
 case i = 1, j = 2
    [[1, 1, 1], [1, 0, *1], [0, 0, 0]]
    
 case  i = 2, j = 1
    [[1, 1, 1], [1, 0, 1], [1, *1, 0]] 
    
 case  i =2, j = 2
    [[1, 1, 1], [1, 0, 1], [1, 1, *2]]  
      
 */


public class UniquePathsII_Obstacle_HowManyPaths {
	
	//Dynamic Programming
	public static int uniquePathsWithObstacles(int[][] grid) { //[[0, 0, 0], [0, 1, 0], [0, 0, 0]]
		
        int rows = grid.length;
        int cols = grid[0].length;  
        
		//Empty case
        if(grid.length == 0) return 0;
        
        // 3. iterate elements of matrix
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
            	
            	if (grid[i][j] == 1) // obstacle, set to 0
            		grid[i][j] = 0;
            	else if (i == 0 && j == 0) 
            		grid[i][j] = 1;      //start by mark 1, already visited 
            	else if ( i == 0)        //row = 0, j = 1,2: mark 1 by using value of left cell to current cell
            		grid[i][j] = grid[i][j-1];  //j = 1 [[1, 1, 0],... ; j = 2 [[1, 1, 1],...
            	else if (j == 0)	  //col = 0, i = 1,2:go down and mark 1 by using value of up cell to current cell
            		grid[i][j] = grid[i-1][j];  // [[1, 1, 1], [1, *1, 0], [0, 0, 0]]; 
            	else                                         // sum = value of up cell + value of down cell
            		grid[i][j] = grid[i][j-1] + grid[i-1][j]; //i = 1, j= 2: [[1, 1, 1], [1, 0, *1], [0, 0, 0]]
            }
        }
        
        return grid[rows - 1][cols - 1]; // represent bottom right
       
   }
		
	public static void main(String[] args) {
		int[][] obstacleGrid = {{0,0,0},
				                {0,1,0},
				                {0,0,0}};
		
		System.out.println("Unique paths: " + uniquePathsWithObstacles(obstacleGrid));
    }
	
}