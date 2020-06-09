package matrix;


/*85. Maximal Rectangle
https://leetcode.com/problems/maximal-rectangle/

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]



		
Time complexity: O(N * M^2) where M is the number of rows and N is the number of columns.

Space complexity: O(M,N))  We allocate an equal sized array to store the maximum width at each point.
 
 We can compute the maximum width of a rectangle that ends at a given coordinate in constant time. 
 We do this by keeping track of the number of consecutive ones each square in each row. As we iterate over each row we update the maximum possible width at that point. 
 This is done using row[i] = row[i - 1] + 1 if row[i] == '1'
 */

public class MaximalRectangle {
	
	public static int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0)
			return 0;
		
		int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];  //[[0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0],...

	    int max = 0;
	    
	    for(int i= 0;i < matrix.length; i++)
	    {
	        for(int j= 0; j < matrix[0].length; j++)
	        {
	            if(matrix[i][j] == '1')
	            {	   
	            	 // compute the maximum width and update dp with it
	            	if (i == 0)
	          		  	dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
	                  else
	                	  dp[i][j] = matrix[i][j] == '1' ? ( dp[i-1][j] + 1 ) : 0;
	          		  

                    int width = dp[i][j]; //min 1
                    
                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                    	
                        width = Math.min(width, dp[k][j]); //1
                        max = Math.max(max,  width * (i - k + 1)); //max(0, 0 - 0 + 1) = 1
                    }
	            } 
	        }    
	    }    
		    
		return max;
	}
	
    
	public static void main (String[] agrs) {
		
		char[][] graph = { {'1','0','1','0','0'},
							{'1','0','1','1','1'},
							{'1','1','1','1','1'},
							{'1','0','0','1','0'} };		
		
		System.out.println("Result: " + maximalRectangle(graph)); //6
		
	
	}
}