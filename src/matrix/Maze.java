package matrix;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 490. The Maze
https://leetcode.com/problems/the-maze/

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that 
the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 
Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.


Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.


 */
public class Maze {
	// DFS solution - up, right, down, left
	// for each cell, I ask do you have a path?
	// if the cell is not the end, return false. Otherwise, return true
	
	/* i choose one path at a time and try to go as deep as possible into the levels of the tree.
	- I make a recursive function dfs( maze, start, destination, visited)
	- visited[i][j] represents the current position that has already been reached ealier.
	- When I reach the particular position in the maze, I mark a "true" at the current position in the visited array.
	- From the start position, I can move either 4 directions. By choosing the four direction, i can determine all the end points. The new endpoint will act as the new start point for the traversals.

	 Time complexity: O(mn),  mm and nn refers to the number of rows and columns of the maze.
	 Space complexity: O(mn). visited array of size m*n is used.
	*/
	  
	  public static boolean hasPath_DFS(int[][] maze, int[] start, int[] destination) {
		int rows = maze.length;
	    int cols = maze[0].length;
	    boolean[][] visited = new boolean[rows][cols];
	    //boolean[][] visited = new boolean[maze.length][maze[0].length];
	    return dfs( maze, start, destination, visited);
	        
	  }
	  
	  private static boolean dfs(int[][] maze, int[] start, int[] dest, boolean[][] visited) {
	    
	    if (visited[start[0]][start[1]]) return false;
	    
	    if (start[0] == dest[0] && start[1] == dest[1]) {
	    	return true; //start position && destination position are the same
	    }
	                
	    visited[start[0]][start[1]] = true;  // mark "true" at start position for visited
	    
	    // 4 directions from each position to up, down, left and right	   
		// int[][] directions= {{-1,0},{1,0},{0,-1},{0,1}};	    
		int[][] directions = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}}; 
		     
	   	    
	    for (int[] dir: directions ) {
	    	int x = start[0] + dir[0];  //0 + 0 = 0
	    	int y = start[1] + dir[1];  // 4 -1 = 3
	    	System.out.println (" Position: {" +x + "," +y + "}" );
	    	
	    	while (0 <= x && x < maze.length && 0 <= y && y < maze[0].length && maze[x][y] == 0) {
	    		x += dir[0];
	    		y += dir[1];	
	    		System.out.println ("Move {" +x + "," +y + "}" );
	    	}
 
	    	if (dfs(maze, new int [] { x - dir[0], y - dir[1]}, dest, visited)){
	    		return true;
	    	}  
	    }
	    return false;
	   
	 
	    
	 /*   int rows = maze.length;
	    int cols = maze[0].length;
	    for (int[] dir : directions) {   // try four direction until it hits the wall
	    	int dx = start[0];
	    	int dy = start[1];
	    	
	    	//{-1,0}  dir[0] = -1, dir[1] = 0
	    	while (dx + dir[0] >= 0 && dx + dir[0] < rows && dy + dir[1] >= 0 && dy + dir[1] < cols && maze[dx + dir[0]][dy + dir[1]] == 0) {
	    		dx = dx + dir[0];
	    		dy = dy + dir[1];
	    	} 
	    	if (dfs(maze, new int[] {dx, dy} , dest, visited)) {
	    		return true;
	    	}
	    }
	    return false */
	                
	  }
	  
	  /*Best solution
	  we make use of a queuequeue. We start with the ball at the startstart position. 
	  For every current position, we add all the new positions possible by traversing in all the four directions(till reaching the wall 
	  or boundary) into the queuequeue to act as the new start positions and mark these positions as True in the visited array. 
	  When all the directions have been covered up, we remove a position value, s, from the front of the queue 
	  and again continue the same process with s acting as the new start position.


	  */
	  public static boolean hasPath_BFS(int[][] maze, int[] start, int[] destination) {
	        boolean[][] visited = new boolean[maze.length][maze[0].length];
	        
	        int[][] dirs= {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	        Queue < int[] > queue = new LinkedList < > ();
	        queue.add(start); //[0,4]
	        
	        visited[start[0]][start[1]] = true; //initialized visited with start position
	        
	        while (!queue.isEmpty()) {
	            int[] s = queue.remove(); //When all the directions have been covered up, we remove a position value, s, from the front of the queue
	            
	            if (s[0] == destination[0] && s[1] == destination[1]) { // check if start [0,4] and destination[4,4] match ?
	                return true;
	            }
	            
	            for (int[] dir: dirs) { //  // try four direction until it hits the wall [0,1]
	                int x = s[0] + dir[0];  // 0 + 0
	                int y = s[1] + dir[1]; //0 + 1 = 5 , out of the boundary
	                                                  //  [0,5], [0,3]
	                
	                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
	                	System.out.println (" Position: {" +x + "," +y + "}" ); //[0,3] + [0,-1]
	                    x += dir[0];
	                    y += dir[1];
	                    System.out.println ("Move {" +x + "," +y + "}" );  //[0,2]
	                }
	                
	                if (!visited[x - dir[0]][y - dir[1]]) {
	                	visited[x - dir[0]][y - dir[1]] = true;
	                    queue.add(new int[] {x - dir[0], y - dir[1]});
	                }
	            }
	        }
	        return false;
	    }
	  
	  
	   public static void main(String[] args) {
	     int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
	     int[] start = {0,4};
	     int[] destination = {4,4};
	    // System.out.println("DFS: " + hasPath_DFS(maze, start, destination)); //true
	     
	     System.out.println("BFS: " + hasPath_BFS(maze, start, destination)); //true
	     
	/*     int[][] maze2 = {{0, 0, 1, 0, 0}, { 0, 0, 0, 0, 0} ,{0, 0, 0, 1, 0}, {1, 1, 0, 1, 1},{0, 0, 0, 0, 0}};
	     int[] start2 = {0,4};
	     int[] destination2 = {3,2};
	     System.out.println("DFS: " + hasPath_DFS(maze2, start2, destination2));
	 */    
	   }
}
