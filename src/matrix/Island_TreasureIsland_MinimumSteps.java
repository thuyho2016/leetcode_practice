package matrix;

import java.util.*;

/*
 * Treasure Island
https://leetcode.com/discuss/interview-question/347457
 
 You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. 
 There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.

Assume the map area is a two dimensional grid, represented by a matrix of characters. 

You must start from the top-left corner of the map and can move one block up, down, left or right at a time. 

 The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner.
 Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. 
 You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe. 
 
 Output the minimum number of steps to get to the treasure.

Example:

Input:  X is treasure, D is dangerous
[['O', 'O', 'O', 'O'],
 ['D', 'O', 'D', 'O'],
 ['O', 'O', 'O', 'O'],
 ['X', 'D', 'D', 'O']]

Output: 5

Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.

	
Time complexity: O(M×N) where M is the number of rows and N is the number of columns.

Space complexity: O(M,N) because in worst case where the grid is filled with 0.

This is similar with https://leetcode.com/problems/cut-off-trees-for-golf-event/solution/
https://leetcode.com/problems/rotting-oranges/

 */

public class Island_TreasureIsland_MinimumSteps {
	
  
	/* Prefer this solution - BFS - Breadth First Search and Queue 
	
	Put it into a queue and set its value as '0' to mark as visited node.
	Iteratively search the neighbors of enqueued nodes until the queue becomes empty. 
	    
    */

	public static int numIslands_BFS(char[][] island) {
        if (island.length == 0 || island[0].length == 0 || island[0][0] =='D') return 0;
      
        if(island[0][0] == 'X') return 0;
        
        int rows= island.length;
        int cols = island[0].length;
        
        //4 directions 
        int[][] dirs = {{0,1}, {1,0}, {0, -1}, {-1, 0}};  //{x,y}
        int steps = 1;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        
       // island[0][0] = 'D'; //mark as visited
        
         boolean[][] visited = new boolean[rows][cols];
         visited[0][0] = true; //mark as visited
     
         //bfs
        //when queue is not empty, remove cur from queue
        while(!queue.isEmpty()) {
    	  int size = queue.size();
        
    	  for (int i =0; i < size; i++) {
        
    		  int[] cur = queue.remove(); // poll () to retrieve and remove
    		 
    		  for (int[] dir: dirs) {
    			  int x = cur[0] + dir[0];
    			  int y = cur[1] + dir[1];
              
    			  // check for 
    			//  if (x < 0 || x >= rows || y < 0 || y >= cols || island[x][y] == 'D') continue;
                  
    			  if (x >= 0  && x < rows && y >= 0 && y < cols && island[x][y] != 'D' && !visited[x][y]) {
    				  if (island[x][y] == 'X') return steps;
    				   visited[x][y] = true; //mark visited
    				  //island[x][y] = 'D';
    				  queue.offer(new int[]{x,y}); // or add (...)
    			  }
    		  }
    	  }
    	  steps++; 
        
      	} //while  
        return -1;
    }
	 
	
   /* DFS & recursive call 
	Treat the 2d grid map as an undirected graph and there is an edge between two horizontally or vertically adjacent nodes of value '0'.

	Algorithm
	During DFS, every visited node should be set as 'D' to mark as visited node. 
	if I see '0', increment steps, then make recursive call DFS
	Count the number of root nodes that trigger DFS, this number would be the number of islands 	
	
	*/
  
    private static int result = Integer.MAX_VALUE ; 
    
    public static int minSteps_DFS(char [][] board) {
        
        int count = 0; 
        dfs(0, 0 , board, count);
        return result; 
    }
    private static void dfs(int i , int j , char [][] board, int count) {
        if (i < 0 || i >= board.length || j < 0 || j>= board[0].length || board[i][j] =='D' )return ; 
        if (board[i][j] == 'X'){
            result = Math.min(result, count);
            return; 
        }
   
        board[i][j] = 'D';
        dfs(i+1, j, board, count+1);
        dfs(i-1, j, board, count + 1);
        dfs(i, j+1, board, count + 1);
        dfs(i, j-1, board, count + 1);

    }
	   
    public static void main(String[] args) {
        //letter O
        char[][] grid =  {{'O', 'O', 'O', 'O'},
                            {'D', 'O', 'D', 'O'},
                            {'O', 'O', 'O', 'O'},
                            {'O', 'D', 'X', 'O'}};
       
       System.out.println("minSteps by BFS: " +  numIslands_BFS(grid)); //Leetcode & Coderpad test 5
       
       char[][] grid2 =  {{'O', 'O', 'O', 'O'},
               		{'D', 'O', 'D', 'O'},
               		{'O', 'O', 'O', 'O'},
               		{'O', 'D', 'X', 'O'}};
      
      System.out.println("minSteps by DFS: " + minSteps_DFS(grid2));
    
	}
  
	  
}