package matrix;

import java.util.Arrays;

/*
 * 73. Set Matrix Zeroes
https://leetcode.com/problems/set-matrix-zeroes/

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]


Solution:

  [1,1,1],
  [1,0,1],
  [1,1,1]
  

Time Complexity: O(M×N) where M and N are the number of rows and columns respectively.

Space Complexity: O(1).
 */

public class SetZeroes {
	
	// DFS solution
		public static void setZeroes2(int[][] matrix) {
			if(matrix == null)
		        return;		
			
			int n = matrix.length;
	        int m = matrix[0].length;
	        boolean[][] visited = new boolean[n][m];
	        
	        for(int i = 0; i < n; i++) {
	            for( int j=0 ; j < m; j++) {
	                if(matrix[i][j] == 0 && !visited[i][j])
	                    dfs(matrix, i, j, n, m, visited);
	            }
	        }
	    }
	    
	    
	    public static void dfs(int[][] matrix, int row, int col, int N, int M, boolean[][] visited) {
	        visited[row][col] = true;
	        
	        for(int i = 0; i < N; i++) {
	            if(matrix[i][col] != 0)
	                visited[i][col] = true;
	            matrix[i][col] = 0;
	        }
	        
	        for(int j = 0; j < M; j++) {
	            if(matrix[row][j] != 0)
	                visited[row][j] = true;
	            matrix[row][j] = 0;
	        }
	    }
		
	    
	//solution - Space O(1)
	//Basic: using the first row and column as a memory to keep track of all the 0's in the entire matrix.
	public static void setZeroes(int[][] matrix) {
	    boolean fr = false,fc = false;
	    
	    for(int i = 0; i < matrix.length; i++) {
	        for(int j = 0; j < matrix[0].length; j++) {
	        	
	            if(matrix[i][j] == 0) {  
	                if(i == 0) 
	                	fr = true;
	                if(j == 0) 
	                	fc = true;
	                matrix[0][j] = 0; // If an element is zero, we set the first element of the corresponding row and column to 0
	                matrix[i][0] = 0;
	            }
	        }
	    }
	    
	    //using the first row and first column, update the elements.
	    for(int i = 1; i < matrix.length; i++) {
	        for(int j = 1; j < matrix[0].length; j++) {
	            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
	                matrix[i][j] = 0;
	            }
	        }
	    }
	    
	    if(fr) { //first row needs to be set to zero
	        for(int j = 0; j < matrix[0].length; j++) {
	            matrix[0][j] = 0;
	        }
	    }
	    if(fc) {  //first column needs to be set to zero 
	        for(int i = 0; i < matrix.length; i++) {
	            matrix[i][0] = 0;
	        }
	    }
	    
	}

	
   
    public static void main (String[] agrs) {
    	int [][] input = {{1,1,1},
    					  {1,0,1},
    					  {1,1,1} };
    	
    	setZeroes2(input);
    	System.out.println(Arrays.deepToString(input));
    	
    }
}