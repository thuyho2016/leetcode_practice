package matrix;

import java.util.*;

/* 
 * 286. Walls and Gates
https://leetcode.com/problems/walls-and-gates/

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  

Time Complexity - O(Number of rows * Number of Columns)
Space Complexity - O(Number of rows * Number of Columns) (in worst case)

To pratice more:
Robot Room cleaner
Surround Regions
Shortest Distance from all building
 */

public class WallAndGate {

	private static final int INF = Integer.MAX_VALUE;
////	private static final int GATE = 0;
	private static final int WALL = -1;
	//BFS
	 public static void wallsAndGates_BFS(int[][] grid) {
	    	if (grid == null || grid.length == 0) return;
	   
	    	Queue<int[]> q = new LinkedList<>();
	      
	    	for(int i=0; i < grid.length; i++) {
				for(int j=0; j < grid[0].length; j++) {
					if (grid[i][j] == 0 ) {   //gate
						q.offer(new int[] {i,j}); 
					}
				}
	    	}
	      
	      //4 directions
	      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};   
	      
	      while( !q.isEmpty()) {  
	          int size = q.size();
	          
	          for(int i= 0; i < size;i++) {
	        	  
	              int[] current = q.poll();
	              
	              for(int[] dir : dirs) { //4
	                  int x = current[0] + dir[0]; //next row
	                  int y = current[1] + dir[1]; //next col
	                  
	                  if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == INF ) {
	                      q.offer(new int[] {x, y});
	                      grid[x][y] = grid[current[0]][current[1]] + 1; //update to GATE
	                     
	                  }
	              }
	          }
	      }
	    }
	 
    //DFS solution - easy to understand
    public static void wallsAndGates(int[][] rooms) {
    	if (rooms == null) return;
    	
    	 for (int i = 0; i < rooms.length; ++i) {
             for (int j = 0; j < rooms[i].length; ++j) {
                 if (rooms[i][j] == 0) {
                	 dfs(rooms, i, j, 0);
                 }
             }
         }
     }
     
    private static void dfs(int[][] rooms, int i, int j, int steps) {   // steps = distance
         if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < steps) return;
         
         // if the "steps" is calculated, dont need to calculate again
         if ( rooms[i][j] >= steps) {
	         rooms[i][j] = steps;
	         
	     
	         dfs(rooms, i + 1, j, steps + 1);
	         dfs(rooms, i - 1, j, steps + 1);
	         dfs(rooms, i, j + 1, steps + 1);
	         dfs(rooms, i, j - 1, steps + 1);
         }
    }
    
    public static void main (String[] agrs) {
    	
    	int [][] input = {
    			{2147483647, -1, 0, 2147483647},
    			{2147483647,2147483647,2147483647, -1},
    			{2147483647, -1, 2147483647, -1},
    			{0, -1, 2147483647,2147483647 } 
    			};
    	
    	//wallsAndGates(input);
    	wallsAndGates_BFS(input);
    	
    	System.out.println(Arrays.deepToString(input)); 
    	//[[3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4]]
	
    }
}