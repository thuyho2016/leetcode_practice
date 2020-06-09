package matrix;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix_Nearest {
	
	public static int updateMatrix_BFS(char[][] grid) {
	      if ( grid.length == 0 || grid[0].length == 0) return 0;
	    
	      int rows= grid.length;
	      int cols = grid[0].length;
	    
	      int numIslands = 0;
	      for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	      
	          if (grid[i][j] == 1) {
	        	//call bfs
	        	  numIslands++;
	          	  bfs(grid, i, j);
	          	  
	          }
	        }
	      }
    	  
	      return numIslands;
	      
	  }
	
	   	 
	public static void bfs(char[][] grid, int i, int j) {
		int rows = grid.length;
		int cols = grid[0].length;
   
		Queue<int[]> queue = new LinkedList<>();
	      
		queue.add(new int[]{i,j});
  	 // grid[i][j] = '0';
  	  
	     int[][] dirs = {{0,1}, {1,0}, {0, -1}, {-1, 0}};  //{x,y}
	     
	    //when queue is not empty, remove cur from queue
	     while(!queue.isEmpty()) {
	    	
		      int[] cur = queue.poll(); // remove () to retrieve and remove
		      
		      for (int[] dir: dirs) {
			        int x = cur[0] + dir[0];
			        int y = cur[1] + dir[1];
			        
			       // if (x < 0  || x >= rows || y < 0 || y >= cols ||  grid[x][y] == 0)  continue;
			        
			      //a valid neighbor must:1.inside of bound;2.not visited;3 . == 1
			        if (x >= 0  && x < rows && y >= 0 && y < cols && grid[x][y] == 1 ) {
			        	grid[x][y] = 0; //mark visited
			        	queue.offer(new int[]{x,y}); // or add (...)
			        }
		      }
	     }
	  }
	  
	public static void main(String[] args) {
		  
		  char[][] grid = {{0,0,0},
				           {1,0,1,0}, 
				           {1,1,1}};		 
		  
        //NOTE: test 1 by 1
		  System.out.println("Number of island by BFS: " +  updateMatrix_BFS(grid)); //1
		 // System.out.println("Number of island by BFS: " +  updateMatrix_BFS(grid2)); //3		 	  
		  
	}
}
