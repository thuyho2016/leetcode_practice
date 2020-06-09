package matrix;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 695. Max Area of Island
https://leetcode.com/problems/max-area-of-island/
 
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.


Time Complexity: O(R * C), where R is the number of rows in the given grid, and C is the number of columns. 
Space Complexity: O(R * C), the space used by seen to keep track of visited squares, and the space used by the call stack during our recursion
 */


public class MaxAreaOfIsland {
	
	//DFS - prefer this
	public static int maxAreaOfIsland(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		
        int max = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			max = Math.max(max, dfs(grid, i, j));
        		}
        			
        	}
        }
        
        return max;
    }
	
	public static int dfs(int[][] grid, int r, int c) {
		if ( r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0)
			return 0;
		
		grid[r][c] = 0; // mark it seen or seen[r][c] = true;
		
		return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c) 
			+ dfs(grid, r, c - 1) + dfs(grid, r, c + 1);
	}
	
	//BFS
	public static int maxAreaOfIsland_BFS(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int ans = 0;
        
        //4 directions
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
               
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                
            	if(grid[i][j] == 1 && !visited[i][j]){
                    int temp = 1;
                    visited[i][j] = true;
                    
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        
                        for(int[] dir: dirs){
                            int x = cur[0] + dir[0];  // newX
                            int y = cur[1] + dir[1];   //newY
                            
                            while(x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1 && !visited[x][y]){
                                temp++;
                                visited[x][y] = true;
                                q.add(new int[]{x, y});
                                
                                x += dir[0];
                                y += dir[1];
                            }
                        }
                    }
                    //System.out.print(i + "\t" + j +"\t" + temp + "\n");
                    ans = Math.max(ans, temp);
                }
            }
        }
        return ans;
	}
	
	public static void main (String[] agrs) {
		int[][] grid = {
						 {0,0,1,0,0,0,0,1,0,0,0,0,0},
						 {0,0,0,0,0,0,0,1,1,1,0,0,0},
						 {0,1,1,0,1,0,0,0,0,0,0,0,0},
						 {0,1,0,0,1,1,0,0,1,0,1,0,0},
						 {0,1,0,0,1,1,0,0,1,1,1,0,0},
						 {0,0,0,0,0,0,0,0,0,0,1,0,0},
						 {0,0,0,0,0,0,0,1,1,1,0,0,0},
						 {0,0,0,0,0,0,0,1,1,0,0,0,0}
						};
		
		System.out.println(maxAreaOfIsland(grid)); //6
		
		int[][] grid2 = {
				 {0,0,1,0,0,0,0,1,0,0,0,0,0},
				 {0,0,0,0,0,0,0,1,1,1,0,0,0},
				 {0,1,1,0,1,0,0,0,0,0,0,0,0},
				 {0,1,0,0,1,1,0,0,1,0,1,0,0},
				 {0,1,0,0,1,1,0,0,1,1,1,0,0},
				 {0,0,0,0,0,0,0,0,0,0,1,0,0},
				 {0,0,0,0,0,0,0,1,1,1,0,0,0},
				 {0,0,0,0,0,0,0,1,1,0,0,0,0}
				};
		System.out.println(maxAreaOfIsland_BFS(grid2)); //6
		
		int[][] grid3 = {{1,1,0,0,0},
						{1,1,0,0,0},
						{0,0,0,1,1},
						{0,0,0,1,1}};
		
		System.out.println(maxAreaOfIsland(grid3)); //4
		
		int[][] grid4 = {{1,1,0,0,0},
				{1,1,0,0,0},
				{0,0,0,1,1},
				{0,0,0,1,1}};
		System.out.println(maxAreaOfIsland_BFS(grid4)); 
		
	}
		
	

}
