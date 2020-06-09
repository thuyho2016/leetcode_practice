package matrix;
/*
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4    square:  1 1  <--row 2 , col 3, 4
                      1 1  <-- row 3

Time complexity : O(mn). Single pass.

Space complexity : O(n). Another array which stores elements in a row is used for dp.
 */

public class MaximalSquare {
/**
 * DP
 * We can only form a square of bigger length if the current index value is > 0 and 
 * all the three adjacent cells (i-1,j-1) , (i,j-1) and (i-1,j) are > 0. 
 * So take the minimum value of these indices are increase it by 1, also update max.
 */
	public static int maximalSquare(char[][] matrix) {
		if(matrix.length == 0)
			return 0;
		
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

	    int max = 0;
	    
	    //start index 1
	    for(int i= 1 ;i <= matrix.length; i++)
	    {
	        for(int j= 1; j <= matrix[0].length; j++)
	        {
	            if(matrix[i - 1][j - 1] == '1')
	            {	                
	                dp[i][j] = Math.min(dp[i-1][j-1], Math.min( dp[i][j-1], dp[i-1][j])) + 1;
	                
	                max = Math.max(max,dp[i][j]);
	            } 
	        }    
	    }    
		    
		return max * max;
	}
	
    //Sliding window - scans the matrix and grows in size each time it contains only '1'
	 public static int maximalSquare2(char[][] matrix) {
	        int size = 0;
	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = 0; j < matrix[0].length; j++) {
	                while (isSquare(matrix, i, j, size)) {
	                    size++;
	                }
	            }
	        }
	        return size * size;
	    }
	    
	    private static boolean isSquare(char[][] matrix, int startI, int startJ, int size) {
	        int endI = startI + size;
	        int endJ = startJ + size;
	        
	        if (endI > matrix.length-1 || endJ > matrix[0].length-1) { // check out of boundary
	            return false;
	        }
	        for (int i = startI; i <= endI; i++) {
	            for (int j = startJ; j <= endJ; j++) {
	                if (matrix[i][j] != '1') {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	
	public static void main (String[] agrs) {
		
		char[][] graph = { {'1','0','1','0','0'},
							{'1','0','1','1','1'},
							{'1','1','1','1','1'},
							{'1','0','0','1','0'} };		
		
		System.out.println("Result: " + maximalSquare(graph)); //4
		
		System.out.println("Result: " + maximalSquare2(graph)); //4
	
	}
}