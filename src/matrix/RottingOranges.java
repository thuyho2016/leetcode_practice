package matrix;

import java.util.*;

/*
 994. Rotting Oranges ( easy level)
 https://leetcode.com/problems/rotting-oranges/
 
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
If this is impossible, return -1 instead.

Input: [[2,1,1],
		[1,1,0],
		[0,1,1]]
		
Output: 4

Explanation:
[2,1,1], -->    [2, 2, 1], -->	[2, 2, 2], -->  [2, 2, 2], -->  [2, 2, 2],
[1,1,0],		[2, 1, 0], 		[2, 2, 0],		[2, 2, 0],		[2, 2, 0],
[0,1,1]			[0, 1, 1]		[0, 1, 1]]		[0, 2, 1]		[0, 2, 2]



Input: [[2,1,1],
		[0,1,1],
		[1,0,1]]
		
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.


Time Complexity: O(N) where N is the number of cells in the grid.
 Space : O(N)
 */

public class RottingOranges {

    //BFS solution
    public static int orangesRotting(int[][] grid) {
	  int freshCount = 0;
	  
      Queue<int[]> q = new LinkedList<>();
      
      
      for(int i=0; i < grid.length;i++) {
          for(int j=0;j < grid[0].length;j++) {
              if (grid[i][j] == 1) {  //fresh orange
                  freshCount++;
              }
              
              if(grid[i][j] == 2) { // if the orange is rotten, add it into the queue
                  q.offer(new int[] {i,j}); 
              }
          }
      }
      //4 directions
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      int dist = 0; //minimun min
      
      while(freshCount != 0 && !q.isEmpty()) {
          int size = q.size();
          
          for(int i=0; i < size;i++) {
        	  
              int[] current = q.poll();
              
              for(int[] dir : dirs) { //4
                  int x = current[0] + dir[0]; //nextX row
                  int y = current[1] + dir[1];  //nextY col
                  
                  if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) { // fresh orange
                      q.offer(new int[] {x, y});
                      grid[x][y] = 2; //It's rotten now.
                      freshCount--;  //so decrease the count of fresh oranges
                  }
              }
          }
          dist++;
      }
      return freshCount == 0 ? dist : -1;
    }
    
    public static void main (String[] agrs) {
    	int [][] input = { {2,1,1},
    						{1,1,0},
    						{0,1,1}};
    	
    	System.out.println( orangesRotting(input)); //4
	
    }
}