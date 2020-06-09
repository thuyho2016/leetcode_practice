package matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 505. The Maze II
https://leetcode.com/problems/the-maze-ii/

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls.
The start and destination coordinates are represented by row and column indexes.
 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0 <--start
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0 <-- destination

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12

Explanation: One shortest way is : left -> down -> left ( 3 times)  -> down -> right (2 times) -> down ( 2 times) -> right (2times).
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
             
Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.

 */
public class MazeII_shortestpath {
	
/* DFS solution - up, right, down, left
	// for each cell, I ask do you have a path?

i choose one path at a time and try to go as deep as possible into the levels of the tree.
- I make a recursive function dfs( maze, start, destination, visited)

- From the start position, I can move either 4 directions. By choosing the four direction, i can determine all the end points. The new endpoint will act as the new start point for the traversals.

distance[i][j] represents the minimum number of steps required to reach the position (i, j)(i,j) starting from the start position. 
This array is initialized with largest integer values in the beginning.

If distance[k][l] + count is lesser than distance[i][j]distance[i][j], we can reach the position (i,j)(i,j) from the current route in lesser number of steps. 
Thus, we need to update the value of distance[i][j] as distance[k][l] + count. Further, now we need to try to reach the destination, dest, from the end position (i,j)(i,j),
 since this could lead to a shorter path to destdest. Thus, we again call the same function dfs but with the position (i,j)(i,j) acting as the current position.
	 
Time complexity : O(m * n * max(m,n)).  m and n refers to the number of rows and columns of the maze. 
Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.

Space complexity : O(mn). distance array of size m * n is used.

*/
	  
	//Best solution
	  public static int shortestDistance(int[][] maze, int[] start, int[] dest) {
		int rows = maze.length;
	    int cols = maze[0].length;
	    int[][] distance = new int[rows][cols];
	 
	    
	    for (int[] row: distance)
	    	Arrays.fill(row, Integer.MAX_VALUE);
	    
	    distance[start[0]][start[1]] = 0;  // Initialize start distance to 0
	    
	    dfs( maze, start, distance);
	    
	    return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
	        
	  }
	  
	  private static void dfs(int[][] maze, int[] start, int[][] distance) {

	    int rows = maze.length;
	    int cols = maze[0].length;
	    // directions from each position to up, down, left and right
	    int[][] directions= {{-1,0},{1,0 }, {0,-1}, {0,1}};	    
	    // int[][] directions = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};   
	    
	    for (int[] dir: directions ) {

	      int x = start[0] + dir[0];  //0 + 0 = 0
	      int y = start[1] + dir[1];  // 4 -1 = 3  
	      // { x,y }
	      int count = 0;
	      
	      while (0 <= x && x < rows && 0 <= y && y < cols && maze[x][y] == 0) { //valid neighnor, count path
	         x += dir[0];
	         y += dir[1];
	         count++;
	      }
 
	      // check if distance[k][l] + count is lesser than distance[i][j]
	       if (distance[start[0]][start[1]] + count < distance[start[0]][y - dir[1]]){
	    	   
	    	   distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
	    	   dfs(maze, new int[] { x - dir[0], y - dir[1]}, distance);
	       }                                            
	    
	    }
	
	  }
	  
	  
	  //BFS
	  public static int shortestDistance_BFS(int[][] maze, int[] start, int[] destination) {
	       
	 	    int[][] distance= new int[maze.length][maze[0].length];
	 	    
	 	    for (int[] row: distance)
	 	    	 Arrays.fill(row,-1);  // Set all cell as -1  // Arrays.fill(row, Integer.MAX_VALUE); 
	 	    
	 	   // Initialize start distance to 0
	 	    distance[start[0]][start[1]] = 0;
	 	    
	      
	     // directions to top, bottom, left and right
	        int[][] dirs ={{-1,0},{1,0},{0,-1},{0,1}};
	        
	        Queue < int[] > queue = new LinkedList<> ();
	        queue.add(start);	        
	    
	        
	        while (!queue.isEmpty()) {
	            int[] s = queue.remove(); //When all the directions have been covered up, we remove a position value, s, from the front of the queue
	            	     //s = [0,4]       
	            
	            for (int[] dir: dirs) {   // try four direction until it hits the wall
	                int dx = s[0];  //0
	                int dy = s[1];   //4
	                int count = distance[s[0]][s[1]]; //0
	                
	                //dir = {-1,0} , dir[0] = -1, dir[1] = 0
	                while (dx + dir[0] >= 0 && dx + dir[0] < maze.length && dy+ dir[1] >= 0  && dy + dir[1]< maze[0].length && maze[dx + dir[0]][dy + dir[1]] == 0) {
	                    dx += dir[0];
	                    dy += dir[1];
	                    count++;
	                }
	                
	             
	                // If this cell is first time to reach or the distance to this cell is shorter
	                // add it to queue and update distance
	     	       if (distance[dx][dy] == -1 || distance[dx][dy] > count){
	     	    	   
	     	    	   distance[dx][dy] = count;  //distance[2,4] = 2
	     	    	   
	     	    	   queue.add(new int[] {dx , dy}); //[2,4], [0,3]
	     	       }
	            }
	        }
	        return distance[destination[0]][destination[1]];
	  }
	  
	  
	   public static void main(String[] args) {
	     int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
	     int[] start = {0,4};
	     int[] destination = {4,4};
	     
	     System.out.println("By DFS: " + shortestDistance(maze, start, destination)); //12
	     System.out.println("By BFS: " + shortestDistance_BFS(maze, start, destination)); //12
	
	     
	/*     int[][] maze2 = {{0, 0, 1, 0, 0}, { 0, 0, 0, 0, 0} ,{0, 0, 0, 1, 0}, {1, 1, 0, 1, 1},{0, 0, 0, 0, 0}};
	     int[] start2 = {0,4};
	     int[] destination2 = {3,2};
	     System.out.println(shortestDistance(maze2, start2, destination2)); //-1
	 */    
	   }
}
