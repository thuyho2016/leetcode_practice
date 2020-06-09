package matrix;

import java.util.*;

/*
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 
Example 1:

Input:
	11110
	11010
	11000
	00000
	
Output: 1
	
Example 2:
	
	Input:
	11000
	11000
	00100
	00011
	
Output: 3
		
Time complexity: O(M×N) where M is the number of rows and N is the number of columns.

Space complexity: O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
 */

public class Island_NumberOfIsland_DFS_BFS {
	
		/* DFS & recursive
		Treat the 2d grid map as an undirected graph and there is an edge between two horizontally or vertically adjacent nodes of value '1'.

		Algorithm
		During DFS, every visited node should be set as '0' to mark as visited node. 
		Count the number of root nodes that trigger DFS, this number would be the number of islands 
		
	    we found an unvisited island, increment and mark the current island
		if node is 1 (island), increment number of Island, and go deeply row & col to check if 1 represents
		
		*/
		
	public static int numIslands_DFS(char[][] grid) {
		
			 
		 if (grid == null || grid.length == 0) return 0;
		 
		 int rows = grid.length;
		 int cols = grid[0].length;
		 
		 if (cols == 0) return 0;
		 
		 int numIslands = 0;
		 
		 for (int i = 0; i < rows; i++) {
			 for (int j = 0; j < cols; j++) {
				 
				 if (grid[i][j] == 1) { //if node is 1 ( island), increment number of Island, and go deeply row & col to check if 1 represents					
					 // we found an unvisited island, increment and mark the current island
					 dfs(grid, i, j);  //recursive
					 ++numIslands;
				 }
			 }
	      }
	      return numIslands;
	}
	    
	public static void dfs(char[][] grid, int r, int c) {
      // check if I am out of grid
	    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)  //integer, no '0'
	       return;
	    
	    if (grid[r][c] == 1) {
	   
		    grid[r][c] = 0; //marked as visited
		    
		    // call dfs for 4 directions
		    dfs(grid, r - 1, c);  //up
		    dfs(grid, r + 1, c); // down
		    dfs(grid, r, c - 1); // left
		    dfs(grid, r, c + 1); // right
	    }
	
	  }

	/* BFS - Breadth First Search and Queue work when mark visited[i][j] = false or true;
	
	Put it into a queue and set its value as '0' to mark as visited node.
	Iteratively search the neighbors of enqueued nodes until the queue becomes empty.    
    */
	 	  
	  public static int numIslands_BFS(char[][] grid) {			  
		
		  if (grid == null || grid.length == 0) return 0;
		  
		  int rows = grid.length;
		  int cols = grid[0].length;
		 
		  boolean[][] marked = new boolean[rows][cols]; // or visit by setting value as '0'
		 
		  int islandCount = 0;
		  
		  for (int i = 0; i < rows; ++i) {
		    for (int j = 0; j < cols; ++j) {
		    	
		      if (!marked[i][j] && grid[i][j] == 1) {
		        bfs(i, j, grid, marked);
		        ++islandCount;
		      }
		    }
		  }
		  return islandCount;
		}

		private static void bfs(int i, int j, char[][] grid, boolean[][] marked) {
		  int rows = grid.length;
		  int cols = grid[0].length;
		  
		  Queue<int[]> queue = new LinkedList<>();
		  queue.offer(new int[] { i, j });
		  
		 // marked[i][j] = true;
		  int[][] direction = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		  
		  // bfs
		  while (queue.size() > 0) {
			  
		    int[] curr = queue.poll(); // remove the head of queue
		    
		    for (int[] dir : direction) {
		      int x = curr[0] + dir[0];
		      int y = curr[1] + dir[1];
		      
		      if (x >= 0 && x < rows && y >= 0 && y < cols) {
		    	     //a valid neighbor must: 1.inside of bound; 2.not visited; 3 . == 1
		        if (!marked[x][y] && grid[x][y] == 1) {
		          queue.offer(new int[] { x, y });
		          marked[x][y] = true; // mark when visited enqueued
		        }
		      }
		    }
		  }
		}

		
	//Amazon Test - use DFS to solve this problem
	 public static int numIslands(int rows, int column, List<List<Integer>> grid) {	     
	        
       	 if (grid == null || grid.size() == 0) return 0;
		 			  
		 int counts = 0;
		 
		 //convert List of List Integer to 2D array
		 int[][] matrix = new int[grid.size()][grid.get(0).size()];
		 
		 for(int i = 0; i < grid.size(); i++){
			for (int j = 0; j < grid.get(i).size(); j++) {
				matrix[i][j] = grid.get(i).get(j);
			}
		 }
		 System.out.println(Arrays.deepToString(matrix)); //[[1, 1, 0, 0], [0, 0, 1, 0], [0, 0, 0, 0], [1, 0, 1, 1], [1, 1, 1, 1]]
	        
	        
		 for (int i = 0; i < rows; i++) { //
			 for (int j = 0; j < column; j++) {
				 //if node is 1, increment counts, and go deeply row & col of the grid to check if 1 represents					
				 // if found an unvisited node, increment and mark the current node
				 if (matrix[i][j] == 1) { 
					 dfs2(matrix, i, j); 
					 ++counts;
				 }
			 }
	      }
		  //Final matrix: [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
	      return counts;
	}
			
		 
	public static void dfs2(int[][] grid, int r, int c) {
		
      // check if I am out of grid
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)  //integer, no '0'
		       return;
		    
		    if (grid[r][c] == 1) {
		   
			    grid[r][c] = 0; //marked as visited
		    
		        // call dfs for 4 directions
			    dfs2(grid, r - 1, c);  //up
			    dfs2(grid, r + 1, c); // down
			    dfs2(grid, r, c - 1); // left
			    dfs2(grid, r, c + 1); // right
	    }
    }
			
	
	  public static void main(String[] args) {
		  //NOTE: test 1 by 1
/*		  char[][] grid = {{1,1,1,1,0}, {1,1,0,1,0}, {1,1,0,0,0} ,{0,0,0,0,0}};		
		  System.out.println("Number of island by BFS: " +  numIslands_BFS(grid)); //1
		  
	     char[][] grid2 = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,1,0,0} ,{0,0,0,1,1}};
		  System.out.println("Number of island by BFS: " +  numIslands_BFS(grid2)); //3		 
		  
	  
		  // put here since it marked 0
		  char[][] grid3 = {{1,1,1,1,0}, {1,1,0,1,0}, {1,1,0,0,0} ,{0,0,0,0,0}};
		  char[][] grid4 = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,1,0,0} ,{0,0,0,1,1}};
		  
		  System.out.println("Number of island by DFS: " +  numIslands_DFS(grid3)); //1
		  System.out.println("Number of island by DFS: " +  numIslands_DFS(grid4)); //3	
		  
*/
		//  int[][] input = {{1,1,0,0}, {0,0,1,0}, {0,0,0,0} ,{1,0,1,1}, {1,1,1,1}};		 
		  List<Integer> l1 = Arrays.asList(1,1,0,0);
		  List<Integer> l2 = Arrays.asList(0,0,1,0);
		  List<Integer> l3 = Arrays.asList(0,0,0,0);
		  List<Integer> l4 = Arrays.asList(1,0,1,1);
		  List<Integer> l5 = Arrays.asList(1,1,1,1);
		  
		  List<List<Integer>> input = new ArrayList<>();
		  input.add(l1);
		  input.add(l2);
		  input.add(l3);
		  input.add(l4);
		  input.add(l5);
			
		  System.out.println("Amazon Test by DFS: " +  numIslands(5,4, input));
		  
	      
	  }
}