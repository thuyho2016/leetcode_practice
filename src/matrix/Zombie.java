package matrix;

import java.util.*;

/* Amazon Online
 Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour. 
 Find out how many hours does it take to infect all humans?

Example:

Input:
[[0, 1, 1, 0, 1],
 [0, 1, 0, 1, 0],
 [0, 0, 0, 0, 1],
 [0, 1, 0, 0, 0]]

Output: 2

Explanation:
At the end of the 1st hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [0, 1, 0, 1, 1],
 [1, 1, 1, 0, 1]]

At the end of the 2nd hour, the status of the grid:
[[1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1],
 [1, 1, 1, 1, 1]]
 
 
int minHours(int rows, int columns, List<List<Integer>> grid) {
	// todo
}


Related problems:

https://leetcode.com/problems/rotting-oranges/  RottingOranges
https://leetcode.com/problems/walls-and-gates/ (premium)  WallAndGate.java


Time Complexity - O(Number of rows * Number of Columns)
Space Complexity - O(Number of rows * Number of Columns) (in worst case)
 */

public class Zombie {

    //BFS solution
    public static int minDays(int[][] grid) {
    	if (grid == null) return 0;
    	
    	int humanCount = 0;
    	int hours = -1; //hour taken to infect all humans
    	Queue<int[]> q = new LinkedList<>();
      
    	for(int i=0; i < grid.length; i++) {
			for(int j=0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {   //zombie 
					q.offer(new int[] {i,j}); 
					
				} else {
					humanCount++;
				}
			}
    	}
      
      //4 directions
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};   
      
      while( !q.isEmpty()) {  //count != 0 && 
          int size = q.size();
          
          for(int i=0; i < size; i++) {
        	  
              int[] current = q.poll();
              
              for(int[] dir : dirs) { //4
                  int x = current[0] + dir[0]; //next row
                  int y = current[1] + dir[1];  //next col
                  
                  if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
                      q.offer(new int[] {x, y});
                      grid[x][y] = 1; //update to human 
                      humanCount--;  
                  }
              }
          }
         
          hours++;
        //If queue got already empty, that means no more humans got infected this time so return
          if(q.isEmpty()) return hours;
         
      }
      return hours;
    }
    
    public static void main (String[] agrs) {
    	int [][] input = { { 0, 1, 1, 0, 1 }, 
    			           { 0, 1, 0, 1, 0 },
    			           { 0, 0, 0, 0, 1 }, 
    			           { 0, 1, 0, 0, 0 } };
    	
    	System.out.println(minDays(input)); //2
	
    }
}